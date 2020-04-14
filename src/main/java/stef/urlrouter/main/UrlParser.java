package stef.urlrouter.main;

import java.util.HashMap;

/**
 * @author stef Class that checks if the provided URL matches the provided
 *         template. A template can contain :parameter as a placeholder for a
 *         parameter and [optional] as a placeholder for an optional part of the
 *         URL.
 */
public class UrlParser {

	private String template;
	private String url;
	private String http;
	private String hostname;
	private String path;
	private HashMap<String, String> parameters;

	private static String HTTP = "http";
	private static String HTTPS = "https";
	private static String HTTP_DELIMITER = "://";

	public UrlParser(String template, String url) {
		this.template = template;
		this.url = url;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HashMap<String, String> checkTemplate() {
		getHttp();
		getHostname();
		getPath();
		getParameters();

		HashMap<String, String> parsedUrl = new HashMap<String, String>(4);
		parsedUrl.put(HTTP, this.http);
		parsedUrl.put("hostname", this.hostname);
		parsedUrl.put("path", this.path);
		parsedUrl.put("parameters", getParametersString());
		return parsedUrl;
	}

	private String getParametersString() {
		StringBuilder builder = new StringBuilder();
		if (this.parameters != null) {
			this.parameters.forEach(
					(x, y) -> builder.append("\"").append(x).append("\":\"").append(y).append("\"").append(" "));
		}
		return builder.toString();
	}

	private void getParameters() {
		if (this.parameters == null) {
			this.parameters = new HashMap<String, String>();
		}

		boolean hasOptionalPart = this.template.contains("[");
		if (hasOptionalPart) {
			System.out.println("gettion optional part");
			getOptionalPart();
		}

		String[] splittedTemplate = this.template.split("/");
		String[] splittedPath = this.path.split("/");
		if (splittedPath != null && splittedTemplate != null) {
			for (int i = 0; i < splittedPath.length; i++) {
				if (splittedTemplate[i].contains(":")) {
					this.parameters.put((String) splittedTemplate[i].subSequence(1, splittedTemplate[i].length()),
							splittedPath[i]);
				}
			}
		} else {
			System.out.println("url does not follow the template");
		}
	}

	private void getOptionalPart() {
		String[] optionalPart = this.template.split("\\[/");
		if (optionalPart.length > 2) {
			System.out.println("more than 1 optional parts");
		} else {
			System.out.println("only one optional part");
			for (String i : optionalPart) {
				System.out.println(i);
			}
		}
	}

	private void getPath() {
		String[] split = this.url.split(this.hostname);
		if (split.length == 2) {
			this.path = split[1];
		} else {
			this.path = "/";
		}
	}

	private void getHostname() {
		String urlWithoutHttp = this.url.split(this.http + HTTP_DELIMITER)[1];
		String hostname = urlWithoutHttp.split("/")[0];
		this.hostname = hostname;
	}

	private void getHttp() {
		String result = null;
		if (!this.url.contains(HTTPS) && this.url.contains(HTTP)) {
			result = HTTP;
		} else {
			result = HTTPS;
		}
		this.http = result;
	}

}

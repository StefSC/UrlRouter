package stef.urlrouter.main;

import java.util.Arrays;
import java.util.List;

public class UrlRouter {

	public static void main(String[] args) {
		List<String> templates = Arrays.asList("/", "/:lang", "/:lang/products", "/:lang/products/:id",
				"/:lang/products/:id/compare/:compareId", "/:lang/products/:id/images[/:imageId]");
		List<String> urls = Arrays.asList("https://www.google.com", "https://www.google.com/en",
				"https://www.google.com/en/products", "https://www.google.com/en/products/123",
				"https://www.google.com/en/products/123/compare/432", "https://www.google.com/en/products/123/images",
				"https://www.google.com/en/products/123/images/2");
		for (int i = 0; i < templates.size(); i++) {
			UrlParser urlParser = new UrlParser(templates.get(i), urls.get(i));
			System.out.println(templates.get(i));
			System.out.println(urls.get(i));
			System.out.println(urlParser.checkTemplate());
		}
//		UrlParser urlParser = new UrlParser(templates.get(templates.size() - 1), urls.get(urls.size() - 1));
//		System.out.println(urlParser.getTemplate());
//		System.out.println(urlParser.getUrl());
//		System.out.println(urlParser.checkTemplate());
//		
//		UrlParser urlParserFail = new UrlParser("/:lang/products/elefants", "https://www.google.com/en/products/123/images/2");
//		System.out.println(urlParserFail.getTemplate());
//		System.out.println(urlParserFail.getUrl());
//		System.out.println(urlParserFail.checkTemplate());
	}

}

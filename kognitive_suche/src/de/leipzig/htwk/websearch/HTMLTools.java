package de.leipzig.htwk.websearch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Franz Schwarzer
 *
 */

public class HTMLTools {

  public String filterHTML(String htmlCode) {

    /**
     * Funktion um aus einem HTML Code die HTML Befhele herauszufiltern
     * 
     * @param htmlCode - zu �bergeben ist ein HTML Quellcode
     * @return Es wird der Klartext zur�ckgegeben
     */

    htmlCode = htmlCode.replaceAll("\\<.*?\\>", ""); // filtert HTML-Tags
    htmlCode = htmlCode.replaceAll("\\s+", " "); // filtert Leerzeichen
    htmlCode = StringEscapeUtils.unescapeXml(htmlCode);
    htmlCode = StringEscapeUtils.unescapeHtml3(htmlCode);
    htmlCode = StringEscapeUtils.unescapeHtml4(htmlCode);


    return htmlCode;

  }

	public Document getHTMLDocument(String url) {
		Document doc=null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("_________________-------__________");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return doc;
	}
	
	public String getHTMLText(Document document){
		if(document==null){
			return "";
		}
			return document.body().text();
	}

	static String getMetaTag(Document document, String attr) {
		
		if(document==null){
			return "";
		}

		Elements elements = document.select("meta[name=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		elements = document.select("meta[property=" + attr + "]");
		for (Element element : elements) {
			final String s = element.attr("content");
			if (s != null)
				return s;
		}
		return null;
	}
	
	String getMetaKeys(Document document){
		return getMetaTag(document,"keywords");
		

	}
}

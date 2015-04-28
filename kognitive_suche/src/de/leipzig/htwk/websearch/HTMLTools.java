package de.leipzig.htwk.websearch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.lang3.StringEscapeUtils;

// @author Franz Schwarzer

public class HTMLTools {

	public String filterHTML(String htmlCode) {
		
		/**
		   * Funktion um aus einem HTML Code die HTML Befhele herauszufiltern
		   * 
		   * @param htmlCode - zu �bergeben ist ein HTML Quellcode
		   * @return Es wird  der Klartext zur�ckgegeben
		   */

		StringEscapeUtils eu=new StringEscapeUtils();
		htmlCode = htmlCode.replaceAll("\\<.*?\\>", ""); // filtert HTML-Tags
		htmlCode= htmlCode.replaceAll("\\s+", " "); // filtert Leerzeichen
		htmlCode=eu.unescapeXml(htmlCode);
		htmlCode=eu.unescapeHtml3(htmlCode);
		htmlCode=eu.unescapeHtml4(htmlCode);
		
		
		return htmlCode;

	}

	public String getHTMLSourceCode(String url) {

		StringBuilder sb = new StringBuilder();
		
		/**
		   * Funktion zum herauslesen des Quellcodes aus einer URL
		   * 
		   * @paramsb - StringBuilder --> fasst die Zeilen zu einem String zusammen
		   * @return gibt den Quellcode zur�ck
		   */
		try {
			Scanner scanner = new Scanner(new InputStreamReader(new URL(url).openStream(),StandardCharsets.UTF_8));
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine() + "\n");
			}
			scanner.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	public String[] getMetaKeys(String htmlSourceCode){
		
		/**
		   * Funktion, welche aus dem Quelltext die Metakeys in  ein Array speichert
		   * 
		   * @param htmlSourceCode - zu �bergeben ist ein HTML QuellCode
		   * @return gibt die Meta Keys zur�ck
		   */
		
	    int index = htmlSourceCode.indexOf("<meta name=\"keywords\" content=");
	    String sub = htmlSourceCode.substring(index);
	    index = sub.indexOf("/>");
	    sub = sub.substring(0, index);

	    for (int i = 0; i < 2; i++) {
	      index = sub.indexOf("\"", sub.indexOf("\"") + 1);
	      sub = sub.substring(index);
	    }

	    sub = sub.substring(1, sub.length() - 2);
	    String[] keys = sub.split(", ");

	    return keys;
	}
}

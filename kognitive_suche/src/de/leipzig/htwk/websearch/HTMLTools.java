package de.leipzig.htwk.websearch;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// @author Franz Schwarzer

public class HTMLTools {

	public static String filterHTML(String htmlCode) {

		htmlCode = htmlCode.replaceAll("\\<.*?\\>", ""); // filtert HTML-Tags
		return htmlCode.replaceAll("\\s", " "); // filtert Leerzeichen

	}

	public static String getHTMLSourceCode(String url) {

		StringBuilder sb = new StringBuilder();

		try {
			Scanner scanner = new Scanner(new URL(url).openStream());
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
}

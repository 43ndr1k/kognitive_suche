package Faroo;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.text.html.parser.DocumentParser;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.Properties;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class API {
	private ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
	private NodeList nList = null;

	public API(String key, String query) {
		super();

		String url = "http://www.faroo.com/api?q=" + query + "&f=xml&key="+ key;
		// get xml from faroo
		this.getData(url);
	}

	public API(String key, String query, int length) {
		super();
		String url = "http://www.faroo.com/api?q=" + query + "&length="+ length + "&f=xml&key=" + key;
		// get xml from faro
		this.getData(url);
	}

	private void getData(String u) {

		String xmlstring = "";
		try {

			HttpURLConnection conn;
			URL url = new URL(u);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader rd;
			String line;
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				xmlstring += line;
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					xmlstring)));

			nList = document.getElementsByTagName("result");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HashMap<String, String>> getCompleteResults() {

		// query
		// "https://faroo-faroo-web-search.p.mashape.com/api?q=test&src=news&length=10&f=xml";

		for (int NodeAtPosition = 0; NodeAtPosition < nList.getLength(); NodeAtPosition++) {
			Element e = (Element) nList.item(NodeAtPosition);
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("title",
					NodeAtPosition + ": "+ e.getElementsByTagName("title").item(0).getTextContent().trim());
			results.add(result);
			// System.out.println("\n" +
			// "Ergebnis "+ results[0].get("title") +"\n"+
			// "Website url: " +
			// e.getElementsByTagName("url").item(0).getTextContent().trim()
			// +"\n"+
			// "Domain: " +
			// e.getElementsByTagName("domain").item(0).getTextContent().trim()
			// +"\n"+
			// "imageUrl: " +
			// e.getElementsByTagName("imageUrl").item(0).getTextContent().trim()
			// +"\n"+
			// "firstIndexed: " +
			// e.getElementsByTagName("firstIndexed").item(0).getTextContent().trim()
			// +"\n"+
			// "firstPublished: " +
			// e.getElementsByTagName("firstPublished").item(0).getTextContent().trim()
			// +"\n"+
			// "kwic: " +
			// e.getElementsByTagName("kwic").item(0).getTextContent().trim()
			// +"\n"+
			// "author: " +
			// e.getElementsByTagName("author").item(0).getTextContent().trim()
			// +"\n"+
			// "votes: " +
			// e.getElementsByTagName("votes").item(0).getTextContent().trim()
			// +"\n"+
			// "isNews: " +
			// e.getElementsByTagName("isNews").item(0).getTextContent().trim()
			// +"\n"+
			// "==========================="
			// );

		}
		return results;
	}

}

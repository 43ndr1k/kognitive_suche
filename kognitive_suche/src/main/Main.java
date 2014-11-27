package main;

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
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Main {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	

		LeseFaroo l = new LeseFaroo();
		Parser p = new Parser();
		//query
		String a = "http://www.faroo.com/api?q=test&src=news&length=10&f=xml";
		//"https://faroo-faroo-web-search.p.mashape.com/api?q=test&src=news&length=10&f=xml";
		NodeList nList = null;
		try {
			nList = p.parse(l.getHTML(a));
		} catch (SAXException | IOException | ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int NodeAtPosition = 0; NodeAtPosition < nList.getLength(); NodeAtPosition++){
			Node result = nList.item(NodeAtPosition);
			Element e = (Element) result;

			System.out.println("\n" +
					"Ergebnis "+ NodeAtPosition +": " + e.getElementsByTagName("title").item(0).getTextContent().trim() +"\n"+
					"Website url: " + e.getElementsByTagName("url").item(0).getTextContent().trim() +"\n"+ 
					"Domain: " + e.getElementsByTagName("domain").item(0).getTextContent().trim() +"\n"+
					"imageUrl: " + e.getElementsByTagName("imageUrl").item(0).getTextContent().trim() +"\n"+
					"firstIndexed: " + e.getElementsByTagName("firstIndexed").item(0).getTextContent().trim() +"\n"+
					"firstPublished: " + e.getElementsByTagName("firstPublished").item(0).getTextContent().trim() +"\n"+
					"kwic: " + e.getElementsByTagName("kwic").item(0).getTextContent().trim() +"\n"+
					"author: " + e.getElementsByTagName("author").item(0).getTextContent().trim() +"\n"+
					"votes: " + e.getElementsByTagName("votes").item(0).getTextContent().trim() +"\n"+
					"isNews: " + e.getElementsByTagName("isNews").item(0).getTextContent().trim() +"\n"+
					"==========================="
			 );
		}
	}
}


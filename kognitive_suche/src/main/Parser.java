package main;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Parser {

	public  NodeList parse(String xmlString) throws SAXException, IOException, ParserConfigurationException{

		try 
		{ 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse( new InputSource( new StringReader( xmlString ) ) );

			NodeList nList = document.getElementsByTagName("result");

			return nList;

		}catch (Exception e) {  
			e.printStackTrace();
			return null; 
		}
	}
}	





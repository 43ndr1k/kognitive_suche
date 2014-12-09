package Faroo;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Dies ist die API, um die Faroo API anzusprechen.
 * 
 * @author Hendrik Sawade
 * @version 1.21
 */
public class API {
	private ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
	private NodeList nList = null;
	private String key = null;
	private HttpURLConnection conn = null;

	/**
	 * Abruf des Suchwortes
	 * @param key API-Key
	 *
	 */
	
	public API(String key) {
		this.key = key;

	}

	/**
	 * Abruf nach Suchwort und beschränkter Anzahl von Suchergebnissen
	 *
	 * @param query
	 *
	 */

	public void query(String query) throws APIExecption {
		
		String url = "http://www.faroo.com/api?q=" + query + "&f=xml&key="+ key;
		// get xml from faroo
		this.getData(url);
		
	}

	/**
	 * Anfrage an die Faroo API stellen
	 * url wird zusammen gestzt
	 * @param query
	 * @param length
	 * @throws Exception
	 */
	public void query(String query, int length) throws Exception{

		if(length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		
		String url = "http://www.faroo.com/api?q=" + query + "&f=xml&key="+ key+"&length="+length;
		// get xml from faroo
		this.getData(url);
		
	}
	
	/**
	 * 
	 * Hier wird die Verbindung zu Faroo aufgebaut und die Daten empfangen.
	 * Es wird eine XML Struktur empfangen
	 * Aus der XML Struktur wird ein Dokument erstellt
	 * @param u URL
	 */
	private void getData(String u) throws APIExecption {
		nList = null;
		String xmlstring = "";
		try {
			//TODO connection aufrecht erhalten

			URL url = new URL(u);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() >= 400){
				throw new APIExecption(conn.getResponseCode());
			}
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

	/**
	 * Hier wird aus dem ersetllen Dokument eine ArrayList
	 * Es werden die einzelden Tags abgefragt und in eine HashMap eingefügt. Aus den vielen Ergebnissen wird so eine
	 * Liste. Jeder Eintrag in der HashMap entspricht ein Tag. Diese Liste wird einer ArrayList hinzugefügt. Die ArrayList
	 * enthält alle Suchergebnisse.
	 *
	 * @return results ArrayList
	 * 
	 */
	public ArrayList<HashMap<String, String>> getCompleteResults() throws Exception {

		// query
		// "https://faroo-faroo-web-search.p.mashape.com/api?q=test&src=news&length=10&f=xml";
		results.clear();
		for (int NodeAtPosition = 0; NodeAtPosition < nList.getLength(); NodeAtPosition++) {
			Element e = (Element) nList.item(NodeAtPosition);
			HashMap<String, String> result = new HashMap<String, String>();
			
			result.put("title", NodeAtPosition + ": "+ e.getElementsByTagName("title").item(0).getTextContent().trim());
			result.put("url", NodeAtPosition + ": "+ e.getElementsByTagName("url").item(0).getTextContent().trim());
			result.put("domain", NodeAtPosition + ": "+ e.getElementsByTagName("domain").item(0).getTextContent().trim());
			result.put("imageUrl", NodeAtPosition + ": "+ e.getElementsByTagName("imageUrl").item(0).getTextContent().trim());
			result.put("firstIndexed", NodeAtPosition + ": "+ e.getElementsByTagName("firstIndexed").item(0).getTextContent().trim());
			result.put("firstPublished", NodeAtPosition + ": "+ e.getElementsByTagName("firstPublished").item(0).getTextContent().trim());
			result.put("kwic", NodeAtPosition + ": "+ e.getElementsByTagName("kwic").item(0).getTextContent().trim());
			result.put("author", NodeAtPosition + ": "+ e.getElementsByTagName("author").item(0).getTextContent().trim());
			result.put("votes", NodeAtPosition + ": "+ e.getElementsByTagName("votes").item(0).getTextContent().trim());
			result.put("isNews", NodeAtPosition + ": "+ e.getElementsByTagName("isNews").item(0).getTextContent().trim());
	
			results.add(result);
		}
		if(results != null){
			return results;
		}
		else{
			throw new Exception("Kein Ergebnis");
		}

	}

}

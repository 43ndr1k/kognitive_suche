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
 * @version 1.22
 */
public class API {
	private static String[] replacements = { "%", "%25", " ", "%20", "!",
			"%21", "#", "%23", "\\$", "%24", "\"", "%22", "&", "%26", "’",
			"%27", "\\(", "%28", "\\)", "%29", "\\*", "%2A", "\\+", "%2B", ",",
			"%2C", "/", "%2F", ":", "%3A", ";", "%3B", "=", "%3D", "\\?",
			"%3F", "@", "%40", "\\[", "%5B", "]", "%5D" };

	private APIResults apiresult = null;
	private String key = null;
	private HttpURLConnection conn = null;

	/**
	 * Key eintragen in die Klasse
	 * 
	 * @param key
	 *            API-Key
	 * 
	 */

	public API(String key) {
		this.key = key;
	}

	/**
	 * Abruf nach Suchwort
	 * 
	 * @param query
	 * 
	 */

	public void query(String query) throws APIExecption {

		query = encoding(query);
		String url = "&q=" + query;
		// get xml from faroo
		this.getData(url);

	}

	/**
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 * @param length
	 * @throws Exception
	 */
	public void query(String query, int length) throws Exception {

		if (length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&length=" + length + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfangen
	 * @param length
	 *            Anzahl an Ergebnisse (max. 10 - default 10)
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, int start, int length) throws Exception {

		if (start < 1 || length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&length=" + length + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfangen
	 * @param length
	 *            Anzahl an Ergebnisse (max. 10 - default 10)
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, int start, int length, String language)
			throws Exception {

		if (start < 1 || length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&length=" + length + "&l=" + language
				+ "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfangen
	 * @param length
	 *            Anzahl an Ergebnisse (max. 10 - default 10)
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @param src
	 *            Source web Web Search (default) Sorted by relevancy Contains
	 *            all kinds of results news News Search Sorted by publishing
	 *            date Contains only news articles from newspapers, magazines
	 *            and blogs topics Trending Topics Similar to Trending News:
	 *            Trending News: for each topic a main article with all
	 *            properties + related articles with title, url, domain only.
	 *            Trending Topics: for each topic all the related articles are
	 *            provided with all properties (more data, slower transfer).
	 *            trends Trending Terms Trending terms, sorted by buzz (number
	 *            of sources reporting on same term). suggest Suggestions
	 *            Suggestions include auto completes for query substrings and
	 *            corrections for misspelled terms. When using the above
	 *            searches with parameter i=true, the suggesti- ons are already
	 *            included in the search result.
	 * 
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, int start, int length, String language,
			String src) throws Exception {

		if (start < 1 || length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&length=" + length + "&l=" + language
				+ "&src=" + src + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfangen
	 * @param length
	 *            Anzahl an Ergebnisse (max. 10 - default 10)
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @param src
	 *            Source web Web Search (default) Sorted by relevancy Contains
	 *            all kinds of results news News Search Sorted by publishing
	 *            date Contains only news articles from newspapers, magazines
	 *            and blogs topics Trending Topics Similar to Trending News:
	 *            Trending News: for each topic a main article with all
	 *            properties + related articles with title, url, domain only.
	 *            Trending Topics: for each topic all the related articles are
	 *            provided with all properties (more data, slower transfer).
	 *            trends Trending Terms Trending terms, sorted by buzz (number
	 *            of sources reporting on same term). suggest Suggestions
	 *            Suggestions include auto completes for query substrings and
	 *            corrections for misspelled terms. When using the above
	 *            searches with parameter i=true, the suggesti- ons are already
	 *            included in the search result.
	 * 
	 * @param kwic
	 *            Keyword in context false snippet is selected from the begin-
	 *            ning of the article. true (default) snippet is selected from
	 *            the article parts con- taining the keywords.
	 * 
	 * @throws Exception
	 *             Wert zu klein
	 */

	public void query(String query, int start, int length, String language,
			String src, String kwic) throws Exception {

		if (start < 1 || length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&length=" + length + "&l=" + language
				+ "&src=" + src + "&kwic=" + kwic + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfangen
	 * @param length
	 *            Anzahl an Ergebnisse (max. 10 - default 10)
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @param src
	 *            Source web Web Search (default) Sorted by relevancy Contains
	 *            all kinds of results news News Search Sorted by publishing
	 *            date Contains only news articles from newspapers, magazines
	 *            and blogs topics Trending Topics Similar to Trending News:
	 *            Trending News: for each topic a main article with all
	 *            properties + related articles with title, url, domain only.
	 *            Trending Topics: for each topic all the related articles are
	 *            provided with all properties (more data, slower transfer).
	 *            trends Trending Terms Trending terms, sorted by buzz (number
	 *            of sources reporting on same term). suggest Suggestions
	 *            Suggestions include auto completes for query substrings and
	 *            corrections for misspelled terms. When using the above
	 *            searches with parameter i=true, the suggesti- ons are already
	 *            included in the search result.
	 * 
	 * @param kwic
	 *            Keyword in context false snippet is selected from the begin-
	 *            ning of the article. true (default) snippet is selected from
	 *            the article parts con- taining the keywords.
	 * @param i
	 *            Instant search false (default) searches for query q true
	 *            searches for best suggestion if query q is substring or
	 *            misspelled. Slower search!
	 * 
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, int start, int length, String language,
			String src, String kwic, boolean i) throws Exception {

		if (start < 1 || length < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&length=" + length + "&l=" + language
				+ "&src=" + src + "&kwic=" + kwic + "&i=" + i + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfange
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @param src
	 *            Source web Web Search (default) Sorted by relevancy Contains
	 *            all kinds of results news News Search Sorted by publishing
	 *            date Contains only news articles from newspapers, magazines
	 *            and blogs topics Trending Topics Similar to Trending News:
	 *            Trending News: for each topic a main article with all
	 *            properties + related articles with title, url, domain only.
	 *            Trending Topics: for each topic all the related articles are
	 *            provided with all properties (more data, slower transfer).
	 *            trends Trending Terms Trending terms, sorted by buzz (number
	 *            of sources reporting on same term). suggest Suggestions
	 *            Suggestions include auto completes for query substrings and
	 *            corrections for misspelled terms. When using the above
	 *            searches with parameter i=true, the suggesti- ons are already
	 *            included in the search result.
	 * 
	 * @param kwic
	 *            Keyword in context false snippet is selected from the begin-
	 *            ning of the article. true (default) snippet is selected from
	 *            the article parts con- taining the keywords.
	 * @param i
	 *            Instant search false (default) searches for query q true
	 *            searches for best suggestion if query q is substring or
	 *            misspelled. Slower search!
	 * 
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, int start, String language, String src,
			String kwic, boolean i) throws Exception {

		if (start < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&l=" + language + "&src=" + src
				+ "&kwic=" + kwic + "&i=" + i + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @param src
	 *            Source web Web Search (default) Sorted by relevancy Contains
	 *            all kinds of results news News Search Sorted by publishing
	 *            date Contains only news articles from newspapers, magazines
	 *            and blogs topics Trending Topics Similar to Trending News:
	 *            Trending News: for each topic a main article with all
	 *            properties + related articles with title, url, domain only.
	 *            Trending Topics: for each topic all the related articles are
	 *            provided with all properties (more data, slower transfer).
	 *            trends Trending Terms Trending terms, sorted by buzz (number
	 *            of sources reporting on same term). suggest Suggestions
	 *            Suggestions include auto completes for query substrings and
	 *            corrections for misspelled terms. When using the above
	 *            searches with parameter i=true, the suggesti- ons are already
	 *            included in the search result.
	 * 
	 * @param kwic
	 *            Keyword in context false snippet is selected from the begin-
	 *            ning of the article. true (default) snippet is selected from
	 *            the article parts con- taining the keywords.
	 * @param i
	 *            Instant search false (default) searches for query q true
	 *            searches for best suggestion if query q is substring or
	 *            misspelled. Slower search!
	 * 
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, String language, String src, String kwic,
			boolean i) throws Exception {

		query = encoding(query);
		String url = "&l=" + language + "&src=" + src + "&kwic=" + kwic + "&i="
				+ i + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param start
	 *            Bei welchem Suchergebnis soll die Suche anfangen
	 * @param src
	 *            Source web Web Search (default) Sorted by relevancy Contains
	 *            all kinds of results news News Search Sorted by publishing
	 *            date Contains only news articles from newspapers, magazines
	 *            and blogs topics Trending Topics Similar to Trending News:
	 *            Trending News: for each topic a main article with all
	 *            properties + related articles with title, url, domain only.
	 *            Trending Topics: for each topic all the related articles are
	 *            provided with all properties (more data, slower transfer).
	 *            trends Trending Terms Trending terms, sorted by buzz (number
	 *            of sources reporting on same term). suggest Suggestions
	 *            Suggestions include auto completes for query substrings and
	 *            corrections for misspelled terms. When using the above
	 *            searches with parameter i=true, the suggesti- ons are already
	 *            included in the search result.
	 * 
	 * @param kwic
	 *            Keyword in context false snippet is selected from the begin-
	 *            ning of the article. true (default) snippet is selected from
	 *            the article parts con- taining the keywords.
	 * 
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, int start, String src, String kwic)
			throws Exception {

		if (start < 1) {

			throw new Exception("Wert kleiner 1");
		}
		query = encoding(query);
		String url = "&start=" + start + "&src=" + src + "&kwic=" + kwic
				+ "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, String language) throws Exception {

		query = encoding(query);
		String url = "&l=" + language + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * Anfrage an die Faroo API stellen url wird zusammen gestzt
	 * 
	 * @param query
	 *            Suchwort
	 * @param language
	 *            Englisch = en, Deutsch = ge, Chinesisch = zh
	 * @param i
	 *            Instant search false (default) searches for query q true
	 *            searches for best suggestion if query q is substring or
	 *            misspelled. Slower search!
	 * @throws Exception
	 *             Wert zu klein
	 */
	public void query(String query, String language, boolean i)
			throws Exception {

		query = encoding(query);
		String url = "&l=" + language + "&i=" + i + "&q=" + query;
		// get xml from faroo
		this.getData(url);
	}

	/**
	 * 
	 * Hier wird die Verbindung zu Faroo aufgebaut und die Daten empfangen. Es
	 * wird eine XML Struktur empfangen Aus der XML Struktur wird ein Dokument
	 * erstellt
	 * 
	 * @param u
	 *            URL
	 */
	private void getData(String u) throws APIExecption {

		NodeList nList = null;

		String xmlstring = "";
		try {
			// TODO connection aufrecht erhalten

			URL url = new URL("http://www.faroo.com/api?f=xml&key=" + key + u);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() >= 400) {
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

			// query
			// "https://faroo-faroo-web-search.p.mashape.com/api?q=test&src=news&length=10&f=xml";

			ArrayList<Result> results = new ArrayList<Result>();
			for (int NodeAtPosition = 0; NodeAtPosition < nList.getLength(); NodeAtPosition++) {
				Element e = (Element) nList.item(NodeAtPosition);

				String title = e.getElementsByTagName("title").item(0)
						.getTextContent().trim();
				String kwic = e.getElementsByTagName("kwic").item(0)
						.getTextContent().trim();
				String author = e.getElementsByTagName("author").item(0)
						.getTextContent().trim();
				String votes = e.getElementsByTagName("votes").item(0)
						.getTextContent().trim();
				String isNews = e.getElementsByTagName("isNews").item(0)
						.getTextContent().trim();
				String url = (e.getElementsByTagName("url").item(0)
						.getTextContent().trim());
				String domain = (e.getElementsByTagName("domain").item(0)
						.getTextContent().trim());
				String imageUrl = (e.getElementsByTagName("imageUrl").item(0)
						.getTextContent().trim());
				String firstIndexed = e.getElementsByTagName("firstIndexed")
						.item(0).getTextContent().trim();
				String firstPublished = e
						.getElementsByTagName("firstPublished").item(0)
						.getTextContent().trim();

				results.add(new Result(title, kwic, author, votes, isNews, url,
						domain, imageUrl, firstIndexed, firstPublished));

			}

			/**
			 * ArrayList<HashMap<String, String>> results = new
			 * ArrayList<HashMap<String, String>>(); for (int NodeAtPosition =
			 * 0; NodeAtPosition < nList.getLength(); NodeAtPosition++) {
			 * Element e = (Element) nList.item(NodeAtPosition); HashMap<String,
			 * String> result = new HashMap<String, String>();
			 * 
			 * result.put("title",
			 * e.getElementsByTagName("title").item(0).getTextContent().trim());
			 * result.put("url",
			 * e.getElementsByTagName("url").item(0).getTextContent().trim());
			 * result.put("domain",
			 * e.getElementsByTagName("domain").item(0).getTextContent
			 * ().trim()); result.put("imageUrl",
			 * e.getElementsByTagName("imageUrl"
			 * ).item(0).getTextContent().trim()); result.put("firstIndexed",
			 * e.getElementsByTagName
			 * ("firstIndexed").item(0).getTextContent().trim());
			 * result.put("firstPublished",
			 * e.getElementsByTagName("firstPublished"
			 * ).item(0).getTextContent().trim()); result.put("kwic",
			 * e.getElementsByTagName("kwic").item(0).getTextContent().trim());
			 * result.put("author",
			 * e.getElementsByTagName("author").item(0).getTextContent
			 * ().trim()); result.put("votes",
			 * e.getElementsByTagName("votes").item(0).getTextContent().trim());
			 * result.put("isNews",
			 * e.getElementsByTagName("isNews").item(0).getTextContent
			 * ().trim());
			 * 
			 * results.add(result); }
			 **/
			if (results != null) {
				apiresult = new APIResults(results);
			} else {
				throw new Exception("Kein Ergebnis");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hier wird aus dem ersetllen Dokument eine ArrayList Es werden die
	 * einzelden Tags abgefragt und in eine HashMap eingefügt. Aus den vielen
	 * Ergebnissen wird so eine Liste. Jeder Eintrag in der HashMap entspricht
	 * ein Tag. Diese Liste wird einer ArrayList hinzugefügt. Die ArrayList
	 * enthält alle Suchergebnisse.
	 * 
	 * @return results ArrayList
	 * 
	 */
	public APIResults getResult() throws Exception {
		return apiresult;
	}

	/**
	 * Diese Methode codiert die query nach URL-Envording Richtlien
	 * 
	 * @param u
	 *            query
	 * @return u
	 */
	private String encoding(String u) {

		for (int i = 0; i < replacements.length - 1; i = i + 2) {
			u = u.replaceAll(replacements[i], replacements[i + 1]);
		}

		return u;
	}

}

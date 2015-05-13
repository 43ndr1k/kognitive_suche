package de.leipzig.htwk.faroo.api;


import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Autor Hendrik Sawade.
 */

/**
 *
 * Die Klasse Api stellt die query Methoden bereit und kommuniziert mit der Faroo Suchmaschine über
 * eine API Schnittstelle.
 */
public class Api {

  /* maybe out sourced in constant interface */
  // private static final String URL_PART_1 = "http://www.faroo.com/api?q=";
  // prtttte static final String URL_PART_3 = "&start=1&length=10&l=en&src=web&i=false&f=json&key=";
  // private static final String URL_PART_4 = "2CJIbhzsHU4nlSqBVZ2OP3fimb4_";

  /**
   * Dienen für die korrekte Darstellung des Suchbegriffes. Muss in html verträgliche Darstellung
   * gebracht werden.
   */
  private static String[] REPLACEMENTS = {"%", "%25", " ", "%20", "!", "%21", "#", "%23", "\\$",
      "%24", "\"", "%22", "&", "%26", "’", "%27", "\\(", "%28", "\\)", "%29", "\\*", "%2A", "\\+",
      "%2B", ",", "%2C", "/", "%2F", ":", "%3A", ";", "%3B", "=", "%3D", "\\?", "%3F", "@", "%40",
      "\\[", "%5B", "]", "%5D"};

  private String key, _url;

  /**
   * Entgegen nehmen von der Faroo URL und dessen Key.
   * 
   * @param key API key
   * @param url API URL
   */
  public Api(String key, String url) {
    this.key = key;
    this._url = url;
  }



  /**
   * Hier wird zu Faroo die Verbindung aufgebaut.
   * 
   * @param urlParameter API URL
   * @return Results Liste
   * @throws APIExecption
   */
  private Results queryJson(String urlParameter) throws APIExecption {
    HttpURLConnection con = null;
    try {

      URL url = new URL(_url + urlParameter + "&key=" + key);
      con = (HttpURLConnection) url.openConnection();

      if (con.getResponseCode() >= 400) {
        throw new APIExecption(con.getResponseCode());
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }


    try {
      String response = readResult(con);

      Gson gson = new Gson();
      Results results = gson.fromJson(response, Results.class);
      if (results.getResults().size() != 0) {
        return results;
      } else {
        Result res = new Result("Kein Ergebnis", "", "", "", "", "", "", "", "", "");
        results.getResults().add(res);
        return results;
        // throw new Exception("Kein Ergebnis");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Hier werden die Daten empfangen. Es wird eine JSON Struktur empfangen.
   * 
   * @param pHttpURLConnection Verbindungsaufbau zur API
   * @return Liste mit JSON Objekten
   */
  private String readResult(HttpURLConnection pHttpURLConnection) {

    StringBuffer response = new StringBuffer();

    try {

      int responseCode = pHttpURLConnection.getResponseCode();
      System.out.println("Result_: " + responseCode);
      BufferedReader in =
          new BufferedReader(new InputStreamReader(pHttpURLConnection.getInputStream()));
      String inputLine;

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    return response.toString();
  }

  /**
   * Diese Methode codiert die query nach URL-Envording Richtlien
   * 
   * @param u query
   * @return u Bearbeitete query
   */
  private String encoding(String u) {

    for (int i = 0; i < REPLACEMENTS.length - 1; i = i + 2) {
      u = u.replaceAll(REPLACEMENTS[i], REPLACEMENTS[i + 1]);
    }

    return u;
  }

  // ********************************************************************************//
  // Querys


  /**
   * Anfrage an die Faroo API stellen, url wird zusammen gestzt
   * 
   * @param query - Suchwort
   * @return Results Liste
   * @throws APIExecption
   */
  public Results query(String query) throws APIExecption {
    query = encoding(query);
    String urlParameter = "&start=1&length=10&l=de&src=web&i=false&f=json" + "&q=" + query;
    return queryJson(urlParameter);


  }

  /**
   * Anfrage an die Faroo API stellen, url wird zusammen gestzt
   * 
   * @param query Suchwort
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @return Results Liste
   * @throws Exception
   */
  public Results query(String query, String language) throws APIExecption {
    query = encoding(query);
    String urlParameter = "&l=" + language + "&q=" + query;
    return queryJson(urlParameter);
  }

  /**
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param length Wie viele Suchergbnisse
   * @throws Exception
   * @return Results Liste
   */
  public Results query(String query, int length) throws APIExecption {

    if (length < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url = "&length=" + length + "&q=" + query;

    return queryJson(url);
  }

  /**
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfangen
   * @param length Anzahl an Ergebnisse (max. 10 - default 10)
   * @throws Exception Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, int start, int length) throws APIExecption {

    if (start < 1 || length < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url = "&start=" + start + "&length=" + length + "&q=" + query;
    return queryJson(url);
  }

  /**
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfangen
   * @param length Anzahl an Ergebnisse (max. 10 - default 10)
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @throws Exception Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, int start, int length, String language) throws APIExecption {

    if (start < 1 || length < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url = "&start=" + start + "&length=" + length + "&l=" + language + "&q=" + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfangen
   * @param length Anzahl an Ergebnisse (max. 10 - default 10)
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * @throws APIExecption Wert kleiner 1.
   * @return Results Liste
   */
  public Results query(String query, int start, int length, String language, String src)
      throws APIExecption {

    if (start < 1 || length < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url =
        "&start=" + start + "&length=" + length + "&l=" + language + "&src=" + src + "&q=" + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfangen
   * @param length Anzahl an Ergebnisse (max. 10 - default 10)
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * @param kwic Keyword in context false snippet is selected from the begin- ning of the article.
   *        true (default) snippet is selected from the article parts con- taining the keywords.
   *
   * @throws APIExecption Wert kleiner 1.
   * @return Results Liste
   */

  public Results query(String query, int start, int length, String language, String src, String kwic)
      throws APIExecption {

    if (start < 1 || length < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url =
        "&start=" + start + "&length=" + length + "&l=" + language + "&src=" + src + "&kwic="
            + kwic + "&q=" + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfangen
   * @param length Anzahl an Ergebnisse (max. 10 - default 10)
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * @param kwic Keyword in context false snippet is selected from the begin- ning of the article.
   *        true (default) snippet is selected from the article parts con- taining the keywords.
   * @param i Instant search false (default) searches for query q true searches for best suggestion
   *        if query q is substring or misspelled. Slower search!
   *
   * @throws APIExecption Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, int start, int length, String language, String src,
      String kwic, boolean i) throws APIExecption {

    if (start < 1 || length < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url =
        "&start=" + start + "&length=" + length + "&l=" + language + "&src=" + src + "&kwic="
            + kwic + "&i=" + i + "&q=" + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfange
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * @param kwic Keyword in context false snippet is selected from the begin- ning of the article.
   *        true (default) snippet is selected from the article parts con- taining the keywords.
   * @param i Instant search false (default) searches for query q true searches for best suggestion
   *        if query q is substring or misspelled. Slower search!
   *
   * @throws APIExecption Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, int start, String language, String src, String kwic, boolean i)
      throws APIExecption {

    if (start < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url =
        "&start=" + start + "&l=" + language + "&src=" + src + "&kwic=" + kwic + "&i=" + i + "&q="
            + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfange
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * 
   * @throws APIExecption Wert zu klein
   * @return Results Liste
   */
  public Results query(int start, String query, String language, String src) throws APIExecption {

    if (start < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url = "&start=" + start + "&l=" + language + "&src=" + src + "&q=" + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * @param kwic Keyword in context false snippet is selected from the begin- ning of the article.
   *        true (default) snippet is selected from the article parts con- taining the keywords.
   * @param i Instant search false (default) searches for query q true searches for best suggestion
   *        if query q is substring or misspelled. Slower search!
   *
   * @throws APIExecption Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, String language, String src, String kwic, boolean i)
      throws APIExecption {

    query = encoding(query);
    String url = "&l=" + language + "&src=" + src + "&kwic=" + kwic + "&i=" + i + "&q=" + query;
    return queryJson(url);
  }

  /**
   *
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param start Bei welchem Suchergebnis soll die Suche anfangen
   * @param src Source web Web Search (default) Sorted by relevancy Contains all kinds of results
   *        news News Search Sorted by publishing date Contains only news articles from newspapers,
   *        magazines and blogs topics Trending Topics Similar to Trending News: Trending News: for
   *        each topic a main article with all properties + related articles with title, url, domain
   *        only. Trending Topics: for each topic all the related articles are provided with all
   *        properties (more data, slower transfer). trends Trending Terms Trending terms, sorted by
   *        buzz (number of sources reporting on same term). suggest Suggestions Suggestions include
   *        auto completes for query substrings and corrections for misspelled terms. When using the
   *        above searches with parameter i=true, the suggesti- ons are already included in the
   *        search result.
   *
   * @param kwic Keyword in context false snippet is selected from the begin- ning of the article.
   *        true (default) snippet is selected from the article parts con- taining the keywords.
   *
   * @throws APIExecption Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, int start, String src, String kwic) throws APIExecption {

    if (start < 1) {

      throw new APIExecption("Wert kleiner 1");
    }
    query = encoding(query);
    String url = "&start=" + start + "&src=" + src + "&kwic=" + kwic + "&q=" + query;
    return queryJson(url);
  }


  /**
   * Anfrage an die Faroo API stellen url wird zusammen gestzt
   *
   * @param query Suchwort
   * @param language Englisch = en, Deutsch = ge, Chinesisch = zh
   * @param i Instant search false (default) searches for query q true searches for best suggestion
   *        if query q is substring or misspelled. Slower search!
   * @throws APIExecption Wert zu klein
   * @return Results Liste
   */
  public Results query(String query, String language, boolean i) throws APIExecption {

    query = encoding(query);
    String url = "&l=" + language + "&i=" + i + "&q=" + query;
    return queryJson(url);
  }



}

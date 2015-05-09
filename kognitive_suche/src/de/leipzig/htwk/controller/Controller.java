package de.leipzig.htwk.controller;

import cognitive.search.ReturnTagObject;
import de.leipzig.htwk.faroo.api.*;
import de.leipzig.htwk.searchApi.DuckDuckGoSearchApi;
import de.leipzig.htwk.searchApi.SearchApiExecption;
import de.leipzig.htwk.websearch.HTMLTools;
import de.leipzig.htwk.websearch.Static;
import de.leipzig.htwk.websearch.ThreadRun;
import gui.GUI;
import visualize.VisController;
import java.util.ArrayList;

import pdf.box.access.PDFDocument;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import cognitive.search.ApiCognitiveSearch;
import cognitive.search.ReturnTagList;

/**
 * @author Hendrik Sawade
 */

/**
 * Der Controller dient zur Vermittlung. Er wickelt die Kommunikation zwischen den einzelenen
 * Klassen ab.
 */
public class Controller {

  /**
   * Die Parameter für die Weitergabe der einzelnen Informationen.
   */
  private String language, src;
  private int start = 1;
  private String key, url;
  private Results results;
  private String query;
  private GUI gui;
  private String searchWord;
  private ArrayList<PDFDocument> pdfBoxDocuments;

  /**
   * Ruft das Konfiguationsfile ab. In dieser steht der Faroo Key und die Faroo API URL.
   */
  public Controller() {
    ConfigFileManagement config = new ConfigFileManagement();
    this.key = config.getKey();
    this.url = config.geturl();
  }

  /**
   * Parameter für die Faroo API
   * 
   * @param _src Welche Informationen möchte man haben
   * @param s Startwert ab welchem Suchergebnis man Ergebnisse haben möchte.
   * @param l Welche Sprache
   */
  public void setParameter(String l, String _src, int s) {
    this.language = l;
    this.src = _src;
    this.start = s;
  }

  /**
   * Die Suchanfrage an Faroo, diese wird von der GUI aufgerufen.
   *
   * @return Results Liste mit den Ergebnisse.
   */
  public void querySearchEngine(int pSearchEngine, String pSearchWord) throws SearchApiExecption {

    this.searchWord = pSearchWord;

    switch (pSearchEngine){
        case 0:
            System.out.println("Query Faroo");

            Api api = new Api(key, url);
            setQuery(searchWord);
            try {
                System.out.println("Suche gestartet");
                this.results = api.query(this.start, searchWord, this.language, src);
                beginWebSearch(this.results);

            } catch (APIExecption apiExecption) {
                apiExecption.printStackTrace();
            }
            break;
        case 1:
            System.out.println("Query DuckDuckGo");

            DuckDuckGoSearchApi duckApi = new DuckDuckGoSearchApi(searchWord, 80);
            this.results = duckApi.getResultList();

            for(Result r: results.getResults()){

                String url = r.getUrl();
                r.setUrl("http://www." + url);

                System.out.println(r.getUrl());

            }

            beginWebSearch(this.results);

            break;
    }

  }

  private void beginWebSearch(Results pResults) {

    /**
     * @author Franz Schwarzer
     */

    long zstVorher = System.currentTimeMillis();

    HTMLTools webSearch = new HTMLTools();

    int resultSize = pResults.getResults().size();
    ThreadRun tr = new ThreadRun(pResults, searchWord, resultSize);
    String[] clearPageText = new String[resultSize];
    for (int i = 0; i < resultSize; i++) {
      System.out.println(i);
      clearPageText[i] = webSearch.filterHTML(Static.pageText[i]);
      System.out.println(clearPageText[i]);
    }


    long zstNachher = System.currentTimeMillis(); // Zeitmessung
    System.out.println("Zeit benötigt: Webseiten Suche: " + ((zstNachher - zstVorher))
        + " millisec");

    beginCognitiveSearch(clearPageText, searchWord);

  }

  /**
   * @author TobiasLenz
   * @param searchText selbsterklärend
   * @param searchWord selbsterklärend
   */

  private void beginCognitiveSearch(String[] searchText, String searchWord) {
    long zstVorher = System.currentTimeMillis();

    ApiCognitiveSearch search = new ApiCognitiveSearch(searchText, searchWord);
    ReturnTagList tags;

    search.doWordCount(); // Häufigkeitsanalyse + Umgebungsanalyse
    search.doMergeTagInfos(); // Zusammenführen von Tag-Infos der Analysen
    /**
     * Hier können durch Nutzung der Api durch search.AddInfo bekannte Tag-Informationen hinzugefügt
     * werden
     * 
     * bsp. search.AddTagInfo(String[] headline, priority 10);
     */


    search.doEditTags(); // Bearbeiten der Tags

    long zstNachher = System.currentTimeMillis(); // Zeitmessung
    System.out.println("Zeit benötigt: Kognitiver Algorithmus: " + ((zstNachher - zstVorher))
        + " millisec");

    tags = search.getTags();
      System.out.println("Tags found for searchword: " + searchWord);
    for(ReturnTagObject t:tags.getTagObjects()){
        System.out.println(t.getTag());
    }

    initVisual(tags, searchWord); // Aufruf der Visualisierung



  }

  /**
   * Setzt die Results Liste temporär für den simple Algorithmus.
   * 
   * @param results - Results Liste
   */
/*  private void setResultList(Results results) {
    this.results = results;
  }*/

  /**
   * Stellt die Results Liste zur Verfügung.
   * 
   * @return results Results Liste
   */
 /* public Results getResultList() {
    return this.results;
  }*/

  /**
   * Setzt das Suchwort
   * 
   * @param query query
   */
  private void setQuery(String query) {
    this.query = query;
  }

  /**
   * Gibt das Suchwort zurück.
   * 
   * @return query
   */
  public String getQuery() {
    return this.query;
  }

  /**
   * Methode für die Visualisierung nach Eingabe eines Suchbegriffes
   * 
   * @author Fabian Freihube, Sebastian Hügelmann
   * @param list Übergabe der gefundenen Ergebnisse per Liste.
   * @param searchword Übergabe des Suchwortes als String.
   */
  public void initVisual(ReturnTagList list, String searchword) {
    /*
     * das Objekt Tag, welches aus der Klasse visualtest übernommen wird dient zu Testzwecken und
     * kann bei der fertigen Implementation durch ein Objekt des Komplexen Suchalgorithmus ersezt
     * werden.
     */
    System.out.println("startVisual Gestartet");

    BorderPane visPane = new BorderPane();
    BorderPane homebuttonPane = new BorderPane();

    homebuttonPane.setCenter(gui.goHomeButton());
    homebuttonPane.setStyle("-fx-background-color: #FFF;");
    homebuttonPane.setPrefHeight(gui.getWindowheight() * 0.15);

    VisController visualControler = new VisController();
    visualControler.setGUI(this.gui);
    visualControler.setPane(visPane);
    visualControler.setQuery(searchword);

    // iv
    visualControler.setPaneHeight((int) (gui.getStage().getHeight() * 0.85));
    visualControler.setPaneWidth((int) gui.getStage().getWidth());

    visPane.setCenter(visualControler.startVisualize(list));
    visPane.setTop(homebuttonPane);
    System.out.println("startVisual fertig");

    Scene visual = new Scene(visPane);
    gui.setStageScene(visual);
  }


  /**
   * Methode zur Übergabe der GUI an den Controller.
   * 
   * @author Sebastian Hügelmann
   */
  public void setGUI(GUI gui) {
    this.gui = gui;
  }

  /**
   * In dieser Funktion werden die Funktionen für eine Suche über Faroo gestartet.
   * 
   * @author Tobias Lenz, Franz Schwarzer
   * @param searchWord - Der Suchtext, welcher über die Suchmaschine genutzt werden soll.
  /* *//*
  public void farooSearch(String searchWord) {
    this.searchWord = searchWord;
    long zstVorher = System.currentTimeMillis();

    Results results = queryFaroo(); // Starten der Faroo Suche

    long zstNachher = System.currentTimeMillis(); // Zeitmessung
    System.out.println("Zeit benötigt: Faroo Suche: " + ((zstNachher - zstVorher)) + " millisec");
    beginWebSearch(results);
  }*/

  public ArrayList<PDFDocument> getPDFBoxDocuments() {
    return gui.getPDFBoxDocuments();
  }

}

package de.leipzig.htwk.controller;

import de.leipzig.htwk.faroo.api.APIExecption;
import de.leipzig.htwk.faroo.api.Api;
import de.leipzig.htwk.faroo.api.ConfigFileManagement;
import de.leipzig.htwk.faroo.api.Results;
import de.leipzig.htwk.websearch.HTMLTools;
import de.leipzig.htwk.websearch.Static;
import de.leipzig.htwk.websearch.ThreadRun;
import gui.GUI;
import simple.algorithm.*;
import visualize.VisController;
import java.util.ArrayList;
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
   * @param pQuery Suchwort
   * @return Results Liste mit den Ergebnisse.
   */
  public void queryFaroo() {
    Api api = new Api(key, url);
    setQuery(searchWord);
    try {
      System.out.println("Suche gestartet");
      Results results = api.query(this.start, searchWord, this.language, src);
      setResultList(results);


    } catch (APIExecption apiExecption) {
      apiExecption.printStackTrace();
    }
  }

  private void beginWebSearch() {

    /**
     * @author Franz Schwarzer
     */
    String tmp;
    HTMLTools webSearch = new HTMLTools();
    Results r = results;
    int resultSize = r.getResults().size();
    String[] searchText = new String[resultSize];

    ThreadRun tr = new ThreadRun(r, searchWord, resultSize);
    String clearPageText[] = new String[resultSize];
    for (int i = 0; i < resultSize; i++) {
      System.out.println(i);
      clearPageText[i] = webSearch.filterHTML(Static.pageText[i]);
      System.out.println(clearPageText[i]);
    }
    beginCognitiveSearch(clearPageText, searchWord);

  }

  private void beginCognitiveSearch(String[] searchText, String searchWord) {
    String searchword = searchWord;
    ApiCognitiveSearch search = new ApiCognitiveSearch();
    ReturnTagList list = new ReturnTagList();
    list = search.ApiCognitiveSearch(searchText, searchWord);
    initVisual(list, searchword);

  }

  /**
   * Setzt die Results Liste temporär für den simple Algorithmus.
   * 
   * @param results - Results Liste
   */
  private void setResultList(Results results) {
    this.results = results;
  }

  /**
   * Stellt die Results Liste zur Verfügung.
   * 
   * @return results Results Liste
   */
  public Results getResultList() {
    return this.results;
  }

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
   * @param gui Objekt GUI aktuelle GUI.
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
    ReturnTagList tags = list;

    BorderPane visPane = new BorderPane();
    BorderPane homebuttonPane = new BorderPane();

    homebuttonPane.setCenter(gui.goHomeButton());
    homebuttonPane.setStyle("-fx-background-color: #FFF;");
    homebuttonPane.setPrefHeight(gui.getWindowheight() * 0.15);

    VisController visualControler = new VisController();
    visualControler.setPane(visPane);
    visualControler.setQuery(searchword);
    // iv
    visualControler.setPaneHeight((int) (gui.getStage().getHeight() * 0.85));
    visualControler.setPaneWidth((int) gui.getStage().getWidth());

    visPane.setCenter(visualControler.startVisualize(tags));
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
   * @param text - Der Suchtext, welcher über die Suchmaschine genutzt werden soll.
   */
  public void farooSearch(String searchWord) {
    this.searchWord = searchWord;
    queryFaroo();
    beginWebSearch();

  }

  public ArrayList<SimAlgTags> getTags() {
    return null;
  }
}

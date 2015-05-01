package de.leipzig.htwk.controller;

import de.leipzig.htwk.faroo.api.APIExecption;
import de.leipzig.htwk.faroo.api.Api;
import de.leipzig.htwk.faroo.api.ConfigFileManagement;
import de.leipzig.htwk.faroo.api.Result;
import de.leipzig.htwk.faroo.api.Results;
import de.leipzig.htwk.searchApi.DuckDuckGoSearchApi;
import de.leipzig.htwk.searchApi.SearchApiExecption;
import de.leipzig.htwk.searchApi.SearchResults;
import de.leipzig.htwk.tests.VisualTest;
import de.leipzig.htwk.websearch.HTMLTools;
import de.leipzig.htwk.websearch.Static;
import de.leipzig.htwk.websearch.ThreadRun;
import de.leipzig.htwk.createJson.CreateJsonDoc;
import gui.GUI;
import simple.algorithm.*;

import java.net.MalformedURLException;
import java.util.ArrayList;

import cognitive.search.ApiCognitiveSearch;
import cognitive.search.ReturnTagList;
import komplexe.suche.TagObjectList;

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
  private ReturnTagList tags;
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
   * @param searchWord Suchwort
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
    
    ThreadRun tr = new ThreadRun(r, searchWord,resultSize);
    String clearPageText[]=new String[resultSize];
    for(int i=0;i<resultSize;i++){
    	System.out.println(i);
    	clearPageText[i]=webSearch.filterHTML(Static.pageText[i]);
    	System.out.println(clearPageText[i]);
	}
    beginCognitiveSearch(clearPageText, searchWord);

  }

  private void beginCognitiveSearch(String[] searchText, String searchWord) {

    ApiCognitiveSearch search = new ApiCognitiveSearch();
    ReturnTagList list = new ReturnTagList();
    list = search.ApiCognitiveSearch(searchText, searchWord);
    initVisual(list);

  }

  private void initVisual(ReturnTagList list) {
    gui.startVisual(list);

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
    this.searchWord = query;
  }

  /**
   * Gibt das Suchwort zurück.
   * 
   * @return searchWord
   */
  public String getQuery() {
    return this.searchWord;
  }


  /**
   * Methode für den Aufruf der startVisual() Methode in der Klasse GUI.
   * 
   * @author Sebastian Hügelmann
   * @param gui Nutzt momentanen Status der GUI.
   */
  public void initVisual() {

    gui.startVisual(tags);

  }


  /**
   * Methode Gui Setter
   */
  public void setGUI(GUI gui) {
    this.gui = gui;
  }

  public ArrayList<SimAlgTags> getTags() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * In dieser Funktion werden die Funktionen für eine Suche über Faroo gestartet.
   * 
   * @param text - Der Suchtext, welcher über die Suchmaschine genutzt werden soll.
   */
  public void farooSearch(String searchWord)  {
    this.searchWord = searchWord;
    try {
      duckDuckGoSearch();
    } catch (SearchApiExecption searchApiExecption) {
      searchApiExecption.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    queryFaroo();
    beginWebSearch();

  }

  /**
   *
   * @return searchResults Liste mit den Ergebnissen aus der DuckDuckGo Suchmaschine.
   * @throws SearchApiExecption
   */
public SearchResults duckDuckGoSearch() throws SearchApiExecption, MalformedURLException {
  DuckDuckGoSearchApi search = new DuckDuckGoSearchApi(getQuery(), 100);
  return search.getDuckDuckGoResults();
}
}

package de.leipzig.htwk.controller;

import de.leipzig.htwk.cognitive.search.ApiCognitiveSearch;
import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.faroo.api.APIExecption;
import de.leipzig.htwk.faroo.api.Api;
import de.leipzig.htwk.config.ConfigFileManagement;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.pdf.box.access.PDFDocument;
import de.leipzig.htwk.search.history.HistoryObject;
import de.leipzig.htwk.search.history.SearchHistory;
import de.leipzig.htwk.searchApi.DuckDuckGoSearchApi;
import de.leipzig.htwk.searchApi.PhantomjsDriver;
import de.leipzig.htwk.searchApi.Results;
import de.leipzig.htwk.searchApi.SearchApiExecption;
import de.leipzig.htwk.websearch.HTMLTools;
import de.leipzig.htwk.websearch.Static;
import de.leipzig.htwk.websearch.ThreadRun;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.ArrayList;
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
  private String key, url, pfad;
  private Results results = null;
  private GUI gui;
  private String searchWord;
  private static ArrayList<PDFDocument> pdfBoxDocuments;
  private SearchHistory lastSearches;
  private PhantomJSDriver driver;
  // test
  private Scene visual;
  private ReturnTagList tags;

  public Scene getVisual() {
    return visual;
  }

  /**
   * Ruft das Konfiguationsfile ab. In dieser steht der Faroo Key und die Faroo API URL.
   */
  public Controller() {
    ConfigFileManagement config = new ConfigFileManagement();
    this.key = config.getKey();
    this.url = config.geturl();
    this.pfad = config.getPfad();
    PhantomjsDriver pJD = new PhantomjsDriver(pfad);
    this.driver = pJD.getDriver();
    lastSearches = new SearchHistory();
   
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


  private void beginWebSearch() {

    /**
     * @author Franz Schwarzer
     */

    long zstVorher = System.currentTimeMillis();

    HTMLTools webSearch = new HTMLTools();
    Results r = this.results;
    int resultSize = r.getResults().size();
    ThreadRun tr = new ThreadRun(r, searchWord, resultSize);
    String[] clearPageText = new String[resultSize];
    for (int i = 0; i < resultSize; i++) {
      clearPageText[i] = Static.pageText[i];
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

  }


  public void setResultList(Results results) {
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
  public void setQuery(String query) {
    this.searchWord = query;
  }

  /**
   * Gibt das Suchwort zurück.
   *
   * @return query
   */
  public String getQuery() {
    return this.searchWord;
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
   * Annahme der PDFDocument Elemente
   *
   * @param PDFBoxDocuments ArrayList welche die Namen der PDFs sowie deren Keywords enthält
   */
  public void setPDFBoxDocuments(ArrayList<PDFDocument> PDFBoxDocuments) {
    this.pdfBoxDocuments = PDFBoxDocuments;
  }

  public ArrayList<PDFDocument> getPdfBoxDocuments() {
    return pdfBoxDocuments;
  }




  /**
   * @Autor Hendrik Sawade
   * Die Suchanfrage an Faroo und DuckDuckGo, diese wird von der GUI
   *        aufgerufen.
   *
   * @return Results Liste mit den Ergebnisse.
   */
  public void querySearchEngine(int pSearchEngine) throws SearchApiExecption {



     lastSearches.addSearch(searchWord);

     switch (pSearchEngine) {
       case 0:
         System.out.println("Query Faroo");


         System.out.println("Query Faroo");

         Api api = new Api(key, url);
         try {
           System.out.println("Suche gestartet");
           this.results = api.query(this.start, searchWord, this.language, src);

         } catch (APIExecption apiExecption) {
           apiExecption.printStackTrace();
         }

         break;
       case 1:

         System.out.println("Query DuckDuckGo");
         long zstVorher = System.currentTimeMillis();

         DuckDuckGoSearchApi duckApi = new DuckDuckGoSearchApi(this.searchWord, 80, this.driver);
         this.results = duckApi.getResultList();


         long zstNachher = System.currentTimeMillis(); // Zeitmessung
         System.out.println(
             "Zeit benötigt: DuckDuckGo Suche Gesamt: " + ((zstNachher - zstVorher)) + " millisec");

         break;

     }

     if(!this.results.getResults().isEmpty()) {
       beginWebSearch();
     } else {
       /**
        * Error Meldung falls es keine Ergebnisse gefunden wurden.
        */
       Alert alert = new Alert(AlertType.ERROR);
       alert.setTitle("Error Dialog");
       alert.setHeaderText("Error Message");
       alert.setContentText("Keine Ergebnisse zum Suchbegriff: \n" + this.searchWord + " gefunden!");
       alert.showAndWait();
       GUI.getInstance().goToHome();

     }
  }


  /**
   * @Autor Hendrik Sawade Schließt den phantomjs Driver
   */
  public void closeDriver() {
    this.driver.quit();
  }


  public ArrayList<HistoryObject> getHistory() {
    return lastSearches.getSearches();
  }

  /**
   * Her wird die Suche der PDF-Dokumente aufgerufen Vorher müssen dem Controller die Dateien
   * übergeben werden
   * 
   * @param searchWord
   */
  public void startPDFSearch(String searchWord) {
    this.searchWord = searchWord;
    adaptPDFFormat();
  }
/**
 * Hier wird das PDF Objekt an die Anforderungen der CognitiveSearchAPI angepasst.
 */
  private void adaptPDFFormat() {
    pdfBoxDocuments.size();
  }



  public ReturnTagList getTags() {
    return tags;
  }

public void setTags(ReturnTagList tagList) {
	this.tags = tagList;
	
}
  
}

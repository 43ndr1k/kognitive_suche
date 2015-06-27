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
import de.leipzig.htwk.searchApi.*;
import de.leipzig.htwk.websearch.HTMLTools;
import de.leipzig.htwk.websearch.Static;
import de.leipzig.htwk.websearch.ThreadRun;
import javafx.application.Platform;
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
  private String key, url, pfad;
  private Results results = null;
  private String searchWord;
  private static ArrayList<PDFDocument> pdfBoxDocuments;
  private SearchHistory lastSearches;
  private PhantomJSDriver driver;
  private ReturnTagList tags;

  /**
   * Ruft das Konfiguationsfile ab. In dieser steht der Faroo Key und die Faroo API URL.
   */
  public Controller() {

    lastSearches = new SearchHistory();

  }

  /**
   * hier wird der StartMode übergeben und die Für den jeweiligen Modi benötigten Funktionen
   * aufgerufen
   * 
   * @param startMode 0 - webSuche 1- PDFSuche
   */
  public void setStartMode(int startMode) {
    if (startMode == 0) {
      ConfigFileManagement config = new ConfigFileManagement();
      this.key = config.getKey();
      this.url = config.geturl();
      this.pfad = config.getPfad();
      PhantomjsDriver pJD = new PhantomjsDriver(pfad);
      this.driver = pJD.getDriver();
    }
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
   * @Autor Hendrik Sawade Die Suchanfrage an Faroo und DuckDuckGo, diese wird von der GUI
   *        aufgerufen.
   *
   * @return Results Liste mit den Ergebnisse.
   */
  public void querySearchEngine(int pSearchEngine) throws SearchApiExecption {



    lastSearches.addSearch(searchWord);

    switch (pSearchEngine) {
      case 0:
        System.out.println("Query Faroo");
        Api api = new Api(key, url);
        try {
          System.out.println("Suche gestartet");

          Results r;
          ArrayList<Result> r1 = new ArrayList<Result>();
          ArrayList<Result> r2 = new ArrayList<Result>();
          for (int i = 1; i < 80; i = i + 10) {
            r = (api.query(i, searchWord, this.language, src));
            r1 = r.getResults();
            r2.addAll(r1);
          }
          Results r3 = new Results();
          r3.setResults(r2);
          this.results = r3;
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
        System.out.println("Zeit benötigt: DuckDuckGo Suche Gesamt: " + ((zstNachher - zstVorher))
            + " millisec");

        break;

    }

    if (!this.results.getResults().isEmpty()) {
      beginWebSearch();
    } else {
      /**
       * Error Meldung falls es keine Ergebnisse gefunden wurden.
       */
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
         
    
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setHeaderText("Error Message");
      alert.setContentText("Keine Ergebnisse zum Suchbegriff: \n" + searchWord + " gefunden!");
      alert.showAndWait();
        }
      });
      //GUI.getInstance().goToHome();

    }
  }


  /**
   * @Autor Hendrik Sawade Schließt den phantomjs Driver
   */
  public void closeDriver() {
    if (driver != null) {
      this.driver.quit();
    }
  }


  public ArrayList<HistoryObject> getHistory() {
    return lastSearches.getSearches();
  }

  /**
   * Her wird die Suche der PDF-Dokumente aufgerufen Vorher müssen dem Controller die Dateien
   * übergeben werden
   */
  public void startPDFSearch() {
    String[] searchText = adaptPDFFormat();
    adaptResultListToPDFFormat();
    beginCognitiveSearch(searchText, searchWord);
  }

  /**
   * Hier wird die Result Liste an die Daten der PDF-Dateien angepasst
   */
  private void adaptResultListToPDFFormat() {
    ArrayList<Result> result = new ArrayList<Result>();
    for (int i = 0; i < pdfBoxDocuments.size(); i++) {
      PDFDocument pdf = pdfBoxDocuments.get(i);

      String title = pdf.getDocname();
      title = title.substring(title.lastIndexOf('/') + 1, title.lastIndexOf('.'));
      String kwic = pdf.getText()[0];
      String url = pdf.getDocname();
      result.add(new Result(title, kwic, url));
    }
    results = new Results();
    results.setResults(result);
  }

  /**
   * Hier wird das PDF Objekt an die Anforderungen der CognitiveSearchAPI angepasst.
   * 
   * @return
   */
  private String[] adaptPDFFormat() {
    String[] searchText = new String[pdfBoxDocuments.size()];
    for (int i = 0; i < pdfBoxDocuments.size(); i++) {
      PDFDocument pdf = pdfBoxDocuments.get(i);
      searchText[i] = "";
      for (int j = 0; j < pdf.getText().length; j++) {
        searchText[i] += pdf.getText()[j]+ " ";
      }
    }
    return searchText;


  }

  public ReturnTagList getTags() {
    return tags;
  }

  public void setTags(ReturnTagList tagList) {
    this.tags = tagList;

  }


}

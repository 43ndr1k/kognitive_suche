package de.leipzig.htwk.searchApi;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import de.leipzig.htwk.infoBox.MessageBox;
import javafx.application.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @Autor Hendrik Sawade.
 */
public class SearchApi {

    /**
     * unitDriver lädt die Engerine für die Websuche.
     */
    private HtmlUnitDriver unitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);

    /**
     * Beinhaltet die alle Results als Result Objekte.
     */
    ArrayList<Result> resultList = new ArrayList<Result>();
    /**
     * URL der Suchmaschine.
     */
    private String url;

    /**
     * Setzt den Tag für den Search Button fest.
     */
    String nextButton;

    /**
     * titleClassList, linkClassList, descriptionClassList dienen zur Temporären Speicherung der Suchergebnisse
     * entsprechen der bedeutung.
     */
    List<WebElement> titleClassList = null;
    List<WebElement> linkClassList = null;
    List<WebElement> descriptionClassList = null;

    /**
     * Klassen Parameter für large, url und snippet
     */
    String titleClass, linkClass, descriptionClass;

    /**
     * Wie viele Ergebnisse will man haben
     */
    int anzResultSize;


    public SearchApi(String url, String nextButton, String titleClass, String urlKlasse, String snippetKlasse) {

        this.url = url;
        this.nextButton = nextButton;
        this.titleClass = titleClass;
        this.linkClass = urlKlasse;
        this.descriptionClass = snippetKlasse;

    }
    /**
     * Dienen für die korrekte Darstellung des Suchbegriffes. Muss in html verträgliche Darstellung gebracht werden.
     */
    private static String[] REPLACEMENTS = { "%", "%25", " ", "%20", "!",
            "%21", "#", "%23", "\\$", "%24", "\"", "%22", "&", "%26", "’",
            "%27", "\\(", "%28", "\\)", "%29", "\\*", "%2A", "\\+", "%2B", ",",
            "%2C", "/", "%2F", ":", "%3A", ";", "%3B", "=", "%3D", "\\?",
            "%3F", "@", "%40", "\\[", "%5B", "]", "%5D" };

    /**
     * Diese Methode codiert die query nach URL-Envording Richtlien
     * @param query query
     * @return url Bearbeitete query String
     */
    private String encoding(String query) {

        for (int i = 0; i < REPLACEMENTS.length - 1; i = i + 2) {
            query = query.replaceAll(REPLACEMENTS[i], REPLACEMENTS[i + 1]);
        }
        return query;
    }

    /**
     * Anfrage an die Suchmaschine stellen, url wird zusammen gesezt und codiert.
     * @param query - Suchwort
     * @return Results Liste
     * @throws SearchApiExecption
     */
    public ArrayList<Result> query(String query) throws SearchApiExecption, MalformedURLException {
        query = encoding(query);
        return searching(this.url + query);
    }

    /**
     * Verbindung zum Internet Testen.
     */
    private void internetTest() {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) new URL("http://www.google.de").openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.setRequestMethod("HEAD");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseCode != 200) {
            // Not OK.
            System.out.println("Fehler!!!!!!!!!!!!!!!!!!!!!!!!!!!111");
            infoBox("Verbindung zum Internet ist fehlgeschlagen.","Verbindungsfehler","Internet Connection");
            System.exit(0);
        }
    }

    /**
     * Kappselt den Suchvorgang.
     * @param completeUrl String zur Suchmaschine, einschließlich des Suchwortes
     * @return
     */
    private ArrayList<Result> searching(String completeUrl) throws SearchApiExecption, MalformedURLException {

        internetTest();

        try {
            unitDriver.get(completeUrl);
        } catch (WebDriverException ioe) {
            ioe.printStackTrace();

            Platform.exit();
            throw new SearchApiExecption("Keine Internetverbindung möglich");
        }

        try {
            for (int i = 0; i < this.anzResultSize; i++) {
                this.titleClassList = (getList(this.titleClass));
                this.linkClassList = (getList(this.linkClass));
                this.descriptionClassList = (getList(this.descriptionClass));
                makeResultList();
                moreResults();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SearchApiExecption("Beim Erstellen der Ergebnisliste ist ein Fehler aufgetreten");
        }
        return this.resultList;
    }

    /**
     * Info Box für Meldungen.
     * @param infoMessage
     * @param titleBar
     * @param headerMessage
     */
    public void infoBox(String infoMessage, String titleBar, String headerMessage) {
        MessageBox messageBox = new MessageBox(infoMessage, titleBar, headerMessage);
        messageBox.run();
        messageBox.showAndWait();
    }

    /**
     * Kümmert sich dadrum das es mehr Suchergebnisse gibt.
     */
    protected void moreResults() throws SearchApiExecption {
        try {
            WebElement next = unitDriver.findElement(By.className(this.nextButton));
            next.click();
        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new SearchApiExecption("Fehler bei klicken des next Buttons");
        }
    }


    /**
     * Gibt eine Result Liste zurück,mit den Result Objekt.
     * @return resultList Beinhaltet eine List mit Result Objekten.
     */
    private void makeResultList() {
        for (int i = 0; i < this.titleClassList.size();i++) {
            this.resultList.add(new Result(
                    this.titleClassList.get(i).getText(),
                    this.descriptionClassList.get(i).getText(),
                    this.linkClassList.get(i).getText())
            );
        }

    }

    /**
     * Sucht nach einer bestimmten Kklasse, die man übergibt. Die Klasse wo die Ergennisse drin stehen.
     * @param className
     * @return Liste mit den gefundenen Suchergebnissen der angegebenen Klasse.
     */
    private List<WebElement> getList(String className) throws SearchApiExecption {
        List<WebElement> list = null;
        try {

            list = unitDriver.findElements(By.className(className));

        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new SearchApiExecption("Bei dem Suchen von Ergebnissen, in den Tags, ist ein Fehler aufgetreten.");
        }
        return list;
    }

    public SearchResults getDuckDuckGoResults() {
        SearchResults results = new SearchResults();
        results.setResults(resultList);
        return results;
    }

    public void setAnzResultSize(int anzResultSize) {
        this.anzResultSize = anzResultSize;
    }
}


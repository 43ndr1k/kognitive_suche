package de.leipzig.htwk.searchApi;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import javafx.application.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

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
    int anzResultSize, gesamtAnzahlErgebnisse;

    /**
     * Wie viele Ergebnisse gibt es pro Seite.
     */
    private int anzSiteResults;

    /**
     * Wie viele ResterErgebnisse sind noch vorhanden.
     * (gesamtAnzahlErgebnisse - anzSiteResults
     */
    int anzRestResults = 0;

    /**
     *
     * @param url
     * @param nextButton
     * @param titleClass
     * @param urlKlasse
     * @param snippetKlasse
     * @param anzSiteResults
     * @param gesamtAnzahlErgebnisse
     */

    public SearchApi(String url, String nextButton, String titleClass, String urlKlasse,
                     String snippetKlasse, int anzSiteResults, int gesamtAnzahlErgebnisse) {

        this.url = url;
        this.nextButton = nextButton;
        this.titleClass = titleClass;
        this.linkClass = urlKlasse;
        this.descriptionClass = snippetKlasse;
        this.anzSiteResults = anzSiteResults;
        this.gesamtAnzahlErgebnisse = gesamtAnzahlErgebnisse;
        this.anzRestResults = gesamtAnzahlErgebnisse;
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
    public ArrayList<Result> query(String query) throws SearchApiExecption {
        query = encoding(query);
        return searching(this.url + query);
    }

    /**
     * Kappselt den Suchvorgang.
     * @param completeUrl String zur Suchmaschine, einschließlich des Suchwortes
     * @return
     */
    private ArrayList<Result> searching(String completeUrl) throws SearchApiExecption {

        try {
            unitDriver.get(completeUrl);
        } catch (WebDriverException ioe) {
            ioe.printStackTrace();

            Platform.exit();
            throw new SearchApiExecption("Keine Internetverbindung möglich");
        }

        try {
            for (int i = 0; i < this.anzResultSize; i++) {
                if((this.anzResultSize > 0) && (this.anzResultSize-1 != i)) {
                    makeClassLists();
                    makeResultList();
                    moreResults();
                } else {
                    makeClassLists();
                    makeResultList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SearchApiExecption("Beim Erstellen der Ergebnisliste ist ein Fehler aufgetreten");
        }
        return this.resultList;
    }

    /**
     * Erstellt die Listen mit den Web Element Klassen
     * @throws SearchApiExecption
     */
    private void makeClassLists() throws SearchApiExecption {
        this.titleClassList = (getList(this.titleClass));
        this.linkClassList = (getList(this.linkClass));
        this.descriptionClassList = (getList(this.descriptionClass));
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
        int anz = createAnzResultObjects();
        for (int i = 0; i < anz;i++) {
            this.resultList.add(new Result(
                    this.titleClassList.get(i).getText(),
                    this.descriptionClassList.get(i).getText(),
                    this.linkClassList.get(i).getText())
            );
        }

    }

    /**
     * Berechnet wie viele Result Objekte noch erstellt werden müssen, anhand wie viele ergebnisse
     * pro Seite es gibt.
     * @return anz Anzahl wie viele Ergebnisse es gibt.
     */
    private int createAnzResultObjects() {
        int anz;
        if (anzRestResults >= anzSiteResults) {
            anzRestResults = anzRestResults - anzSiteResults;
            anz = anzSiteResults;
        } else {
            anz = anzRestResults;
        }
        return anz;
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
            this.anzSiteResults = list.size();

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


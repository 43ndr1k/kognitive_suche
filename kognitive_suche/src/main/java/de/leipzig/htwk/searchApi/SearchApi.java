package de.leipzig.htwk.searchApi;

import de.leipzig.htwk.faroo.api.Result;
import de.leipzig.htwk.faroo.api.Results;
import javafx.application.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;


/**
 * @Autor Hendrik Sawade.
 */
public class SearchApi {

    /**
     * unitDriver lädt die Engerine für die Websuche.
     */
    private PhantomJSDriver driver;

    /**
     * Pfad zu Phantomjs
     */
    static String PHANTOMJS = "lib/phantomjs";


    /**
     * Beinhaltet die alle Results als Result_ Objekte.
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
    int gesamtAnzahlErgebnisse;

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
     * No Results Class
     */
    String noresultclass;

    /**
     * SetUp für den Phantomjs Driver.
     */
    private void driverSetUp() {
        //Create instance of PhantomJS driver
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,PHANTOMJS);
        driver = new PhantomJSDriver(capabilities);
    }

    /**
     *
     * @param url
     * @param nextButton
     * @param titleClass
     * @param urlKlasse
     * @param snippetKlasse
     * @param noResultClass
     * @param gesamtAnzahlErgebnisse
     */

    public SearchApi(String url, String nextButton, String titleClass, String urlKlasse,
                     String snippetKlasse, String noResultClass, int gesamtAnzahlErgebnisse) {

        this.url = url;
        this.nextButton = nextButton;
        this.titleClass = titleClass;
        this.linkClass = urlKlasse;
        this.descriptionClass = snippetKlasse;
        this.noresultclass = noResultClass;
        this.gesamtAnzahlErgebnisse = gesamtAnzahlErgebnisse;
        this.anzRestResults = gesamtAnzahlErgebnisse;
        driverSetUp();
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
    public void query(String query) throws SearchApiExecption {
        query = encoding(query);
        searching(this.url + query);

    }

    /**
     * Kappselt den Suchvorgang.
     * @param completeUrl String zur Suchmaschine, einschließlich des Suchwortes
     * @return
     */
    private void searching(String completeUrl) throws SearchApiExecption {

        try {
            driver.get(completeUrl);
        } catch (WebDriverException ioe) {
            ioe.printStackTrace();

            Platform.exit();
            throw new SearchApiExecption("Keine Internetverbindung möglich");
        }

        try {
            while (resultList.size() < gesamtAnzahlErgebnisse) {
                List<WebElement> noResults = getList(this.noresultclass);
                if(noResults.size() == 0 && anzRestResults != 0) {
                    makeClassLists();
                    makeResultList();
                    if (resultList.size() < gesamtAnzahlErgebnisse) {
                        moreResults();
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SearchApiExecption("Beim Erstellen der Ergebnisliste ist ein Fehler aufgetreten");
        }
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
            WebElement next = driver.findElement(By.className(this.nextButton));
            next.click();
        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new SearchApiExecption("Fehler bei klicken des next Buttons");
        }
    }


    /**
     * Gibt eine Result_ Liste zurück,mit den Result_ Objekt.
     * @return resultList Beinhaltet eine List mit Result_ Objekten.
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
     * Berechnet wie viele Result_ Objekte noch erstellt werden müssen, anhand wie viele ergebnisse
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

            list = driver.findElements(By.className(className));
            if (list.size() != 0) {
                this.anzSiteResults = list.size();
            } else if(className == noresultclass && list.size() != 0) {
                anzRestResults = 0;
            }

        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new SearchApiExecption("Bei dem Suchen von Ergebnissen, in den Tags, ist ein Fehler aufgetreten.");
        }
        return list;
    }

    public Results getResultList() {
        Results results = new Results();
        results.setResults(resultList);
        return results;
    }



}


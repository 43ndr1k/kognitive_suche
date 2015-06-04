package de.leipzig.htwk.searchApi;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Autor Hendrik Sawade.
 */


/**
 * DuckDuckGo Api
 * Durchsucht die Suchmaschine DuckDuckGo.
 * Bei DuckDuckGo sind maximal 80 Ergebnisse zu erwarten.
 */
public class DuckDuckGoSearchApi extends SearchApi {

    private static String URL = "https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=";
    private static String NEXTBUTTON = "navbutton";
    private static String TITLECLASS = "large";
    private static String LINKCLASS = "large";
    private static String DESCRIPTIONCLASS = "snippet";
    private static String NORESULTSCLASS = "no-results";

    /**
     * Dieser Constructor erstellt die SearchApi. Übergibt die folgenden Parameter an den Constructor
     * der SeachApi Klasse.
     * @param query Suchwort
     * @param anzResultCount Wie viele Ergebnisse möchte man haben
     * @param driver Phantomjs Driver
     * @throws SearchApiExecption
     */
    public DuckDuckGoSearchApi(String query, int anzResultCount, PhantomJSDriver driver) throws SearchApiExecption {

        super(URL, NEXTBUTTON, TITLECLASS, LINKCLASS, DESCRIPTIONCLASS, NORESULTSCLASS,anzResultCount, driver);
        query(query);
    }


    /**
     * Überschreibt die Methode in der SearchApi Klasse. Dies ist notwendig da es änderungen bei der
     * DuckDuckGo Suche gibt.
     * Diese Methode erstellt die title, link und description listen. Die Listen dienen später
     * für die Erstellung der Result Liste.
     * @throws SearchApiExecption
     */
    @Override
    protected void makeClassLists() throws SearchApiExecption {
        this.titleClassList = (getList(this.titleClass));
        this.linkClassList = titleClassList;
        this.descriptionClassList = (getList(this.descriptionClass));
        super.makeClassLists();
    }
}

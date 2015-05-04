package de.leipzig.htwk.searchApi;

import java.net.MalformedURLException;

/**
 * @Autor Hendrik Sawade.
 */
public class DuckDuckGoSearchApi extends SearchApi {

    private static String URL = "https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=";
    private static String NEXTBUTTON = "navbutton";
    private static String TITLECLASS = "large";
    private static String LINKCLASS = "url";
    private static String DESCRIPTIONCLASS = "snippet";



    public DuckDuckGoSearchApi(String query, int anzResultCount) throws SearchApiExecption, MalformedURLException {

        super(URL, NEXTBUTTON, TITLECLASS, LINKCLASS, DESCRIPTIONCLASS );
            setAnzResultSize(anzResultCount);
        this.resultList = query(query);
    }

    //TODO Zählt nicht richtig, berechnung anpassen. Mit Mod, Resultslist soschreiben das es genau die anforderte ergebnis zahl kommt get rest methode.
    @Override
    public void setAnzResultSize(int countResult) {
        countResult = (int) Math.ceil(countResult / 30.0);
        super.setAnzResultSize(countResult);
    }
}

package de.leipzig.htwk.searchApi;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @Autor Hendrik Sawade.
 */
public class DuckDuckGoSearchApi extends SearchApi {

    private static String URL = "https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=";
    private static String NEXTBUTTON = "navbutton";
    private static String TITLECLASS = "large";
    private static String LINKCLASS = "url";
    private static String DESCRIPTIONCLASS = "snippet";
    //private static double anzSiteResults = 30.0;
    private static String NORESULTSCLASS = "no-results";

    public DuckDuckGoSearchApi(String query, int anzResultCount, PhantomJSDriver driver) throws SearchApiExecption {

        super(URL, NEXTBUTTON, TITLECLASS, LINKCLASS, DESCRIPTIONCLASS, NORESULTSCLASS,anzResultCount, driver);
        query(query);
    }



}

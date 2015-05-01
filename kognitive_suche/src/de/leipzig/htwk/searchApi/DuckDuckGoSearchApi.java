package de.leipzig.htwk.searchApi;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * @Autor Hendrik Sawade.
 */
public class DuckDuckGoSearchApi extends SearchApi {



    ArrayList<Result> resultList;

    public DuckDuckGoSearchApi(String query, int anzResultCount) throws SearchApiExecption, MalformedURLException {
        setURL("https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1");
        setNextButton("navbutton");
        setCountResult(anzResultCount);
        setLargeKasse("large");
        setUrlKlasse("url");
        setSnippetKlasse("snippet");
        this.resultList = query(query);
    }

    public SearchResults getDuckDuckGoResults() {
        SearchResults results = new SearchResults();
        results.setResults(resultList);
        return results;
    }

    //TODO Zählt nicht richtig, berechnung anpassen.
    @Override
    public void setCountResult(int countResult) {
        countResult = (int) Math.ceil(countResult / 30.0);
        super.setCountResult(countResult);
    }
}

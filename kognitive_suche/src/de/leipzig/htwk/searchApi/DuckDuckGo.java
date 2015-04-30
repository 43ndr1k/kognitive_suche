package de.leipzig.htwk.searchApi;

import java.util.ArrayList;

/**
 * Created by hendrik on 29.04.15.
 */
public class DuckDuckGo extends SearchApi {



    ArrayList<Result> resultList;

    public DuckDuckGo(String query, int anzResultCount) throws SearchApiExecption {
        setURL("https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1");
        setSearchButton("navbutton");
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


}

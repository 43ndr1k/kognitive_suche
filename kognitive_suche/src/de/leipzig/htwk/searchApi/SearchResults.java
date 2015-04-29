package de.leipzig.htwk.searchApi;

import java.util.ArrayList;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Bereitstellung der Suchergebnisse.
 */
public class SearchResults {
    private ArrayList<Result> results;

    /**
     * Gibt die Result Liste zurück
     * @return results
     */
    public ArrayList<Result> getResults() {
        return results;
    }

    /**
     * Nimmt die Result Liste entgegen.
     * @param results
     */
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}

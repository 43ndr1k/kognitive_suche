package de.leipzig.htwk.searchApi;

import java.util.ArrayList;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Bereitstellung der Suchergebnisse.
 */
public class SearchResults {
    private ArrayList<Result_> results;

    /**
     * Gibt die Result_ Liste zurück
     * @return results
     */
    public ArrayList<Result_> getResults() {
        return results;
    }

    /**
     * Nimmt die Result_ Liste entgegen.
     * @param results
     */
    public void setResults(ArrayList<Result_> results) {
        this.results = results;
    }
}

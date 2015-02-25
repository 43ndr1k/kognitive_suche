package kognitive_suche.src.de.leipzig.htwk.faroo.api;

import java.util.ArrayList;

/**
 * Created by Hendrik
 */
public class Results {
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

package de.leipzig.htwk.faroo.api;

import java.util.ArrayList;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Bereitstellung der Suchergebnisse.
 */
public class Results {
  private ArrayList<Result> results;

  /**
   * Gibt die Result_ Liste zurück
   * 
   * @return results
   */
  public ArrayList<Result> getResults() {
    return results;
  }

  /**
   * Nimmt die Result_ Liste entgegen.
   * 
   * @param results
   */
  public void setResults(ArrayList<Result> results) {
    this.results = results;
  }
}

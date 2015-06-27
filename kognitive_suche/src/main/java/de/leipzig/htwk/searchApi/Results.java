package de.leipzig.htwk.searchApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Bereitstellung der Suchergebnisse.
 */
public class Results {
  private List<Result> results;

  /**
   * Gibt die Result_ Liste zurück
   * 
   * @return results
   */
  public List<Result> getResults() {
    return results;
  }

  /**
   * Nimmt die Result_ Liste entgegen.
   * 
   * @param results
   */
  public void setResults(List<Result> results) {
    this.results = results;
  }
}

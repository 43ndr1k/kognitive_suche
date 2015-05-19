package de.leipzig.htwk.websearch;

import de.leipzig.htwk.searchApi.Results;

public class ThreadRun {

  /**
   * Dient zum Ausführen der WebSearchThreads, um paralleles verbinden mit den URLS zu ermöglichen
   * 
   * @author Franz Schwarzer
   * 
   * 
   * 
   * @param anzahlErgebnisse Hier wird angegeben, wieviele URLs bei der Suche gefunden wurden. Noch
   *        inkomplett, da FarooAPI ja nicht mehr genutzt wird
   * @param wst Ist ein Array, welches die Threads umfasst.
   * 
   */

  int anzahlErgebnisse; // wird bei verwendung der neuen SuchAPI entsprechend
  // ersetzt

  WebSearchThread[] wst;

  public ThreadRun(Results results, String searchword, int anzahlErgebnisse) {

    this.anzahlErgebnisse = anzahlErgebnisse;
    Static.pageText = new String[anzahlErgebnisse];
    wst = new WebSearchThread[anzahlErgebnisse];

    for (int i = 0; i < results.getResults().size(); i++) {
      wst[i] = new WebSearchThread(results.getResults().get(i).getUrl(), i);
      wst[i].start();

    }

    while (ready() == false) {
    }

  }

  public boolean ready() {
    /**
     * Prüft ob alle Threads fertig abgearbeitet sind
     */

    for (int i = 0; i < anzahlErgebnisse; i++) {
      if (wst[i].isAlive() == true) {
        return false;
      }
    }
    System.out.println("fertig");
    return true;

  }

}

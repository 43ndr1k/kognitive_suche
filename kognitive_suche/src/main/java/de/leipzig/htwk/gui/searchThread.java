package de.leipzig.htwk.gui;

import javafx.application.Platform;
import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.searchApi.Results;
import de.leipzig.htwk.searchApi.SearchApiExecption;

/**
 * Wird benötigt um die Suche in einem eigenen Thread zu starten
 * 
 * @author Tobias Lenz
 *
 */

@SuppressWarnings("restriction")
public class searchThread extends Thread {

  private Controller mController;
  private int searchEnigne;
  Callback c;
  private int startMode;

  public searchThread(Callback cgui, Controller mController, int startMode) {
    this.c = cgui;
    this.mController = mController;
    this.startMode = startMode;
  }

  @Override
  public void run() {

/**
 * Die Suche wird über querySearchEngine gestartet.
 * Danach wird ein Callback an die GUI über einen neuen Thread ausgeführt
 */
    try {
      if( startMode == 0){
         mController.querySearchEngine(searchEnigne);
      }
      else if(startMode == 1){
        mController.startPDFSearch();
      }
     
    } catch (SearchApiExecption e) {
      e.printStackTrace();
    }
    /**
     * Der Callback muss in einem JavaFX-Thread gestartet werden...
     */
    Platform.runLater(new Runnable() {
      @Override
      public void run() {        
        c.callback();
      }
    });


  }

  public void setSearchEngine(int searchEngine) {
    this.searchEnigne = searchEngine;
  }

}

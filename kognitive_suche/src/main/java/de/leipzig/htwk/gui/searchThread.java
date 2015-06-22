package de.leipzig.htwk.gui;

import javafx.application.Platform;

import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.searchApi.Results;
import de.leipzig.htwk.searchApi.SearchApiExecption;

public class searchThread extends Thread {

  private Controller mController;
  private int searchEnigne;
  Results results;
  ReturnTagList tags;
  GUI c;

  public searchThread(GUI gui, Controller mController) {
    this.c = gui;
    this.mController = mController;
  }

  @SuppressWarnings("restriction")
  @Override
  public void run() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {

        try {
          mController.querySearchEngine(searchEnigne);
        } catch (SearchApiExecption e) {
          e.printStackTrace();
        }
        results = mController.getResultList();
        tags = mController.getTags();
        c.callback();
      }
    });

  }

  public void setSearchEngine(int searchEngine) {
    this.searchEnigne = searchEngine;
  }

}

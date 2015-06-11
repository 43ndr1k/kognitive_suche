package main;

import de.leipzig.htwk.gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Die Main :D
 * @author Sebastian Hügelmann
 */
public class Main extends Application{
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage arg0) throws Exception {
    GUI.getInstance();
  }
}

package de.leipzig.htwk.visualize;


import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.searchApi.Results;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


/**
 * Controller der Visualisierung
 * 
 * @author Fabian Freihube
 */
public class VisController {

  // Tobi am start

  private static int paneWidth;
  private static int paneHeight;
  private Results results;
  private static int activePads;
  private static Pane pane;
  private static String query;
  private GUI gui;
  private static Pattern pattern;
  
  public VisController() {

  }



  public Results getResults() {
	return results;
}


  public void setResults(Results results) {
	this.results = results;
}


public int getPaneWidth() {
    return paneWidth;
  }

  public void setPaneWidth(int paneWidth) {
    VisController.paneWidth = paneWidth;
  }

  public int getPaneHeight() {
    return paneHeight;
  }

  public void setPaneHeight(int paneHeight) {
    VisController.paneHeight = paneHeight;
  }

  /**
   * Aufruf der Visualisierung
   * 
   * @param tags Tag Objekt
   * @return Pane mit positionierten Objekten
   */
  public Pane startVisualize(ReturnTagList tags, int navMode) {
    pattern = new Pattern(paneHeight, paneWidth, query, tags, this.gui, results, navMode);

    return pattern.getPane();
  }


    /**
     * Methode zur Übergabe der GUI an den Controller.
     *
     * @author Sebastian Hügelmann
     */
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

  /**
   * 
   * @author Ivan Ivanikov
   */

  public static void setPane(Pane pane) {
    VisController.pane = pane;
  }

  public static void setQuery(String query) {
    VisController.query = query;
  }
  
  public static Pattern getPattern(){
	  return pattern;
  }




public Parent getPane() {
	return pane;
	}




public void updatePattern(ReturnTagList tags) {
	pattern.update(tags);
	((BorderPane) (pane)).setCenter(pattern.getPane());

}
}

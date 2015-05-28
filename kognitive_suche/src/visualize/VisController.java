package visualize;


import cognitive.search.ReturnTagList;
import de.leipzig.htwk.faroo.api.Results;
import gui.GUI;
import javafx.scene.layout.Pane;

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

  public VisController(Results results) {
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
  public Pane startVisualize(ReturnTagList tags) {
    Pattern pattern = new Pattern(paneHeight, paneWidth, query, tags, this.gui, results);

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
}

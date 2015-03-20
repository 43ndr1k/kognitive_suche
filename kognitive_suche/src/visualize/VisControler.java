package visualize;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import komplexeSuche.TagObjectList;

/**
 * @author Fabian Freihube
 */
public class VisControler {
	
	// Tobi am start
	
	private static int paneWidth;
	private static int paneHeight;
	private TagObjectList tags;
	
	private static int activePads;
	private static Pane pane;
	private static String query;

	public int getPaneWidth() {
		return paneWidth;
	}

	public void setPaneWidth(int paneWidth) {
		VisControler.paneWidth = paneWidth;
		
	}



	public int getPaneHeight() {
		return paneHeight;
	}



	public void setPaneHeight(int paneHeight) {
		VisControler.paneHeight = paneHeight;
	}





	public Pane startVisualize (TagObjectList tags)  {
		  Pattern pattern = new Pattern(paneHeight, paneWidth, query, tags);
		  
		  return pattern.getPane();
	  }
	
	/**
	 * 
	 * @author Ivan Ivanikov
	 */
	public static void setPane(Pane pane){
		VisControler.pane = pane;
	}
	
	public static void setQuery(String query){
		VisControler.query = query;
	}
}

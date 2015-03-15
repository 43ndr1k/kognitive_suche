package visualize;


import javafx.scene.layout.Pane;
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
		  Pattern pattern = new Pattern(paneHeight, paneWidth, tags);
		  return pattern.getPane();
	  }
}

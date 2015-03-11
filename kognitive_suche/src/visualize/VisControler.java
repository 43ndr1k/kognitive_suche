package visualize;

import javafx.application.Application;
import javafx.scene.Scene;

public class VisControler {
	  public static Scene startVisualize ()  {
		  Pattern pattern = new Pattern(5);
		  return pattern.getScene();
	  }
}

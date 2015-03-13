package visualize;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * @author Fabian Freihube
 */
public class Pattern {
	
	private int paneWidth;
	private int paneHeight;
	private static final double padSize = 102;
	private static final double padOffset = 3;
	
	private static final Color lightGreen = Color.web("#9FDA9F");
	private static final Color orange = Color.web("#FFC63E");
	private static final Color lightBlue = Color.web("#5bc9ff");
	private static final Color lightRed = Color.web("#ff9595");
	private static final Color lightPurple = Color.web("#c395ff");
	private static final Color lightGrey = Color.web("#e5e5e5");
	
	private int activePads;
	
	private static Pane visPane;
	
	public Pattern (int activePads, int paneHeight, int paneWidth)  {
		// TODO Auto-generated method stub
	        this.activePads = activePads;
	        this.paneHeight = paneHeight;
	        this.paneWidth = paneWidth;
		    
		    visPane = new Pane();
		    visPane.setPrefSize(paneHeight,paneWidth);
		    
		    double oneHexHeight = getHexHeight();
		    double oneHexWidth = getHexWidth();
		    double columnCorrection = getColumnCorrection(oneHexHeight);
		    
		    //Berechnung der Zeilen und Spaltenanzahl in Abh�ngigkeit von Fenstergr��e und PadSize
		    int rows = getRows(oneHexHeight);
		    int columns = getColumns(oneHexWidth, columnCorrection);
		    
		    System.out.println("Rows:" + rows + " Columns:" + columns + " ActivePads:" + activePads);
		    
		    Boolean[][] padMap = createPadMap(rows, columns);
		    visPane = printPattern(padMap, oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane);
		    
		}

	private int getColumns(double oneHexWidth, double columnCorrection) {
		return (int) (Math.round((((paneWidth/(oneHexWidth-columnCorrection+padOffset))+0.5)+0.5)*1)/1.0);
	}

	private int getRows(double oneHexHeight) {
		return (int) (Math.round((((paneHeight/(oneHexHeight+padOffset))+0.5)+0.5)*1)/1.0);
	}

	private double getColumnCorrection(double oneHexHeight) {
		return Math.tan(Math.toRadians(30)) * 0.5 * oneHexHeight;
	}

	private double getHexWidth() {
		return 2*padSize;
	}

	private double getHexHeight() {
		return 2*((0.5*padSize)/Math.tan(Math.toRadians(30)));
	}

	private Pane printPattern(Boolean[][] padMap, double oneHexWidth, double columnCorection, double oneHexHeight, int rows, int columns, Pane visPane) {
		
			for(int y = 0; y < rows; y++)
			{
				for(int x = 0; x < columns; x++)
				{
					if((x % 2) == 0)
					{
						if(padMap[x][y] == true)
							visPane = addColorPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, visPane, (x-0.5), (y-0.25));
						else 
							visPane = addGreyPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, visPane, (x-0.5), (y-0.25));
					} else {
						if(padMap[x][y] == true)
							visPane = addColorPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, visPane, (x-0.5), (y-0.75));
						else 
							visPane = addGreyPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, visPane, (x-0.5), (y-0.75));
					}
				}
			}
		    
		return visPane;
	}

	private Pane addGreyPad(double oneHexWidth, double columnCorection,
			double oneHexHeight, int rows, int columns, Pane visPane, double x, double y) {
		Pad pad;
		StackPane padPane = new StackPane();
		
		double xPos = (oneHexWidth-columnCorection+padOffset)*(x);
		double yPos = (oneHexHeight+padOffset)*(y);
		
		padPane.setLayoutX(xPos);
		padPane.setLayoutY(yPos);
		
		pad = new Pad (padSize, 
				0,
				0,
				lightGrey); 
		
		padPane.getChildren().add(pad.getShape());
		padPane.getChildren().add(pad.getLightShape());
		visPane.getChildren().add(padPane);
		
		return visPane;
	}

	private Pane addColorPad(double oneHexWidth, double columnCorection, 
			double oneHexHeight, int rows, int columns, Pane visPane, double x, double y) {
		Pad pad = null;
		StackPane padPane = new StackPane();
		StackPane exPadPane = new StackPane();
		Pane linkPane = genLinkPane();
		Label smallTopicLabel = new Label("Beispiel Knoten");
		Label largeTopicLabel = new Label("Beispiel Knoten");
		
		smallTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		largeTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

		double xPos = (oneHexWidth-columnCorection+padOffset)*(x);
		double yPos = (oneHexHeight+padOffset)*(y);
		
		int random =  (int) (Math.random() * 5);
		
		switch(random)
    	{
    		case 0: pad = new Pad (padSize, 
    				0, 
    				0, 
    				lightGreen); 
    		break;
    		
    		case 1: pad = new Pad (padSize, 
    				0, 
    				0, 
    				orange);
    		break;
    		case 2: pad = new Pad (padSize, 
    				0, 
    				0, 
    				lightBlue);
    		break;
    		case 3: pad = new Pad (padSize, 
    				0, 
    				0, 
    				lightRed);
    		break;
    		
    		case 4: pad = new Pad (padSize, 
    				0, 
    				0, 
    				lightPurple);
    		break;
    		
    	}		
		
		padPane.setLayoutX(xPos);
		padPane.setLayoutY(yPos);
		
		exPadPane.setLayoutX(xPos-(padSize*2.2-padSize));
		exPadPane.setLayoutY(yPos-(0.5*(oneHexHeight+padOffset)*2.2-0.5*(oneHexHeight+padOffset)));
		
		padPane.getChildren().add(pad.getShape());
		padPane.getChildren().add(pad.getLightShape());
		padPane.getChildren().add(smallTopicLabel);
		exPadPane.getChildren().add(pad.getExShape());
		exPadPane.getChildren().add(pad.getExLightShape());
		exPadPane.getChildren().add(linkPane);
		exPadPane.getChildren().add(largeTopicLabel);
		exPadPane.setVisible(false);
		
		pad.getExLightShape().toFront();
		
		visPane.getChildren().add(padPane);
		visPane.getChildren().add(exPadPane);
		
		pad.getLightShape().setOnMouseEntered(event -> {
			exPadPane.setVisible(true);
			exPadPane.toFront();
		});
    
		pad.getExLightShape().setOnMouseExited(event -> {
			exPadPane.setVisible(false);
		});
		
		return visPane;
	}

	private Pane genLinkPane() {
		Pane linkPane = new Pane();
		Label tagLabel1 = new Label("Tag1");
		
		//linkPane.getChildren().add(topicLabel);
		
		return linkPane;
	}

	private Boolean[][] createPadMap(int rows, int columns) {
		// TODO Auto-generated method stub
		Boolean[][] padMap = new Boolean[columns][rows];
		
		int insertRow = rows/2;
		int insertColumn = columns/2;
		
		for(int x = 0; x < columns; x++)
			for(int y = 0; y < rows; y++)
				padMap[x][y] = false;
		
		for (int i = 0; i < activePads+1; i++)
		{
			switch(i)
			{
				case 1: padMap[insertColumn-1][insertRow] = true; break;
				case 2: padMap[insertColumn][insertRow] = true; break;
				case 3: padMap[insertColumn-2][insertRow] = true; break;
				case 4: padMap[insertColumn][insertRow-1] = true; break;
				case 5: padMap[insertColumn-2][insertRow-1] = true; break;
				case 6: padMap[insertColumn-1][insertRow+1] = true; break;
				case 7: padMap[insertColumn-1][insertRow-1] = true; break;
				case 8: padMap[insertColumn-3][insertRow] = true; break;
				case 9: padMap[insertColumn+1][insertRow] = true; break;
			}
		}
		
		return padMap;
	}

  public Pane getPane() {
    return visPane;
    // TODO Auto-generated method stub  
  }


}

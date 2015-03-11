package visualize;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Pattern {
	
	private static final double wWidth = 1024;
	private static final double wHeight = 768;
	private static final double padSize = 100;
	private static final double padOffset = 3;
	
	private static final Color lightGreen = Color.web("#9FDA9F");
	private static final Color orange = Color.web("#FFC63E");
	private static final Color lightBlue = Color.web("#5bc9ff");
	private static final Color lightRed = Color.web("#ff9595");
	private static final Color lightPurple = Color.web("#c395ff");
	private static final Color lightGrey = Color.web("#e5e5e5");
	
	private int activePads;
	
	private static Scene scene;
	
	public Pattern (int activePads)  {
		// TODO Auto-generated method stub
	        this.activePads = activePads;
	  
			Group root = new Group();
		    scene = new Scene(root, wWidth, wHeight);
		    
		    Group g = new Group();
		    
		    double oneHexHeight = getHexHeight();
		    double oneHexWidth = getHexWidth();
		    double columnCorrection = getColumnCorrection(oneHexHeight);
		    
		    //Berechnung der Zeilen und Spaltenanzahl in Abh�ngigkeit von Fenstergr��e und PadSize
		    int rows = getRows(oneHexHeight);
		    int columns = getColumns(oneHexWidth, columnCorrection);
		    
		    System.out.println("Rows:" + rows + " Columns:" + columns + " ActivePads:" + activePads);
		    
		    Boolean[][] padMap = createPadMap(rows, columns);
		    g = printPattern(padMap, oneHexWidth, columnCorrection, oneHexHeight, rows, columns, g);
		    
			scene.setRoot(g);
		}

	private int getColumns(double oneHexWidth, double columnCorrection) {
		return (int) (Math.round((((wWidth/(oneHexWidth-columnCorrection+padOffset))+0.5)+0.5)*1)/1.0);
	}

	private int getRows(double oneHexHeight) {
		return (int) (Math.round((((wHeight/(oneHexHeight+padOffset))+0.5)+0.5)*1)/1.0);
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

	private Group printPattern(Boolean[][] padMap, double oneHexWidth, double columnCorection, double oneHexHeight, int rows, int columns, Group g) {
		
			for(int y = 0; y < rows; y++)
			{
				for(int x = 0; x < columns; x++)
				{
					if((x % 2) == 0)
					{
						if(padMap[x][y] == true)
							g = addColorPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, g, x, (y+0.5));
						else 
							g = addGreyPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, g, x, (y+0.5));
					} else {
						if(padMap[x][y] == true)
							g = addColorPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, g, x, y);
						else 
							g = addGreyPad(oneHexWidth, columnCorection, oneHexHeight, rows, columns, g, x, y);
					}
				}
			}
		    
		return g;
	}

	private Group addGreyPad(double oneHexWidth, double columnCorection,
			double oneHexHeight, int rows, int columns, Group g, double x, double y) {
		Pad pad;
		
		pad = new Pad (padSize, 
				(oneHexWidth-columnCorection+padOffset)*(x), 
				(oneHexHeight+padOffset)*(y),
				lightGrey); 
		
		return setPad(pad, g, false);
	}

	private Group addColorPad(double oneHexWidth, double columnCorection, 
			double oneHexHeight, int rows, int columns, Group g, double x, double y) {
		Pad pad = null;
		int random =  (int) (Math.random() * 5);
		
		switch(random)
    	{
    		case 0: pad = new Pad (padSize, 
    				(oneHexWidth-columnCorection+padOffset)*x, 
    				(oneHexHeight+padOffset)*y, 
    				lightGreen); 
    		break;
    		
    		case 1: pad = new Pad (padSize, 
    				(oneHexWidth-columnCorection+padOffset)*x, 
    				(oneHexHeight+padOffset)*y, 
    				orange);
    		break;
    		case 2: pad = new Pad (padSize, 
    				(oneHexWidth-columnCorection+padOffset)*x, 
    				(oneHexHeight+padOffset)*y, 
    				lightBlue);
    		break;
    		case 3: pad = new Pad (padSize, 
    				(oneHexWidth-columnCorection+padOffset)*x, 
    				(oneHexHeight+padOffset)*y, 
    				lightRed);
    		break;
    		
    		case 4: pad = new Pad (padSize, 
    				(oneHexWidth-columnCorection+padOffset)*x, 
    				(oneHexHeight+padOffset)*y, 
    				lightPurple);
    		break;
    		
    	}		
		
		return setPad(pad, g, true);
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

	private Group setPad(Pad pad, Group g, boolean expandable) {
		
		
		g.getChildren().add(pad.getShape());
	    g.getChildren().add(pad.getLightShape());
	    
	    if(expandable)
	    {
		    g.getChildren().add(pad.getExLightShape());
		    g.getChildren().add(pad.getExShape());
		    g.getChildren().add(pad.getLink());
		    
			pad.getExLightShape().toFront();

	    }
		
		return g;
	}

  public Scene getScene() {
    return scene;
    // TODO Auto-generated method stub  
  }


}

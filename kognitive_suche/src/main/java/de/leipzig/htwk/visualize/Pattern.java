package de.leipzig.htwk.visualize;

import java.util.ArrayList;

import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.list.Listenausgabe;
import de.leipzig.htwk.searchApi.Results;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Generierung des Feldes von Hexagons
 * 
 * @author Fabian Freihube
 */
public class Pattern {

  private int paneWidth;
  private int paneHeight;
  private int stepCorrection = 0;
  private int selectedPad = 10;
  private int navMode = 0;
  private Results results;
  private ArrayList<Pad> savedPads = new ArrayList<Pad>();
  private static final double PAD_SIZE = 102;
  private static final double PAD_OFFSET = 3;

  private static final Color COLOR_LIGHTGREEN = Color.web("#9FDA9F");
  private static final Color COLOR_ORANGE = Color.web("#FFC63E");
  private static final Color COLOR_LIGHTBLUE = Color.web("#5bc9ff");
  private static final Color COLOR_RED = Color.web("#ff9595");
  private static final Color COLOR_LIGHTPURPLE = Color.web("#c395ff");
  private static final Color COLOR_LIGHTGREY = Color.web("#e5e5e5");
  private static final Color COLOR_SUPERLIGHTGREY = Color.web("#f2f2f2");

  private Color[] colors = {COLOR_LIGHTGREEN, COLOR_ORANGE, COLOR_LIGHTBLUE, COLOR_RED,
      COLOR_LIGHTPURPLE, COLOR_LIGHTGREY};

  private int activePads;

  private final int MAX_TAGS = 7;

  private static Pane visPane;

  private static ReturnTagList tags;
  
  private Pane cuted;
  
  private int[][] padMap;


  /**
   * @author Sebastian Hügelmann
   */
  private GUI gui;

  /**
   * Generiert das Feld von Hexagons
   * 
   * @param paneHeight Höhe des Feldes
   * @param paneWidth Breite des Feldes
   * @param query
   * @param tags
   * @param gui
   * @param navMode Gibt an ob es ein Button nach vorne und hinten geben soll
   */
  public Pattern(int paneHeight, int paneWidth, String query, ReturnTagList tags, GUI gui,
      Results results, int navMode) {
    // Auto-generated method stub
    this.paneHeight = paneHeight;
    this.paneWidth = paneWidth;
    this.tags = tags;
    if (tags.getSize() < MAX_TAGS) {
      this.activePads = tags.getSize();
    } else {
      this.activePads = 7;
    }

    this.gui = gui;
    this.results = results;
    this.navMode = navMode;

    visPane = new Pane();// Pane Für Kacheln
    visPane.setPrefSize(this.paneHeight, this.paneWidth - 320);

    // BorderPane SearchList = new BorderPane();//Pane für Suchliste
    // SearchList.setPrefSize(paneHeight,paneWidth/2);

    double oneHexHeight = getHexHeight();
    double oneHexWidth = getHexWidth();
    double columnCorrection = getColumnCorrection(oneHexHeight);

    // Berechnung der Zeilen und Spaltenanzahl in Abhängigkeit von
    // Fenstergröße und PadSize
    int rows = getRows(oneHexHeight);
    int columns = getColumns(oneHexWidth, columnCorrection);

    System.out.println("Rows:" + rows + " Columns:" + columns + " ActivePads:" + activePads);

    padMap = createPadMap(rows, columns, columns/2, rows/2);
    visPane =
        printPattern( oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane);
    // iv

    // list.setOnAction(new EventHandler<ActionEvent>() {
    // @Override
    // public void handle(ActionEvent Liste) {
    // visPane.getChildren().clear();

    Listenausgabe ausgabe = new Listenausgabe(results);
    ausgabe.setWidth(320);
    ausgabe.setHeight(paneHeight);
    ausgabe.setLayoutX(paneWidth - 334);
    ausgabe.setLayoutY(0);
    /**
     * Listenausgabe an Patterngui weitergegeben
     */
    visPane.getChildren().addAll(ausgabe.ergebnisausgabe());
    visPane.setStyle("-fx-background-color:#FFF;");
    
	cuted = new Pane();
	cuted.getChildren().add(visPane);

    // }});

    // visPane.getChildren().addAll(list);
  }

  public void addTag(double x, double y, String tag) {
    x += .0; // sinvoller code
    y += .25;

    if (x % 2 != 0) {
      y -= .25;
    }

    Pane pad =
        addColorPad(getHexWidth(), getColumnCorrection(getHexHeight()), getHexHeight(),
            getRows(getHexHeight()),
            getColumns(getHexWidth(), getColumnCorrection(getHexHeight())), visPane, x, y, tag, 0, 0);
  }

  /**
   * Berechnet die Anzahl der Spalten in Abhängikeit von der Größe der Hexagons und des Feldes.
   * 
   * @param oneHexWidth Breite eines Hexagons
   * @param columnCorrection Versatz zwischen den Hexagons in der Breite
   * @return Anzahl der Spalten
   */
  private int getColumns(double oneHexWidth, double columnCorrection) {
    return (int) (Math
        .round((((paneWidth / (oneHexWidth - columnCorrection + PAD_OFFSET)) + 0.5) + 0.5) * 1) / 1.0);
  }

  /**
   * Berechnet die Anzahl der Reihen in Abhängikeit von der Größe der Hexagons und des Feldes.
   * 
   * @param oneHexHeight Höhe eines Hexagons
   * @return Anzahl der Reihen
   */
  private int getRows(double oneHexHeight) {
    return (int) (Math.round((((paneHeight / (oneHexHeight + PAD_OFFSET)) + 0.5) + 0.5) * 1) / 1.0);
  }

  /**
   * @param oneHexHeight Höhe eines Hexagons
   * @return Versatz zwischen den Hexagons in der Breite
   */
  private double getColumnCorrection(double oneHexHeight) {
    return Math.tan(Math.toRadians(30)) * 0.5 * oneHexHeight;
  }

  /**
   * @return Breite eines Hexagons
   */
  private double getHexWidth() {
    return 2 * PAD_SIZE;
  }

  /**
   * @return Höhe eines Hexagons
   */
  private double getHexHeight() {
    return 2 * ((0.5 * PAD_SIZE) / Math.tan(Math.toRadians(30)));
  }

  /**
   * Positionieren der Hexagons
   * 
   * @param padMap Array das anzeigt, ob dieses Feld aktiv ist.
   * @param oneHexWidth Breite eines Hexagons
   * @param columnCorrection Versatz zwischen den Hexagons in der Breite
   * @param oneHexHeight Höhe eines Hexagons
   * @param rows Anzahl der Reihen
   * @param columns Anzahl der Spalten
   * @param visPane Hauptpane auf der die Elemente positioniert werden.
   * @return visPane mit positionierten Elementen
   */
  private Pane printPattern( double oneHexWidth, double columnCorrection,
      double oneHexHeight, int rows, int columns, Pane visPane) {
    int numOfTags = tags.getSize();
    if (numOfTags > MAX_TAGS) {
      numOfTags = MAX_TAGS;
    }
    int rangeOfTags = numOfTags;

    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < columns; x++) {
        if ((x % 2) == 0) {
          switch (padMap[x][y]) {

            case 0: visPane =
                    addGreyPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns,
                    visPane,
                    (x - 0.5), (y - 0.25),
                    x, y);

              break;

            case 1:
              visPane =
                  addColorPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getTagObject(rangeOfTags - numOfTags).getTag(),
                      x, y);
              numOfTags--;
              break;

            case 2:
              visPane =
                  addMidPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword(),
                      x, y);
              break;

            case 3:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), 1,
                      x, y);
              break; // Navbutton zurück

            case 4:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), 2,
                      x, y);
              break; // Navbutton vor
              
            case 10:
            	visPane =
            			addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword(), 10,
                      x, y);
            break;
            
            case 11:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword(), 11,
                      x, y);
            break;
            
            case 12:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword(), 12,
                      x, y);
            break;
            
            case 13:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword(), 13,
                      x, y);
            break;
            
            case 14:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword(), 14,
                      x, y);
            break;

          }

        } else {
          switch (padMap[x][y]) {

            case 0: visPane =
                    addGreyPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns,
                    visPane,
                    (x - 0.5), (y - 0.75),
                    x, y);
              break;

            case 1:
              visPane =
                  addColorPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getTagObject(rangeOfTags - numOfTags).getTag(),
                      x, y);
              numOfTags--;
              break;

            case 2:
              visPane =
                  addMidPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword(),
                      x, y);
              break;

            case 3:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), 1,
                      x, y);
              break; // Navbutton zurück

            case 4:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), 2,
                      x, y);
              break; // Navbutton vor
              
            case 10:
            	visPane =
            			addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword(), 10,
                      x, y);
            break;
            
            case 11:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword(), 11,
                      x, y);
            break;
            
            case 12:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword(), 12,
                      x, y);
            break;
            
            case 13:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword(), 13,
                      x, y);
            break;
            
            case 14:
            	visPane =
                addChosenPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword(), 14,
                      x, y);
            break;

          }
        }
      }
    }

    return visPane;

  }

  private Pane addChosenPad(double oneHexWidth, double columnCorrection,
		double oneHexHeight, int rows, int columns, Pane visPane, double x,
		double y, String searchWord, int pos, int xInPadMap, int yInPadMap) {
	  
	  	Pad pad = null;
	    StackPane padPane = new StackPane();
	    
	    System.out.println("ChosePad added at: " + xInPadMap + " " + yInPadMap + " Nr: " + pos);
	    
	    String topicWord = searchWord.split(" ")[pos-9];

	    Label smallTopicLabel = new Label(topicWord);

	    smallTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

	    double xPos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
	    double yPos = (oneHexHeight + PAD_OFFSET) * (y);

	    pad = new Pad(PAD_SIZE, 0, 0, COLOR_LIGHTGREEN,
	            xInPadMap, yInPadMap);
	    padPane.setLayoutX(xPos);
	    padPane.setLayoutY(yPos);

	    padPane.getChildren().add(pad.getShape());
	    padPane.getChildren().add(smallTopicLabel);
	    padPane.getChildren().add(pad.getLightShape());

	    visPane.getChildren().add(padPane);

	return visPane;
}

private Pane addMidPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane2, double x, double y, String searchWord, int xInPadMap, int yInPadMap) {

    Pad pad = null;
    StackPane padPane = new StackPane();
    
    String topicWord = searchWord.split(" ")[0];

    Label smallTopicLabel = new Label(topicWord);

    smallTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

    double xPos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
    double yPos = (oneHexHeight + PAD_OFFSET) * (y);

    pad = new Pad(PAD_SIZE, 0, 0, Color.WHITE,
            xInPadMap, yInPadMap);
    padPane.setLayoutX(xPos);
    padPane.setLayoutY(yPos);

    padPane.getChildren().add(pad.getShape());
    padPane.getChildren().add(smallTopicLabel);
    padPane.getChildren().add(pad.getLightShape());

    visPane.getChildren().add(padPane);

    return visPane;
  }

  private Pane addNavPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane, double x, double y, int navButtonMode, int xInPadMap, int yInPadMap) {

    Pad pad = null;
    StackPane padPane = new StackPane();

    Label smallTopicLabel;

    if (navButtonMode == 1)
      smallTopicLabel = new Label("←");
    else
      smallTopicLabel = new Label("→");

    smallTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 60));

    double xPos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
    double yPos = (oneHexHeight + PAD_OFFSET) * (y);

    pad = new Pad(PAD_SIZE, 0, 0, COLOR_LIGHTGREY,
            xInPadMap, yInPadMap);
    padPane.setLayoutX(xPos);
    padPane.setLayoutY(yPos);

    padPane.getChildren().add(pad.getShape());
    padPane.getChildren().add(smallTopicLabel);
    padPane.getChildren().add(pad.getLightShape());

    visPane.getChildren().add(padPane);

    pad.getLightShape().setOnMouseEntered(
        event -> {
          ((Shape) ((StackPane) ((Shape) event.getTarget()).getParent()).getChildren().get(0))
              .setFill(COLOR_SUPERLIGHTGREY);
        });

    pad.getLightShape().setOnMouseExited(
        event -> {
          ((Shape) ((StackPane) ((Shape) event.getTarget()).getParent()).getChildren().get(0))
              .setFill(COLOR_LIGHTGREY);
        });

    if (navButtonMode == 1) {
        padPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            gui = GUI.getInstance();
            gui.controllBTPosition(-1);
          }
        });
      } else {
        padPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
          public void handle(MouseEvent event) {
            gui = GUI.getInstance();
            gui.controllBTPosition(+1);
          }
        });

      }

    return visPane;

  }

  /**
   * Fügt ein neues inaktives Hexagon hinzu.
   * 
   * @param oneHexWidth Breite eines Hexagons
   * @param columnCorrection Versatz zwischen den Hexagons in der Breite
   * @param oneHexHeight Höhe eines Hexagons
   * @param rows Anzahl der Reihen
   * @param columns Anzahl der Spalten
   * @param visPane Hauptpane auf der die Elemente positioniert werden.
   * @param x X-Position des Hexagons
   * @param y Y-Position des Hexagons
 * @param xInPadMap 
 * @param yInPadMap 
   * @return visPane mit neuem aktiven Hexagon
   */
  @SuppressWarnings({"restriction", "unused"})
  private Pane addGreyPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane, double x, double y, int xInPadMap, int yInPadMap) {
    Pad pad;
    StackPane padPane = new StackPane();

    double xpos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
    double ypos = (oneHexHeight + PAD_OFFSET) * (y);

    padPane.setLayoutX(xpos);
    padPane.setLayoutY(ypos);

    pad =
        new Pad(PAD_SIZE, (oneHexWidth - columnCorrection + PAD_OFFSET) * (x),
            (oneHexHeight + PAD_OFFSET) * (y), COLOR_LIGHTGREY,
            xInPadMap, yInPadMap);

    padPane.getChildren().add(pad.getShape());
    padPane.getChildren().add(pad.getLightShape());
    visPane.getChildren().add(padPane);

    return visPane;
  }

  /**
   * Fügt ein neues aktives Hexagon hinzu.
   * 
   * @param oneHexWidth Breite eines Hexagons
   * @param columnCorrection Versatz zwischen den Hexagons in der Breite
   * @param oneHexHeight Höhe eines Hexagons
   * @param rows Anzahl der Reihen
   * @param columns Anzahl der Spalten
   * @param visPane Hauptpane auf der die Elemente positioniert werden.
   * @param x X-Position des Hexagons
   * @param y Y-Position des Hexagons
   * @param labelText Text in der Mitte
 * @param xInPadMap 
 * @param yInPadMap 
   * @return visPane mit neuem aktiven Hexagon
   */
  private Pane addColorPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane, double x, double y, String labelText, int xInPadMap, int yInPadMap) {
    Pad pad = null;
    StackPane padPane = new StackPane();
    StackPane exPadPane = new StackPane();
    Pane linkPane = genLinkPane();

    /**
     * @author Sebastian Hügelmann
     */
    Label smallTopicLabel = new Label(labelText);
    Label largeTopicLabel = new Label(labelText);

    smallTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    largeTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

    double xPos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
    double yPos = (oneHexHeight + PAD_OFFSET) * (y);

    pad = new Pad(PAD_SIZE, 0, 0, COLOR_RED, xInPadMap, yInPadMap);
    padPane.setLayoutX(xPos);
    padPane.setLayoutY(yPos);

    exPadPane.setLayoutX(xPos - (PAD_SIZE * 2.2 - PAD_SIZE));
    exPadPane.setLayoutY(yPos
        - (0.5 * (oneHexHeight + PAD_OFFSET) * 2.2 - 0.5 * (oneHexHeight + PAD_OFFSET)));

    padPane.getChildren().add(pad.getShape());
    padPane.getChildren().add(pad.getLightShape());
    padPane.getChildren().add(smallTopicLabel);
    exPadPane.getChildren().add(pad.getExShape());
    exPadPane.getChildren().add(pad.getExLightShape());
    exPadPane.getChildren().add(linkPane);
    exPadPane.getChildren().add(largeTopicLabel);
    exPadPane.setVisible(false);

    pad.getExShape().setUserData(pad);
    pad.getExLightShape().setUserData(pad);
    
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

    /**
     * @author Sebastian Hügelmann
     */
    exPadPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        /*
         * addTag(x, y - 1, "TAG!"); Patternpositionen später zum einkommentieren addTag(x, y + 1,
         * "TAG!"); addTag(x + 1, y - .5, "TAG!"); addTag(x + 1, y + .5, "TAG!"); addTag(x - 1, y -
         * .5, "TAG!"); addTag(x - 1, y + .5, "TAG!");
         * 
         * addMidPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane, x, y,
         * labelText);
         */
    	  
    	  tags.setSearchWord(tags.getSearchword() + " " + largeTopicLabel.getText());
    	  gui.getInstance().setClickedTag(x,y, labelText, tags.getSearchword());
    	  
	      incSelectedPad();
    	  
    	  padMap[(int) ((Pad) ((Polygon) event.getTarget()).getUserData()).getX()]
    			[(int) ((Pad) ((Polygon) event.getTarget()).getUserData()).getY()] 
    					= selectedPad;
    	      	     	  
    	  gui.getInstance().setSuchleisteText(tags.getSearchword());
      }
    });

    return visPane;
  }

private void incSelectedPad() {
	  selectedPad++;
}

private Pane genLinkPane() {
    Pane linkPane = new Pane();
    Label tagLabel1 = new Label("Tag1");

    // linkPane.getChildren().add(topicLabel);

    return linkPane;
  }

  /**
   * Generiert aus der Anzahl der aktiven Pads und den Reihen und Spalten ein Array, welches
   * anzeigt, welche Felder aktiv sind und was in diesem Feld für ein Pad ist.
   * 
   * @param rows Anzahl der Reihen
   * @param columns Anzahl der Spalten
   * @return padMap
   */
  private int[][] createPadMap(int rows, int columns, int insertColumn, int insertRow) {
    // Auto-generated method stub
    padMap = new int[columns][rows];

    for (int x = 0; x < columns; x++)
      for (int y = 0; y < rows; y++)
        padMap[x][y] = 0;
    
    for (int i = 0; i < activePads + 1; i++) {
      switch (i) {
        case 1:
          //if(selectedPad != 10)	
        	//  padMap[insertColumn - 1 + stepX][insertRow + stepY] = selectedPad;
          break;
        case 2:
          padMap[insertColumn][insertRow + stepCorrection] = 1;
          break;
        case 3:
          padMap[insertColumn - 2][insertRow + stepCorrection] = 1;
          break;
        case 4:
          padMap[insertColumn][insertRow - 1 + stepCorrection] = 1;
          break;
        case 5:
          padMap[insertColumn - 2][insertRow - 1 + stepCorrection] = 1;
          break;
        case 6:
          padMap[insertColumn - 1][insertRow + 1] = 1;
          break;
        case 7:
          padMap[insertColumn - 1][insertRow - 1 ] = 1;
          break;
      }

      // Navbuttons

     /* switch (navMode) {

        case 1:
          padMap[insertColumn - 3][insertRow - 1] = 3; // Zurück
          break;
        case 2:
          padMap[insertColumn + 1][insertRow - 1] = 4; // Vor
          break;
        case 3:
          padMap[insertColumn - 3][insertRow - 1] = 3; // Zurück
          padMap[insertColumn + 1][insertRow - 1] = 4; // Vor
          break;

        default:
          break;

      }*/
      
      padMap[(columns/2) - 1][rows/2] = 2;


    }

    return padMap;
  }


  public Pane getPane() {
    return cuted;

  //public Pane getPane() {
    // Funktioniert soweit, der Getter muss nur noch auf ScrollPane gesetzt
    // werden. Und die Scrollleiste der Listenausgabe verschoben werden.
    // ScrollPane rol = new ScrollPane();
    // rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    // rol.setStyle("-fx-background-color:#FFF;");
    // rol.setContent(visPane);

  //  return visPane;

  }

  /**
   * Methode zur Übergabe der GUI an den Controller.
   *
   * @author Sebastian Hügelmann
   */
  public void setGUI(GUI gui) {
    this.gui = gui;
  }

  public void addNewTags(ReturnTagList tags, double x, double y, String labelText ) {
	  activePads=(tags.getSize() < MAX_TAGS-1)?tags.getSize():MAX_TAGS-1;//und das ist shorthand if und else
	Double[] xs = {x, x, x+1, x+1, x-1, x-1};
	Double[] ys = {y-1, y+1, y-.5,y+.5,y-.5,y+.5};  

	for (int i = 0; i < activePads; i++ ){ 
		addTag(xs[i],ys[i], tags.getTagObject(i).getTag());
	}
addMidPad(getHexWidth(),
		getColumnCorrection(getHexHeight()),
		getHexHeight(),
		getRows(getHexHeight()),
		getColumns(getHexWidth(),getColumnCorrection(getHexHeight())),visPane, x, y, labelText, 0 , 0);
	}

  public void update(ReturnTagList tags, Results results) {
	  int midColumn;
	  int midRow;
	  this.results = results;
	  
	  if(selectedPad == 11) {
		  midColumn = padMap.length/2 - 1;
		  midRow = padMap[midColumn].length/2;
	  } else {
		  midColumn = savedPads.get(savedPads.size()-1).getX();
		  midRow = savedPads.get(savedPads.size()-1).getY();
	  }
	 
	  
	  System.out.println("midColumn: " + midColumn + " midRow: " + midRow);
	  
	  double hexWidth = getHexWidth();
	  double hexHeight = getHexHeight();
	  double cc = getColumnCorrection(hexWidth) - 8;
	  
	  int rows = getRows(hexHeight);
	  int columns = getColumns(hexWidth, cc); 
	  
	  Pattern.tags = tags;
	  
	  for(int i = 0; i < padMap.length; i++)
		  for(int j = 0; j < padMap[i].length; j++)
			  if(padMap[i][j] == selectedPad)
			  {
				  System.out.println("Found that Pad!");
				  System.out.println(i + " " + j);
				  
				  if(i == midColumn + 1)
				  {
					  if(j == midRow)
					  	  { toggleStepcorrection(); System.out.println("0");}
					  
					  if(j == midRow + 1)
						  { toggleStepcorrection(); System.out.println("1");}
						  
					  if(j == midRow - 1)
						  { toggleStepcorrection(); System.out.println("2");}
						  
				  }

				  if(i == midColumn)
				  {
					  if(j == midRow) {System.out.println("3");}
					  
					  if(j == midRow + 1)
					  	{ System.out.println("4"); }
						  
					  if(j == midRow - 1)
					  	{ System.out.println("5"); }
				  }
					  
				  if(i == midColumn - 1)
				  {
					  if(j == midRow)
					      { toggleStepcorrection(); System.out.println("6");}
					  
					  if(j == midRow - 1)
						  {  toggleStepcorrection(); System.out.println("7");}
					  
					  if(j == midRow + 1)
					  	  {  toggleStepcorrection(); System.out.println("8");}
				  }
				  
				  savedPads.add(new Pad(0, 0, 0, null, i, j));
				  padMap = createPadMap(rows, columns, i+1, j);
				  i = padMap.length; j = padMap[i-1].length; break;
					  
			  }
	  
	  visPane = new Pane();// Pane Für Kacheln
	  visPane.setPrefSize(paneHeight, paneWidth - 320); 
	  
	  for(int i = 0; i < savedPads.size(); i++)
	  {
		  padMap[savedPads.get(i).getX()][savedPads.get(i).getY()] = 10 + i;
		  System.out.println("Placed: " + (10+i) + " at: " + savedPads.get(i).getX() + " " + savedPads.get(i).getY());
	  }

	  
	  System.out.println("Rows:" + rows + " Columns:" + columns + " ActivePads:" + activePads);
	  visPane = printPattern( hexWidth, cc, hexHeight, 
			  rows, columns, visPane);
	  
	  Listenausgabe ausgabe = new Listenausgabe(results);
	    ausgabe.setWidth(320);
	    ausgabe.setHeight(paneHeight);
	    ausgabe.setLayoutX(paneWidth - 334);
	    ausgabe.setLayoutY(0);
	  visPane.getChildren().addAll(ausgabe.ergebnisausgabe());
	  
	  cuted = new Pane();
	  cuted.getChildren().add(visPane);

  }

	private void toggleStepcorrection() {
		if(stepCorrection == 0)
			stepCorrection = 1;
		else 
			stepCorrection = 0;
	}
  
}


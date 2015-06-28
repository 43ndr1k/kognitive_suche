package de.leipzig.htwk.visualize;



import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.gui.GUI;
import de.leipzig.htwk.list.Listenausgabe;
import de.leipzig.htwk.searchApi.Results;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

/**
 * Generierung des Feldes von Hexagons
 * 
 * @author Fabian Freihube
 */
public class Pattern {

  private int paneWidth;
  private int paneHeight;
  private int navMode;
  private Results results;
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
    visPane.setPrefSize(paneHeight, paneWidth - 320);

    // BorderPane SearchList = new BorderPane();//Pane für Suchliste
    // SearchList.setPrefSize(paneHeight,paneWidth/2);



    double oneHexHeight = getHexHeight();
    double oneHexWidth = getHexWidth();
    double columnCorrection = getColumnCorrection(oneHexHeight);

    // Berechnung der Zeilen und Spaltenanzahl in Abhängigkeit von Fenstergröße und PadSize
    int rows = getRows(oneHexHeight);
    int columns = getColumns(oneHexWidth, columnCorrection);

    System.out.println("Rows:" + rows + " Columns:" + columns + " ActivePads:" + activePads);

    int[][] padMap = createPadMap(rows, columns);
    visPane =
        printPattern(padMap, oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane);
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
            getColumns(getHexWidth(), getColumnCorrection(getHexHeight())), visPane, x, y, tag);
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
  private Pane printPattern(int[][] padMap, double oneHexWidth, double columnCorrection,
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
            case 0: // visPane =
                    // addGreyPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns,
                    // visPane,
                    // (x - 0.5), (y - 0.25));
              break;

            case 1:
              visPane =
                  addColorPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getTagObject(rangeOfTags - numOfTags).getTag());
              numOfTags--;
              break;

            case 2:
              visPane =
                  addMidPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), tags.getSearchword());
              break;

            case 3:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), 1);
              break; // Navbutton zurück

            case 4:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.25), 2);
              break; // Navbutton vor

          }

        } else {
          switch (padMap[x][y]) {
            case 0: // visPane =
                    // addGreyPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns,
                    // visPane,
                    // (x - 0.5), (y - 0.75));
              break;

            case 1:
              visPane =
                  addColorPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getTagObject(rangeOfTags - numOfTags).getTag());
              numOfTags--;
              break;

            case 2:
              visPane =
                  addMidPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), tags.getSearchword());
              break;

            case 3:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), 1);
              break; // Navbutton zurück

            case 4:
              visPane =
                  addNavPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                      (x - 0.5), (y - 0.75), 2);
              break; // Navbutton vor

          }
        }
      }
    }

    return visPane;

  }

  private Pane addMidPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane2, double x, double y, String searchWord) {

    Pad pad = null;
    StackPane padPane = new StackPane();

    Label smallTopicLabel = new Label(searchWord);

    smallTopicLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

    double xPos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
    double yPos = (oneHexHeight + PAD_OFFSET) * (y);

    pad = new Pad(PAD_SIZE, 0, 0, Color.WHITE);
    padPane.setLayoutX(xPos);
    padPane.setLayoutY(yPos);

    padPane.getChildren().add(pad.getShape());
    padPane.getChildren().add(smallTopicLabel);
    padPane.getChildren().add(pad.getLightShape());

    visPane.getChildren().add(padPane);

    return visPane;
  }

  @SuppressWarnings("restriction")
  private Pane addNavPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane, double x, double y, int navButtonMode) {

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

    pad = new Pad(PAD_SIZE, 0, 0, COLOR_LIGHTGREY);
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
        @Override
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
   * @return visPane mit neuem aktiven Hexagon
   */
  @SuppressWarnings({"restriction", "unused"})
  private Pane addGreyPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane, double x, double y) {
    Pad pad;
    StackPane padPane = new StackPane();

    double xpos = (oneHexWidth - columnCorrection + PAD_OFFSET) * (x);
    double ypos = (oneHexHeight + PAD_OFFSET) * (y);

    padPane.setLayoutX(xpos);
    padPane.setLayoutY(ypos);

    pad =
        new Pad(PAD_SIZE, (oneHexWidth - columnCorrection + PAD_OFFSET) * (x),
            (oneHexHeight + PAD_OFFSET) * (y), COLOR_LIGHTGREY);

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
   * @return visPane mit neuem aktiven Hexagon
   */
  private Pane addColorPad(double oneHexWidth, double columnCorrection, double oneHexHeight,
      int rows, int columns, Pane visPane, double x, double y, String labelText) {
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

    int random = (int) (Math.random() * 5);

    pad = new Pad(PAD_SIZE, 0, 0, colors[random]);
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
    	 
        gui.getInstance().setSuchleisteText(tags.getSearchword());
      }
    });

    return visPane;
  }

  private Pane genLinkPane() {
    Pane linkPane = new Pane();
    Label tagLabel1 = new Label("Tag1");

    // linkPane.getChildren().add(topicLabel);

    return linkPane;
  }

  /**
   * Generiert aus der Anzahl der aktiven Pads und den Reihen und Spalten ein Array, welches anzeigt,
   * welche Felder aktiv sind und was in diesem Feld für ein Pad ist.
   * 
   * @param rows Anzahl der Reihen
   * @param columns Anzahl der Spalten
   * @return padMap
   */
  private int[][] createPadMap(int rows, int columns) {
    // Auto-generated method stub
    int[][] padMap = new int[columns][rows];

    int insertRow = rows / 2;
    int insertColumn = columns / 2;

    for (int x = 0; x < columns; x++)
      for (int y = 0; y < rows; y++)
        padMap[x][y] = 0;

    for (int i = 0; i < activePads + 1; i++) {
      switch (i) {
        case 1:
          padMap[insertColumn - 1][insertRow] = 2;
          break;
        case 2:
          padMap[insertColumn][insertRow] = 1;
          break;
        case 3:
          padMap[insertColumn - 2][insertRow] = 1;
          break;
        case 4:
          padMap[insertColumn][insertRow - 1] = 1;
          break;
        case 5:
          padMap[insertColumn - 2][insertRow - 1] = 1;
          break;
        case 6:
          padMap[insertColumn - 1][insertRow + 1] = 1;
          break;
        case 7:
          padMap[insertColumn - 1][insertRow - 1] = 1;
          break;
        case 8:
          padMap[insertColumn - 3][insertRow] = 1;
          break;
        case 9:
          padMap[insertColumn + 1][insertRow] = 1;
          break;
      }

      // Navbuttons

      switch (navMode) {

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

      }

    }

    return padMap;
  }

  public Pane getPane() {
	//Funktioniert soweit, der Getter muss nur noch auf ScrollPane gesetzt werden. Und die Scrollleiste der Listenausgabe verschoben werden.
	//ScrollPane rol = new ScrollPane();
	//rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	//rol.setStyle("-fx-background-color:#FFF;");
	//rol.setContent(visPane);
	
    return visPane;
    // Auto-generated method stub
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
		getColumns(getHexWidth(),getColumnCorrection(getHexHeight())),visPane, x, y, labelText);
	}
  }


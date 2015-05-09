package visualize;



import cognitive.search.ReturnTagList;
import de.leipzig.htwk.list.Listenausgabe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import gui.GUI;
import javafx.scene.input.MouseEvent;

/**
 * Generierung des Feldes von Hexagons
 * 
 * @author Fabian Freihube
 */
public class Pattern {

  private int paneWidth;
  private int paneHeight;
  private static final double PAD_SIZE = 102;
  private static final double PAD_OFFSET = 3;

  private static final Color COLOR_LIGHTGREEN = Color.web("#9FDA9F");
  private static final Color COLOR_ORANGE = Color.web("#FFC63E");
  private static final Color COLOR_LIGHTBLUE = Color.web("#5bc9ff");
  private static final Color COLOR_RED = Color.web("#ff9595");
  private static final Color COLOR_LIGHTPURPLE = Color.web("#c395ff");
  private static final Color COLOR_LIGHTGREY = Color.web("#e5e5e5");

  private Color[] colors = {COLOR_LIGHTGREEN, COLOR_ORANGE, COLOR_LIGHTBLUE, COLOR_RED,
      COLOR_LIGHTPURPLE, COLOR_LIGHTGREY};

  private int activePads;

  private static int MAX_TAGS = 9;

  private static Pane visPane;

  private static ReturnTagList tags;

  /**
   * @author Sebastian Hügelmann
   */
  private GUI gui;

  /**
   * Generiert das Feld von Hexagons
   *  @param paneHeight Höhe des Feldes
   * @param paneWidth Breite des Feldes
   * @param query
   * @param tags
   * @param gui
   */
  public Pattern(int paneHeight, int paneWidth, String query, ReturnTagList tags, GUI gui) {
    // Auto-generated method stub
    this.paneHeight = paneHeight;
    this.paneWidth = paneWidth;
    this.tags = tags;
    this.activePads = tags.getSize();
    this.gui = gui;

    visPane = new Pane();
    visPane.setPrefSize(paneHeight, paneWidth);

    double oneHexHeight = getHexHeight();
    double oneHexWidth = getHexWidth();
    double columnCorrection = getColumnCorrection(oneHexHeight);

    // Berechnung der Zeilen und Spaltenanzahl in Abhängigkeit von Fenstergröße und PadSize
    int rows = getRows(oneHexHeight);
    int columns = getColumns(oneHexWidth, columnCorrection);

    System.out.println("Rows:" + rows + " Columns:" + columns + " ActivePads:" + activePads);

    Boolean[][] padMap = createPadMap(rows, columns);
    visPane =
        printPattern(padMap, oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane);
    // iv
    Button list = new Button("Liste");// liste Button

   /* list.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent list) {
        visPane.getChildren().clear();
        Listenausgabe ausgabe = new Listenausgabe(query);
        ausgabe.setWidth(paneWidth);
        ausgabe.setHeight(paneHeight);
        *//**
         * Listenausgabe an Patterngui weitergegeben
         *//*
        visPane.getChildren().addAll(ausgabe.ergebnisausgabe());
      }

    });*/


    visPane.getChildren().addAll(list);


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
  private Pane printPattern(Boolean[][] padMap, double oneHexWidth, double columnCorrection,
      double oneHexHeight, int rows, int columns, Pane visPane) {
    int numOfTags = tags.getSize();
    if (numOfTags > MAX_TAGS) {
      numOfTags = MAX_TAGS;
    }
    int rangeOfTags = numOfTags;
    for (int y = 0; y < rows; y++) {
      for (int x = 0; x < columns; x++) {
        if ((x % 2) == 0) {
          if (padMap[x][y] == true) {
            visPane =
                addColorPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                    (x - 0.5), (y - 0.25), tags.getTagObject(rangeOfTags - numOfTags).getTag());
            numOfTags--;
          } else {
            visPane =
                addGreyPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                    (x - 0.5), (y - 0.25));
          }
        } else {
          if (padMap[x][y] == true) {
            visPane =
                addColorPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                    (x - 0.5), (y - 0.75), tags.getTagObject(rangeOfTags - numOfTags).getTag());
            numOfTags--;
          } else {
            visPane =
                addGreyPad(oneHexWidth, columnCorrection, oneHexHeight, rows, columns, visPane,
                    (x - 0.5), (y - 0.75));
          }
        }
      }
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
    String testlabel = "baum";
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
        System.out.println("Hallo");
        System.out.println(testlabel);
        System.out.println(largeTopicLabel.getText());
        // GUI.getInstance();
        // Funzt noch nicht da Scheiße!
        System.out.println("Text geholt von gui suchleiste: " + gui.getSuchleiste().getText());
        gui.setSuchleisteText(largeTopicLabel.getText());
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
   * Generiert aus der Anzahl der aktiven Pads und den Reihen und Spalten ein Array das anzeigt
   * welche Felder aktiv sind.
   * 
   * @param rows Anzahl der Reihen
   * @param columns Anzahl der Spalten
   * @return padMap
   */
  private Boolean[][] createPadMap(int rows, int columns) {
    // Auto-generated method stub
    Boolean[][] padMap = new Boolean[columns][rows];

    int insertRow = rows / 2;
    int insertColumn = columns / 2;

    for (int x = 0; x < columns; x++)
      for (int y = 0; y < rows; y++)
        padMap[x][y] = false;

    for (int i = 0; i < activePads + 1; i++) {
      switch (i) {
        case 1:
          padMap[insertColumn - 1][insertRow] = true;
          break;
        case 2:
          padMap[insertColumn][insertRow] = true;
          break;
        case 3:
          padMap[insertColumn - 2][insertRow] = true;
          break;
        case 4:
          padMap[insertColumn][insertRow - 1] = true;
          break;
        case 5:
          padMap[insertColumn - 2][insertRow - 1] = true;
          break;
        case 6:
          padMap[insertColumn - 1][insertRow + 1] = true;
          break;
        case 7:
          padMap[insertColumn - 1][insertRow - 1] = true;
          break;
        case 8:
          padMap[insertColumn - 3][insertRow] = true;
          break;
        case 9:
          padMap[insertColumn + 1][insertRow] = true;
          break;
      }
    }

    return padMap;
  }

  public Pane getPane() {
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
}

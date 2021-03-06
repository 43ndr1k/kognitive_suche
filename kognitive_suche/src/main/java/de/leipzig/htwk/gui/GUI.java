package de.leipzig.htwk.gui;

import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.pdf.box.access.PDFDocument;
import de.leipzig.htwk.search.history.HistoryObject;
import de.leipzig.htwk.search.history.tags.TagListHistory;
import de.leipzig.htwk.search.history.tags.TagListHistoryObject;
import de.leipzig.htwk.searchApi.Results;
import de.leipzig.htwk.visualize.VisController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;


/**
 * Erstellung der GUI
 *
 * @author Sebastian Hügelmann
 */
@SuppressWarnings("restriction")
public class GUI extends Stage implements Callback {


  static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  static java.awt.Rectangle workAreaSize_ = GraphicsEnvironment.getLocalGraphicsEnvironment()
      .getMaximumWindowBounds();
  private String operatingSystem = getOS();


  private static final int windowHeight = 768;
  private static final int windowWidth = 1280;
  private static final int windowsWindowHeight = (int) workAreaSize_.height;
  private static final int windowsWindowWidth = (int) screenSize.getWidth();
  private static final int FAROO = 0;
  private static final int DUCKDUCKGO = 1;
  

  private static int startMode = 0; // gibt an ob die Kog Suche aus der PDFBox oder
  // direkt gestartet wird 0 - WebSuche 1 - PDFSuche
  private static Controller mController;
  public ArrayList<String> tags = new ArrayList<String>();
  public ArrayList<String> url = new ArrayList<String>();
  public ArrayList<String> kwic = new ArrayList<String>();
  private BorderPane pane1 = new BorderPane();
  Scene start;
  private Stage stage;
  TextField suchleiste;
  BorderPane loadingPane = new BorderPane();
  Scene loadingScene;
  ArrayList<PDFDocument> pdfBoxDocuments = new ArrayList<PDFDocument>();
  private TagListHistory tagListHistory;
  private int btPosition = -1; // Position in der Breadcrump Trail
  private VisController visualController;
  private String clickedTagName;
  private double clickedTagX;
  private double clickedTagY;
  private static GUI instance;
  private int VerlaufTemp;
  
  

  /**
   * Konstruktor wird benötigt um eine Instanz der GUI zu erstellen. Threadunsichere
   * Instanzerstellung.
   *
   */
  private GUI() {
    /*
     * Notwendig um eine Instanz der GUI zu erstellen. Wichtig für aufrufen aus PDFBox
     */

  }

  /**
   * NUR mit Angabe des StartModes wird die GUI erstellt
   * 
   * @param mode
   */
  public void setStartMode(int mode) {
    startMode = mode;
    mController = new Controller();
    mController.setStartMode(startMode);
    if (startMode == 0) {
      mController.setParameter("de", "web", 1);
    }
    start = new Scene(pane1);
    stage = new Stage();
    suchleiste = new TextField();
    loadingScene = new Scene(loadingPane);

    tagListHistory = new TagListHistory();
    tagListHistory.clear();


    /* Anzeige der Stage */
    Image icon = new Image(String.valueOf(getClass().getResource("/icons/icon.png")));
    stage.getIcons().add(icon);
    stage.setTitle("Kognitive Suche");
    stage.centerOnScreen();


    if (operatingSystem.equals("Windows 7")) {
      stage.setWidth(windowsWindowWidth);
      stage.setHeight(windowsWindowHeight);
    } else {
      stage.setWidth(windowWidth);
      stage.setHeight(windowHeight);
    }


    stage.setScene(drawHomeScreen());
    // damit der Treiber sich schließt
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent we) {
        mController.closeDriver();
      }
    });
    stage.setResizable(true);
    stage.show();
  }

  public void goToHome() {
    stage.setScene(drawHomeScreen());
  }

  /**
   * Methode zur Erstellung des Buttons mit Logo für die Rückkehr auf die Startseite.
   *
   * @author Sebastian Hügelmann
   * @return HBox
   */
  public HBox goHomeButton() {

    HBox hboxHOME = new HBox();
    final ImageView imv = new ImageView();
    final Image image = new Image(String.valueOf(getClass().getResource("/icons/bild.jpg")));
    imv.setImage(image);
    imv.setCursor(Cursor.HAND);

    imv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        System.out.println("Tile pressed");
        
        mController.setResultList(null);
        mController.setTags(null);
        visualController = null;
        
        stage.setScene(drawHomeScreen());
        
        tagListHistory.clear();
        btPosition = -1;
      }
    });

    hboxHOME.getChildren().add(imv);
    hboxHOME.setAlignment(Pos.CENTER);
    hboxHOME.setPadding(new Insets(15, 15, 15, 15));
    return hboxHOME;
  }

  /**
   * Methode zeichnet die Startszene
   *
   * @author Sebastian Hügelmann
   * @return Scene
   */
  public Scene drawHomeScreen() {

    String searchButtonText = "Suche";
    pane1.getChildren().clear();
    HBox hbox1 = new HBox();// horizontale Box für Suchleiste und Buttons
    HBox hbox2 = new HBox();// schliessen box
    HBox hbox3 = new HBox();// Horizontale Box für Buttons von vbox2 und
    // vbox3
    VBox vbox1 = new VBox();// vertikale Box für Logo, hbox2
    VBox vbox2 = new VBox();// Vertikale Box für Sprachauswahl Buttons
    VBox vbox3 = new VBox();// Vertikale Box für Suchartauswahl Buttons
    final String[] SelectedLanguage = {"de"}; // Variable für
    // Sprachauswahl
    final String[] Selectedsrc = {"web"}; // Variable für Suchartauswahl
    pane1.setStyle("-fx-background-color: #FFF;");
    pane1.setCenter(vbox1);
    pane1.setBottom(hbox2);// schliessen

    if (startMode == 1) {
      if (mController.getPdfBoxDocuments() != null) {
        Label pdfAnzeige = new Label();
        pdfAnzeige = new Label("Eingelesene PDFs: " + mController.getPdfBoxDocuments().size());
        pdfAnzeige.setStyle("-fx-font-size: 12pt;");
        hbox3.getChildren().addAll(pdfAnzeige);
      }
    }

    hbox1.setAlignment(Pos.CENTER);
    hbox2.setAlignment(Pos.BOTTOM_RIGHT);// Rechte ecke postionsbestimmung
    // closebox

    hbox3.setAlignment(Pos.BOTTOM_CENTER);

    hbox3.setPadding(new Insets(-50));
    hbox3.setSpacing(20);

    vbox2.setPadding(new Insets(10));
    vbox2.setSpacing(10);

    vbox3.setPadding(new Insets(10));
    vbox3.setSpacing(10);

    hbox1.setPadding(new Insets(15, 30, 15, 30)); /*
                                                   * Bestimmt den Abstand vom Rand nach Innen
                                                   */
    hbox1.setSpacing(20); /* Bestimmt den Abstand der Elemente voneinander */
    hbox1.setStyle("-fx-background-color: #FFF;"); /*
                                                    * Bestimmt die Hintergrundfarbe
                                                    */
    vbox1.setStyle("-fx-background-color: #FFF;");
    vbox1.setAlignment(Pos.CENTER);
    vbox1.setSpacing(50);
    suchleiste.setMaxWidth(200);
    suchleiste.clear();

    Button close = new Button("Schliessen");// button zum schliessen

    close.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {
        Platform.exit();

      }

    });

    suchleiste.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
          if (!suchleiste.getText().trim().isEmpty()) {
            startQuery();
          } else {
            /**
             * Error Meldungen, falls kein Suchbegriff eingegeben wurde.
             */
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Error Message");
            alert
                .setContentText("Kein Suchbegriff eingegeben! \n Bitte geben Sie einen Suchbegriff ein.");
            alert.showAndWait();
          }

        }
      }

    });

    hbox1.getChildren().add(suchleiste);

    if (startMode == 0) {
      searchButtonText = "WebSuche";
    } else if (startMode == 1) {
      searchButtonText = "Suche in PDFs";
    }

    Button suchButton = new Button(searchButtonText);

    suchButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent sucheF) {
        if (!suchleiste.getText().trim().isEmpty()) {
          startQuery();
        } else {
          /**
           * Error Meldungen, falls kein Suchbegriff eingegeben wurde.
           */
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error Dialog");
          alert.setHeaderText("Error Message");
          alert
                  .setContentText("Kein Suchbegriff eingegeben! \n Bitte geben Sie einen Suchbegriff ein.");
          alert.showAndWait();
        }

      }
    });

    hbox1.getChildren().add(suchButton);


    Button history = new Button("Verlauf");
    history.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent sucheP) {
        showHistory();
      }

    });

    hbox1.getChildren().add(history);
    vbox1.getChildren().addAll(goHomeButton(), hbox1, hbox3);
    return start;
  }

  /**
   * @author Fabian Freihube Methode um den Suchverlauf anzuzeigen
   */
  private void showHistory() {

    ArrayList<HistoryObject> historyData = mController.getHistory();


    Collections.reverse(historyData);//

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    Separator sTitle = new Separator();
    VBox vbox1 = new VBox();
    VBox titleBox = new VBox();
    HBox lineBox;
    BorderPane historyPane = new BorderPane();
    BorderPane pane = new BorderPane();
    ScrollPane rol = new ScrollPane();
    Hyperlink[] link = new Hyperlink[historyData.size()];
    Label dateLabel = new Label();

    Label title = new Label("Verlaufsübersicht:");
    title.setStyle("-fx-font-size: 20pt;");
    titleBox.getChildren().addAll(title, sTitle);
    pane.setTop(titleBox);

    // Label label2[] = new Label[25];

    for (int k = 0; k < historyData.size(); k++) {
      dateLabel = new Label(dateFormat.format(historyData.get(k).date) + ": ");
      link[k] = new Hyperlink(historyData.get(k).searchWord);
      lineBox = new HBox();
      lineBox.setStyle("-fx-padding: 5 5 5 5");
      lineBox.getChildren().addAll(dateLabel, link[k]);
      vbox1.getChildren().add(lineBox);
      dateLabel.setWrapText(true);
      dateLabel.setStyle("-fx-font-weight: italic;");
      dateLabel.setStyle("-fx-label-padding: 0 0 0 0;");
      dateLabel.setStyle("-fx-font-size: 20pt;");
      link[k].setStyle("-fx-font-size: 18pt;");

      String searchword = historyData.get(k).searchWord;

      link[k].setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent sucheP) {
          setSuchleisteText(searchword);
        }
      });
    }

    pane.setCenter(vbox1);
    rol.setPrefSize(500, 500);
    rol.setContent(pane);
    rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    rol.setStyle("-fx-padding: 25 25 25 25");

    BorderPane homebuttonPane = new BorderPane();

    homebuttonPane.setCenter(goHomeButton());
    homebuttonPane.setStyle("-fx-background-color: #FFF;");
    homebuttonPane.setPrefHeight(getWindowheight() * 0.15);

    historyPane.setTop(homebuttonPane);
    historyPane.setCenter(rol);
    Scene historyScene = new Scene(historyPane);

    Collections.reverse(historyData);//

    stage.setScene(historyScene);


  }



  /**
   * Diese Methode startet die Suche aus dem Controller
   *
   */
  public void startQuery() {

    stage.setScene(loadIndicator()); 
    mController.setQuery(suchleiste.getText());
    searchThread th = new searchThread(this, mController, startMode);
    th.setSearchEngine(DUCKDUCKGO);
    th.start();

  }


  /**
   * Setter zum setzten des Suchleistens Textes nach Auswahl einer Kategorie in der Visualisierung.
   *
   * @author Sebastian Hügelmann
   * @param suchleiste Suchleiste für den Suchbegriff.
   */

  public void setSuchleisteText(String suchleiste) {
    System.out.println("Übergebener Begriff " + suchleiste);
    this.suchleiste.setText(suchleiste);
    System.out.println(this.suchleiste.getText());
    startQuery();
  }

  public static int getWindowheight() {
    return windowHeight;
  }

  public static int getWindowwidth() {
    return windowWidth;
  }

  public void setStageScene(Scene visual) {
    this.stage.setScene(visual);
    stage.setScene(visual);
  }

  public Stage getStage() {
    return this.stage;
  }

  private Scene loadIndicator() {
    DoubleProperty stroke = new SimpleDoubleProperty(100.0);
    System.out.println("Ladebalken Methode gestartet!");
    loadingPane.setStyle("-fx-background-color: #FFF;");

    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE);

    final KeyValue kv = new KeyValue(stroke, 0);
    final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv);

    timeline.getKeyFrames().add(kf);
    timeline.play();

    // Vertikale Box für Loading Bar und darunter Label
    VBox root = new VBox(3);
    root.setAlignment(Pos.CENTER);

    // Stackpane für den Balken
    StackPane progressIndicator = new StackPane();

    // Ladebalken
    Rectangle bar = new Rectangle(350, 13);
    bar.setFill(Color.TRANSPARENT);
    bar.setStroke(Color.BLACK);
    bar.setArcHeight(15);
    bar.setArcWidth(15);
    bar.setStrokeWidth(2);

    // Viereck im balken
    Rectangle progress = new Rectangle(342, 6);
    progress.setFill(Color.BLACK);
    progress.setStroke(Color.BLACK);
    progress.setArcHeight(8);
    progress.setArcWidth(8);
    progress.setStrokeWidth(1.5);
    progress.getStrokeDashArray().addAll(3.0, 7.0, 3.0, 7.0);
    progress.strokeDashOffsetProperty().bind(stroke);

    // Hinzufügen zur StackPane
    progressIndicator.getChildren().add(progress);
    progressIndicator.getChildren().add(bar);

    // Stackpane zur VBox um darunter das label zu packen
    root.getChildren().add(progressIndicator);

    Text label = new Text("Loading...");
    label.setFill(Color.BLACK);

    // Label wird hinzugefügt
    root.getChildren().add(label);

    // Jetzt ist alles in der VBox root
    loadingPane.setCenter(root);
    return loadingScene;
  }

  public TextField getSuchleiste() {
    // Auto-generated method stub
    return suchleiste;
  }

  public static GUI getInstance() {
    if (instance == null) {
      instance = new GUI();
    }
    return instance;

  }

  public void reDrawHomeScreen() {
    stage.setScene(drawHomeScreen());
  }

  public String getOS() {
    String os = "os.name";
    String returnString;
    Properties prop = System.getProperties();
    returnString = prop.getProperty(os);
    return returnString;
  }

  public Controller getController() {
    return mController;
  }

  /**
   * Methode für die Visualisierung nach Eingabe eines Suchbegriffes
   *
   * @author Fabian Freihube, Sebastian Hügelmann
   * @param list Übergabe der gefundenen Ergebnisse per Liste.
   * @param searchword Übergabe des Suchwortes als String.
   */
  @SuppressWarnings("static-access")
  public void initVisual(ReturnTagList list, String searchword, Results results) {

    // setResultList(results); brauch ich vielleicht
    System.out.println("startVisual Gestartet");
    ReturnTagList tags = list;
   
    BorderPane visPane = new BorderPane();
    visPane.setMinSize(0, 0);
    BorderPane homebuttonPane = new BorderPane();
    System.out.println("Checkpoint 1");
    homebuttonPane.setCenter(this.goHomeButton());
    homebuttonPane.setStyle("-fx-background-color: #FFF;");
    homebuttonPane.setPrefHeight(this.getWindowheight() * 0.15);
    System.out.println("Checkpoint 2");
    visualController = new VisController();
    visualController.setResults(results);
    visualController.setPane(visPane);
    visualController.setQuery(searchword);
    System.out.println("Checkpoint 3");
    visualController.setPaneHeight((int) (this.getStage().getHeight() * 0.85));
    visualController.setPaneWidth((int) this.getStage().getWidth());
    System.out.println("Checkpoint 4");
    visPane.setCenter(visualController.startVisualize(tags, getNavMode()));
    System.out.println("Checkpoint 5");
    visPane.setTop(homebuttonPane);

    System.out.println("Checkpoint Ende");
    Scene visual = new Scene(visPane);

    this.setStageScene(visual);

    System.out.println("fertig visual");
  }

  private void addStepToTagListHistory() {
    btPosition++;
    tagListHistory.addStep(btPosition, mController.getTags(), mController.getResultList());
    System.out.println("Step hinzugefügt: " + mController.getTags().getSearchword() + " Pos: " + btPosition);
  }

  /**
   * Annahme der Ergebnisse de Suche und Aufruf der Visualisierung
   */
  private void startVisualisation() {
    ReturnTagList tags = mController.getTags();
    Results results = mController.getResultList();

    // Falls keine Ergebnisse gefunden wurden wird zum Homescreen bzw. zur letzten Tag auswahl
    // zurückgekehrt
    if (results == null || tags == null) {
      if (btPosition == -1) {
        this.reDrawHomeScreen();
      } else {
        TagListHistoryObject his = tagListHistory.getStep(btPosition);
        initVisual(his.getTagList(), his.getTagList().getSearchword(), his.getResults());
        
      }
    } else {
      
      addStepToTagListHistory();
      initVisual(tags, tags.getSearchword(), results); // Starten der Visualisierung
    }

  }

  /**
   * Aufruf der Visualisierung nach Erhalt des Callbacks
   */
  public void callback() {
    System.out.println("Callback erhalten. Alles Roger!");
    //startVisualisation();    ---callback wird ersetzt 
      
    if (visualController == null){
    	startVisualisation();
    }
    else{
           
      addNewTags();
    
    }
  }
  
  private void addNewTags(){
	
	  ReturnTagList tags = mController.getTags();
	  Results results = mController.getResultList();
	  addStepToTagListHistory();

	  visualController.setResults(results);
	  visualController.updatePattern(tags);
	  //Scene visual = new Scene(visualController.getPane());
	  //System.out.println (getStage());
	  //System.out.println(getStage().getScene());
	  BorderPane homebuttonPane = new BorderPane();
	  homebuttonPane.setCenter(this.goHomeButton());
	  homebuttonPane.setStyle("-fx-background-color: #FFF;");
	  homebuttonPane.setPrefHeight(this.getWindowheight() * 0.15);
	 
	   ((BorderPane) visualController.getPane()).setTop(homebuttonPane);
	   this.setStageScene(visualController.getPane().getScene());
  }
  
  public VisController getVisualController() {
	  return this.visualController;
  }
  

  public void controllBTPosition(int pos) {
    
    btPosition = pos;
    
    if(btPosition != -1)
    {
      mController.setTags(tagListHistory.getStep(btPosition).getTagList());
      mController.setResultList(tagListHistory.getStep(btPosition).getResults());
  
      addNewTags();
    } else {
      
      mController.setTags(tagListHistory.getStep(0).getTagList());
      mController.setResultList(tagListHistory.getStep(0).getResults());
      System.out.println("In das MidPad wurde eingfügt: " + tagListHistory.getStep(0).getTagList().getSearchword());
      startVisualisation();
      
    }

  }

  public int getNavMode() {

    System.out.println("Pos: " + btPosition + " Steps: " + tagListHistory.getStepsCount());

    if (btPosition == 0 && (tagListHistory.getStepsCount() - 1) == 0)
      return 0;
    else if (btPosition == 0 && (tagListHistory.getStepsCount() - 1) != 0)
      return 2;
    else if (btPosition != 0 && (tagListHistory.getStepsCount() - 1) == btPosition)
      return 1;
    else if (btPosition != 0 && (tagListHistory.getStepsCount() - 1) != btPosition)
      return 3;

    return 0;
  }

public void setClickedTag(double x, double y, String tagName, String suchleiste) {
	clickedTagY = y;
	clickedTagX = x;
	clickedTagName = tagName;
	//Wieso?
	//this.suchleiste.setText(suchleiste);
}


}

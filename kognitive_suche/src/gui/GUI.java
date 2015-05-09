package gui;


import de.leipzig.htwk.controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

import pdf.box.access.PDFDocument;
import visualize.Pattern;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Erstellung der GUI
 *
 * @author Sebastian Hügelmann
 */

public class GUI extends Application {
  private static final int windowHeight = 768;
  private static final int windowWidth = 1024;
  private int startMode = 0; // gibt an ob die Kog Suche aus der PDFBox oder direkt gestartet wird
  private Controller mController;
  public ArrayList<String> tags = new ArrayList<String>();
  public ArrayList<String> url = new ArrayList<String>();
  public ArrayList<String> kwic = new ArrayList<String>();
  private BorderPane pane1 = new BorderPane();
  Scene start;
  private Stage stage;

  TextField suchleiste; /* DIESEN TEXT BRAUCH DER CONTROLLER UND FAROO */

  private Timeline timeline = new Timeline();
  private DoubleProperty stroke = new SimpleDoubleProperty(100.0);
  BorderPane loadingPane = new BorderPane();
  Scene loadingScene;

  ArrayList<PDFDocument> pdfBoxDocuments = new ArrayList<PDFDocument>();


  /**
   * {@inheritDoc}
   * 
   * @exception Exception
   * @param stage2 Ist Grundfläche für alle Panes.
   */
  @Override
  public void start(Stage stage2) throws Exception {


    /* Notwendig um eine Instanz der GUI zu erstellen. Wichtig für aufrufen aus PDFBox */
    start = new Scene(pane1);
    stage = new Stage();
    suchleiste = new TextField();
    loadingScene = new Scene(loadingPane);
    mController = new Controller();

    mController.setGUI(this);
    // mController.setParameter("de", "web", 1);

    /* Anzeige der Stage */
    stage.setTitle("Kognitive Suche");
    stage.centerOnScreen();

    stage.setWidth(windowWidth);
    stage.setHeight(windowHeight);
    stage.setScene(drawHomeScreen());

    stage.setResizable(true);
    stage.show();

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
    final Image image2 = new Image("http://www.imn.htwk-leipzig.de/~shuegelm/image.jpg");
    imv.setImage(image2);
    imv.setCursor(Cursor.HAND);

    imv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        System.out.println("Tile pressed");
        stage.setScene(drawHomeScreen());
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
    pane1.getChildren().clear();
    HBox hbox1 = new HBox();// horizontale Box für Suchleiste und Buttons
    HBox hbox2 = new HBox();// schliessen box
    HBox hbox3 = new HBox();// Horizontale Box für Buttons von vbox2 und vbox3
    VBox vbox1 = new VBox();// vertikale Box für Logo, hbox2
    VBox vbox2 = new VBox();// Vertikale Box für Sprachauswahl Buttons
    VBox vbox3 = new VBox();// Vertikale Box für Suchartauswahl Buttons
    final String[] SelectedLanguage = {"de"}; // Variable für Sprachauswahl
    final String[] Selectedsrc = {"web"}; // Variable für Suchartauswahl
    pane1.setStyle("-fx-background-color: #FFF;");
    pane1.setCenter(vbox1);
    pane1.setBottom(hbox2);// schliessen

    hbox1.setAlignment(Pos.CENTER);
    hbox2.setAlignment(Pos.BOTTOM_RIGHT);// Rechte ecke postionsbestimmung closebox

    hbox3.setAlignment(Pos.BOTTOM_CENTER);

    hbox3.setPadding(new Insets(-50));
    hbox3.setSpacing(20);

    vbox2.setPadding(new Insets(10));
    vbox2.setSpacing(10);

    vbox3.setPadding(new Insets(10));
    vbox3.setSpacing(10);

    hbox1.setPadding(new Insets(15, 30, 15, 30)); /* Bestimmt den Abstand vom Rand nach Innen */
    hbox1.setSpacing(20); /* Bestimmt den Abstand der Elemente voneinander */
    hbox1.setStyle("-fx-background-color: #FFF;"); /* Bestimmt die Hintergrundfarbe */
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
          startQuery();
        }
      }

    });

    Button sucheF = new Button("Suche in F");

    sucheF.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent sucheF) {
        startQuery();
      }
    });

    Button sucheP = new Button("Suche in P");
    sucheP.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent sucheP) {
        // mController.startSearchP();
        // kwic = mController.getKeywords2();
        // url = mController.getDocName();
        // tags = mController.getKeywords1(); // Ohne Sortierung soviel ich weiß

      }

    });

    /**
     * Soll später mal die Links aufrufen.
     * 
     * @author Sadik Ulusan
     */
    // Auswahl der Sprache mit einer Combobox
    /*
     * final ObservableList<String> languages = FXCollections.observableArrayList( "deutsch",
     * "englisch", "chinesisch" ); final ComboBox language = new ComboBox();
     * 
     * hbox1.getChildren().addAll(suchleiste,sucheF,sucheP, language);
     * 
     * language.setItems(languages);
     */



    /**
     * @author Christian Schmidt
     */
    // Label für die Suchoptionen

    Label label = new Label("Faroo Suchoptionen");
    label.setFont(Font.font("Arial", 14));
    HBox schrift = new HBox(label);
    schrift.setAlignment(Pos.CENTER);
    schrift.setPadding(new Insets(-15, 15, 15, 15));
    schrift.setSpacing(10);
    // Buttons für die Suchoptionen
    final Button[] btnlanguage = new Button[2];
    btnlanguage[0] = new Button("de");
    btnlanguage[0].setText("German");
    btnlanguage[0].setStyle("-fx-background-color: #00B2EE;");
    btnlanguage[0].setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        btnlanguage[0].setStyle("-fx-background-color: #00B2EE;");
        btnlanguage[1].setStyle("-fx-background: #FFFFFF;");
        SelectedLanguage[0] = "de";
        mController.setParameter("de", "web", 1);

      }
    });
    btnlanguage[1] = new Button("en");
    btnlanguage[1].setText("English");
    btnlanguage[1].setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        btnlanguage[1].setStyle("-fx-background-color: #00B2EE;");
        btnlanguage[0].setStyle("-fx-background: #FFFFFF;");
        SelectedLanguage[0] = "en";
      }
    });

    final Button[] btnsrc = new Button[3];
    btnsrc[0] = new Button("web");
    btnsrc[0].setText("Web");
    btnsrc[0].setStyle("-fx-background-color: #00B2EE;");
    btnsrc[0].setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        btnsrc[0].setStyle("-fx-background-color: #00B2EE;");
        btnsrc[1].setStyle("-fx-background: #FFFFFF;");
        btnsrc[2].setStyle("-fx-background: #FFFFFF;");
        Selectedsrc[0] = "web";
      }
    });
    btnsrc[1] = new Button("news");
    btnsrc[1].setText("News");
    btnsrc[1].setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        btnsrc[1].setStyle("-fx-background-color: #00B2EE;");
        btnsrc[0].setStyle("-fx-background: #FFFFFF;");
        btnsrc[2].setStyle("-fx-background: #FFFFFF;");
        Selectedsrc[0] = "news";
      }
    });
    btnsrc[2] = new Button("topics");
    btnsrc[2].setText("Headlines");
    btnsrc[2].setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        btnsrc[2].setStyle("-fx-background-color: #00B2EE;");
        btnsrc[1].setStyle("-fx-background: #FFFFFF;");
        btnsrc[0].setStyle("-fx-background: #FFFFFF;");
        Selectedsrc[0] = "topics";

      }
    });

    // --------------------------------------------------
    // Vertikalbox für Buttons Deutsch und Englisch
    vbox2.getChildren().addAll(btnlanguage[0], btnlanguage[1]);
    // Vertikalbox für WEB NEWS und Title
    vbox3.getChildren().addAll(btnsrc[0], btnsrc[1], btnsrc[2]);
    // Die beiden Vertikalboxen von Sprache und Suchart werden in einer Horizontalbox
    // zusammengeführt
    // hbox3.getChildren().addAll(vbox2, vbox3);
    hbox1.getChildren().addAll(suchleiste, sucheF, sucheP);
    vbox1.getChildren().addAll(goHomeButton(), hbox1, hbox3);
    // hbox2.getChildren().addAll(close);
    // Parameterübergabe an den Controller - scheint hier aber nicht zu gehen
    mController.setParameter(SelectedLanguage[0], Selectedsrc[0], 1);
    return start;
  }

  /**
   * Diese Methode startet die Suche aus dem Controller
   *
   */
  public void startQuery() {
    stage.setScene(loadingIndicator());


    // Task suche;
    // suche = createWorker();
    // new Thread(suche).start();

    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        mController.farooSearch(suchleiste.getText());
      }
    });
  }

  // public Task createWorker() {
  // return new Task() {
  // @Override
  // protected Object call() throws Exception {
  // mController.farooSearch(suchleiste.getText());
  // return null;
  // }
  // };
  // }

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


  private Scene loadingIndicator() {
    System.out.println("Ladebalken Methode gestartet!");
    loadingPane.setStyle("-fx-background-color: #FFF;");
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

  public void startKogSucheExtern() {
    startMode = 1;
    launch();
  }

  public ArrayList<PDFDocument> getPDFBoxDocuments() {
    return pdfBoxDocuments;
  }

  public void setPDFBoxDocuments(ArrayList<PDFDocument> pdfBoxDocuments) {
    this.pdfBoxDocuments = pdfBoxDocuments;
  }

  public TextField getSuchleiste() {
    // Auto-generated method stub
    return suchleiste;
  }

}

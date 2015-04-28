package gui;


import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.faroo.api.Results;
import de.leipzig.htwk.tests.VisualTest;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import komplexe.suche.TagObjectList;
import simple.algorithm.SimAlgTags;
import visualize.VisController;

import java.util.ArrayList;

/**
 * GUI Erstellung
 *
 * @author Sebastian Hügelmann
 */

public class GUI extends Application {
  private static final int windowHeight = 768;
  private static final int windowWidth = 1024;

  private Controller mController = new Controller();
  public ArrayList<String> tags = new ArrayList<String>();
  public ArrayList<String> url = new ArrayList<String>();
  public ArrayList<String> kwic = new ArrayList<String>();
  private BorderPane pane1 = new BorderPane();
  Scene start = new Scene(pane1);
  private GridPane pane2 = new GridPane();
  private Stage stage = new Stage();
  // private Scene start1 = new Scene(pane1);
  private int anzkat = 10;

  final TextField suchleiste = new TextField(); /* DIESEN TEXT BRAUCH DER CONTROLLER UND FAROO */

  public static void main(String[] args) {
    launch(args);
  }
  
  /**
   * {@inheritDoc}
   * @exception Exception
   * @param stage Ist Grundfläche für alle Panes.
   */
  @Override
  public void start(Stage stage2) throws Exception {

	mController.setGUI(this);
    mController.setParameter("de", "web", 1);
    
    // Scene start1 = new Scene(pane1());
    // Scene start1 = new Scene(pane1());
    // start1.setRoot(pane1());

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
   * Methode holt Resultate vom Controller aufgrund des Suchbegriffs 
   * @author Hendrik Sawade
   * @param kwic Kurzbeschreibung
   * @param url Url der jeweiligen Adresse
   * @param tags Erstellten Tags vom simplen Algorithmus
   */
  private void getData() {

    mController.queryFaroo(suchleiste.getText());
    Results r = mController.getResultList();
    for (int i = 0; i < r.getResults().size(); i++) {
      kwic.add(r.getResults().get(i).getKwic());
      // title.add(r.getResults().get(i).getTitle());
      url.add(r.getResults().get(i).getUrl());
    }

    ArrayList<SimAlgTags> treffer = mController.getTags();
    for (int i = 0; i < treffer.size(); i++) {
      tags.add(treffer.get(i).gettag());
    }
  }

  
  /**
   * Methode zeichnet ein Eingabefeld und macht es editierbar für den Benutzer
   * @author Sebastian Hügelmann
   * @param anzkat Dummy Integer zur Erstellung der Textfields
   * @param pane1 Die Grundpane die auf der "stage" liegt. Ist eine BorderPane.
   * @param pane2 GridPane auf der die Textfields erstellt werden. Wurde durch Visualisierung vispane ersetzt.
   */
  public void textfield() {
    TextArea textfield[] = new TextArea[25];
    System.out.println("Button Action ausgeführt");


    // Aufruf Controller
    // GUI.suchleiste.getText(); @Parameter
    /*
     * Suchbegriffe aus der Suchleiste auslesen und an den Controller übergeben @Parameter
     * Rückgabe der Tags Tags sind in String Array gespeichert? Anzahl String Arrays = Anzahl
     * Kategorie @Parameter anzKat
     */

    /* Hier wird die Kategorieanzahl übergeben */
    /* int anzKat=4; */
    /* Test mit 4 Kategorien */
    int feld = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 5; j++) {
        // textfield[i] = new
        // TextArea("Ich bin das Textfeld in der Spalte "+i+" Zeile "+j+" !\n"+"Es können Tags per Hand gelöscht werden und mit Enter die Kategorie auswählen");
        textfield[feld] = new TextArea("" + tags.get(feld)); // DU MUESSTEST NOCH PRUEFEN OB DAS
                                                             // ARRAYLIST WAS ENTHAELT
        /* Vorerst Editable,um Tags per hand rauszufiltern. */
        textfield[feld].setEditable(true);
        textfield[feld].setWrapText(true);
        textfield[feld].setOnKeyPressed(new EventHandler<KeyEvent>() {
          
          
          @Override
          public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
              System.out.println("Enter wurde gedrückt!");
              // Aufruf Controller
              // GUI.suchleiste.getText(); @Parameter
              // GUI.textfield[i].getText(); @Parameter

              /*
               * Tags + Suchstring an Controller @Parameter Rückgabe der Tags Tags sind in String
               * Array gespeichert? Anzahl String Arrays = Anzahl Kategorie @Parameter anzKat
               */
              /* Zum Test 1 Kategorie */
              anzkat = 1;

              if (anzkat < 2) {
                // ergebnisausgabe(); /*Ausgabe der Webseiten*/
              } else {
                textfield();
              } /* Kategorien mit Tags erstellen */


            }
          }
        });
        pane2.add(textfield[feld], i, j);
        System.out.println("Feld erstellt!");
        feld++;
      }
    }
    pane2.setAlignment(Pos.CENTER);
    pane2.setPadding(new Insets(20, 30, 20, 30));
    pane2.setHgap(10);
    pane2.setVgap(10);
    pane1.setCenter(pane2);
    pane1.setTop(goHomeButton());
  }
  
  
/**
 * Methode zur Erstellung des Buttons mit Logo f�r die Rückkehr auf die Startseite.
 * @author Sebastian Hügelmann
 * @param stage Ist Grundfläche für alle Panes.
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
        // start.setRoot(pane1);

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
   * @author Sebastian Hügelmann
   * @param pane1 Die Grundpane die auf der "stage" liegt. Ist eine BorderPane.
   * @param suchleiste Ist das Textfield zur Eingabe des Suchbegriffes.
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

    // vbox2.setAlignment(arg0);
    // vbox3.setAlignment(arg0);
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
          getData();
          // textfield();
          //startVisual();
          
          //Startet nun die Methode initVisual() aus dem Controller
          mController.initVisual();
          System.out.println("mController.initVisual() wurde aufgerufen");
        }
      }

    });

    Button sucheF = new Button("Suche in F");

    sucheF.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent sucheF) {
        getData();
        // textfield(); /*Ruft die Methode zur Generierung Textfelder auf*/
        //startVisual();
        
        //Startet nun die Methode initVisual() aus dem Controller
        mController.initVisual();
        System.out.println("mController.initVisual() wurde aufgerufen");
      }
    });

    Button sucheP = new Button("Suche in P");
    sucheP.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent sucheP) { // Bei PDFbox wird das TXT feld nicht ben�tigt -
                                               // bis jetzt
        // mController.startSearchP();
        // kwic = mController.getKeywords2();
        // url = mController.getDocName();
        // tags = mController.getKeywords1(); // Ohne Sortierung soviel ich wei�
        textfield();

      }

    });

    /**
     * Soll sp�ter mal die Links aufrufen.
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
    // Label f�r die Suchoptionen

    Label label = new Label("Farroo Suchoptionen");
    label.setFont(Font.font("Arial", 14));
    HBox schrift = new HBox(label);
    schrift.setAlignment(Pos.CENTER);
    schrift.setPadding(new Insets(-15, 15, 15, 15));
    schrift.setSpacing(10);
    // Buttons f�r die Suchoptionen
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
    vbox2.getChildren().addAll(btnlanguage[0], btnlanguage[1]); // Vertikalbox für Buttons Deutsch
                                                                // und Englisch
    vbox3.getChildren().addAll(btnsrc[0], btnsrc[1], btnsrc[2]); // Vertikalbox für WEB NEWS und
                                                                 // TITLE
    hbox3.getChildren().addAll(vbox2, vbox3); // Die beiden Vertikalboxen von Sprache und Suchart
                                              // werden in einer Horizontalbox zusammengeführt
    hbox1.getChildren().addAll(suchleiste, sucheF, sucheP);
    vbox1.getChildren().addAll(goHomeButton(), hbox1, schrift, hbox3);
    hbox2.getChildren().addAll(close);
    mController.setParameter(SelectedLanguage[0], Selectedsrc[0], 1); // Parameterübergabe an den
                                                                      // Controller - scheint hier
                                                                      // aber nicht zu gehen
    return start;
  }

  /**
   * Methode für die Visualisierung nach Eingabe eines Suchbegriffes
   * @author Fabian Freihube, Sebastian Hügelmann (small Part)
   * @param stage Ist Grundfläche für alle Panes.
   */
  public void startVisual(TagObjectList Tags) {
    /*
     * das Objekt Tag, welches aus der Klasse visualtest übernommen wird dient zu Testzwecken und
     * kann bei der fertigen Implementation durch ein Objekt des Komplexen Suchalgorithmus ersezt
     * werden.
     */
	System.out.println("startVisual Gestartet");
    
	//VisualTest tmp = new VisualTest();

    //TagObjectList tags = tmp.getTags();
	TagObjectList tags = Tags;

    BorderPane visPane = new BorderPane();
    BorderPane homebuttonPane = new BorderPane();

    homebuttonPane.setCenter(goHomeButton());
    homebuttonPane.setStyle("-fx-background-color: #FFF;");
    homebuttonPane.setPrefHeight(windowHeight * 0.15);
    
    VisController visualControler = new VisController();
    visualControler.setPane(visPane);
    visualControler.setQuery(suchleiste.getText());
    // iv
    visualControler.setPaneHeight((int) (stage.getHeight() * 0.85));
    visualControler.setPaneWidth((int) stage.getWidth());

    visPane.setCenter(visualControler.startVisualize(tags));
    visPane.setTop(homebuttonPane);
    System.out.println("startVisual fertig");

    Scene visual = new Scene(visPane);
    stage.setScene(visual);
    System.out.println("scene gesetzt");
  }

}
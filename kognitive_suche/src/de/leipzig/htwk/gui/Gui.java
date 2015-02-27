package kognitive_suche.src.de.leipzig.htwk.gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kognitive_suche.src.de.leipzig.htwk.controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Created by hendrik.
 */
public class Gui extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        final String[] bLanguage = {"de"};
        final String[] bWhatSearch = {"web"};
        final int[] start = {1};
        final TextField textFieldSearch = new TextField();
        textFieldSearch.setMinWidth(400);

        /**
         * HBox
         */
        /**
         * Soll das Icon dastellen für den Search Button.
         */
        Image lupe = new Image("file:" + s + "static/icons/lupe.png",20,0,false,false);

        /**
         * Center BorderPane
         */
        final Browser browser = new Browser();
        HBox centerBox = new HBox(browser);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(10,10,10,15));
        centerBox.setSpacing(10);

        /**
         * Left BorderPane
         */
        final Button[] btnLanguage = new Button[2];
        btnLanguage[0] = new Button("de");
        btnLanguage[0].setText("German");
        btnLanguage[0].setStyle("-fx-background-color: #00C02D;");
        btnLanguage[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnLanguage[0].setStyle("-fx-background-color: #00C02D;");
                btnLanguage[1].setStyle("-fx-background: #FFFFFF;");
                bLanguage[0] = "de";

            }
        });
        btnLanguage[1] = new Button("en");
        btnLanguage[1].setText("English");
        btnLanguage[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnLanguage[1].setStyle("-fx-background-color: #00C02D;");
                btnLanguage[0].setStyle("-fx-background: #FFFFFF;");
                bLanguage[0] = "en";
            }
        });

        final Button[] btnWhat = new Button[3];
        btnWhat[0] = new Button("web");
        btnWhat[0].setText("Web");
        btnWhat[0].setStyle("-fx-background-color: #00C02D;");
        btnWhat[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnWhat[0].setStyle("-fx-background-color: #00C02D;");
                btnWhat[1].setStyle("-fx-background: #FFFFFF;");
                btnWhat[2].setStyle("-fx-background: #FFFFFF;");
                bWhatSearch[0] = "web";
            }
        });
        btnWhat[1] = new Button("news");
        btnWhat[1].setText("News");
        btnWhat[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnWhat[1].setStyle("-fx-background-color: #00C02D;");
                btnWhat[0].setStyle("-fx-background: #FFFFFF;");
                btnWhat[2].setStyle("-fx-background: #FFFFFF;");
                bWhatSearch[0] = "news";
            }
        });
        btnWhat[2] = new Button("topics");
        btnWhat[2].setText("Top Stories");
        btnWhat[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnWhat[2].setStyle("-fx-background-color: #00C02D;");
                btnWhat[1].setStyle("-fx-background: #FFFFFF;");
                btnWhat[0].setStyle("-fx-background: #FFFFFF;");
                bWhatSearch[0] = "topics";
            }
        });
        //Trennstrich.
        Separator sep = new Separator();
        sep.setHalignment(HPos.LEFT);

        /**
         * Adden der Verschiedenen Button in die VBox.
         */
        VBox whatPane = new VBox(btnWhat[0], btnWhat[1],btnWhat[2], sep);
        whatPane.setAlignment(Pos.CENTER_LEFT);
        whatPane.setPadding(new Insets(5));
        whatPane.setSpacing(10);

        VBox LanguagePane = new VBox(btnLanguage);
        LanguagePane.setAlignment(Pos.CENTER_LEFT);
        LanguagePane.setPadding(new Insets(0));
        LanguagePane.setSpacing(10);

        Separator sepLeft = new Separator();
        sepLeft.setValignment(VPos.TOP);
        sepLeft.setPadding(new Insets(10));

        VBox checkBoxPane = new VBox(whatPane, LanguagePane, sepLeft);
        checkBoxPane.setAlignment(Pos.TOP_LEFT);
        checkBoxPane.setPadding(new Insets(10,10,10,10));
        checkBoxPane.setSpacing(10);

        /**
         * BottomPane
         */
        Button bmoreResults = new Button("more");
        final Label lCountResults = new Label();
        lCountResults.setText("Results: " + 10);
        bmoreResults.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start[0] += 10;
                lCountResults.setText("Results: " + String.valueOf(start[0]-1));
                searching(bWhatSearch[0],bLanguage[0], textFieldSearch.getText(), start[0]);
                browser.load();
            }
        });

        HBox moreResultsBox = new HBox(lCountResults, bmoreResults);
        moreResultsBox.setAlignment(Pos.BOTTOM_LEFT);
        moreResultsBox.setPadding(new Insets(10));
        moreResultsBox .setSpacing(20);

        Button bExit = new Button("Exit");
        bExit.setAlignment(Pos.CENTER_RIGHT);
        HBox buttonPaneBox = new HBox(bExit);
        buttonPaneBox.setAlignment(Pos.CENTER_RIGHT);
        buttonPaneBox.setPadding(new Insets(5));
        buttonPaneBox.setSpacing(10);
        bExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        HBox bottomBox = new HBox(moreResultsBox, buttonPaneBox);

        bottomBox.setAlignment(Pos.TOP_LEFT);
        bottomBox.setPadding(new Insets(5));

        /**
         * Top BorderPane
         */
        Label label = new Label("Kognitive Suche");
        label.setFont(Font.font("Cambria", 32));
        HBox schrift = new HBox(label);
        schrift.setAlignment(Pos.CENTER);
        schrift.setPadding(new Insets(15,15,15,15));
        schrift.setSpacing(10);

        Button bSearch = new Button("Search");
        HBox entryPane = new HBox(textFieldSearch, bSearch);
        entryPane.setAlignment(Pos.TOP_CENTER);
        entryPane.setPadding(new Insets(15,15,15,50));
        entryPane.setSpacing(10);

        bSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lCountResults.setText("Results: " + 10);
                searching(bWhatSearch[0],bLanguage[0], textFieldSearch.getText(), start[0]);
                browser.load();
            }
        });

        bSearch.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent keyEvent)
            {
                if(keyEvent.getCode() == KeyCode.ENTER)
                {
                    lCountResults.setText("Results: " + 10);
                    searching(bWhatSearch[0],bLanguage[0], textFieldSearch.getText(), start[0]);
                    browser.load();
                }
            }
        });
        Separator sepTop = new Separator();
        sepTop.setHalignment(HPos.CENTER);
        sepTop.setPadding(new Insets(15, 10, 10, 15));

        VBox TopBox = new VBox(schrift, entryPane, sepTop);

        /**
         * BorderPane setting und hinzufügen der pane zur scene.
         */
        BorderPane pane = new BorderPane();
        pane.setTop(TopBox);
        pane.setCenter(centerBox);
        pane.setLeft(checkBoxPane);
        pane.setBottom(bottomBox);
        Scene scene = new Scene(pane);

        /**
         * stage settings
         */
        stage.setTitle("Kognitive Suche");
        stage.centerOnScreen();
        stage.setMinHeight(600.0);
        stage.setMinWidth(700.0);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Ruft den Controller auf, damit die Suchanfrage bearbeitet wird.
     * @param what - Was wird gesucht
     * @param language
     * @param q - Suchwort
     * @param start - Ab welchen Ergebniss aufgeben werden sollen.
     * @return Results
     */
    private kognitive_suche.src.de.leipzig.htwk.controller.Controller Controller = new Controller();
    private void searching(String what, String language, String q, int start){
        Controller.setParameter(language,what,start);
        Controller.queryFaroo(q);
    }

}

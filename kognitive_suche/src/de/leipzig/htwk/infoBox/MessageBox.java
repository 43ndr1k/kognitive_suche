package de.leipzig.htwk.infoBox;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @Autor Hendrik Sawade.
 */

/**
 * Ist die Eingabemaske für die Konfiguationsdatei. Eingabe: Key und URL.
 */
public class MessageBox extends Stage {

    /**
     * MessageBox für Meldungen.
     */

    String infoMessage, titleBar, headerMessage;
    public MessageBox(String infoMessage, String titleBar, String headerMessage) {
        super();
        setTitle(headerMessage);
        this.headerMessage = headerMessage;
        this.infoMessage = infoMessage;
        this.titleBar = titleBar;

    }
    public void run() {

        /**
         * Top Pane
         */
        Label toplabel = new Label(titleBar);
        toplabel.setFont(Font.font("Cambria", 28));
        HBox topBox = new HBox(toplabel);
        topBox.setAlignment(Pos.TOP_LEFT);
        topBox.setPadding(new Insets(15, 15, 15, 15));
        topBox.setSpacing(10);

        /**
         * Center Pane
         */



        Label message = new Label(infoMessage);

        VBox centerBox = new VBox(message);
        centerBox.setAlignment(Pos.TOP_LEFT);
        centerBox.setPadding(new Insets(15,15,15,15));
        centerBox.setSpacing(10);

        /**
         * Botom Pane
         */
        Button bok = new Button("    OK    ");


        HBox BotomBox = new HBox(bok);
        BotomBox.setAlignment(Pos.BOTTOM_RIGHT);
        BotomBox.setPadding(new Insets(15,15,15,15));
        BotomBox.setSpacing(10);

        bok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
                //Platform.exit();
            }
        });

        /**
         * config stage
         */
        BorderPane pane = new BorderPane();
        pane.setTop(topBox);
        pane.setCenter(centerBox);
        pane.setBottom(BotomBox);
        Scene scene = new Scene(pane, 350, 180);
        setScene(scene);
    }

}

package de.leipzig.htwk.faroo.api;

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
public class EingabeMaske extends Stage {

  /**
   * Grafische eingabe Maske der Konfigurationsdatei.
   */
  private String key = "", url;

  public EingabeMaske() {
    super();
    setTitle("Konfigurationsmaske");
  }

  public void run() {

    /**
     * Top Pane
     */
    Label toplabel = new Label("Eingabe der Konfigurationsdaten");
    toplabel.setFont(Font.font("Cambria", 28));
    HBox topBox = new HBox(toplabel);
    topBox.setAlignment(Pos.TOP_LEFT);
    topBox.setPadding(new Insets(15, 15, 15, 15));
    topBox.setSpacing(10);

    /**
     * Center Pane
     */
    final TextField keyTextBox = new TextField();
    keyTextBox.setMaxWidth(300);
    final TextField urlTextBox = new TextField();
    urlTextBox.setMaxWidth(300);
    urlTextBox.setText("http://www.faroo.com/api?");
    keyTextBox.setText("2CJIbhzsHU4nlSqBVZ2OP3fimb4_");
    Label lkey = new Label("Eingabe Key");
    Label lurl = new Label("Eingabe URL");
    VBox centerBox = new VBox(lkey, keyTextBox, lurl, urlTextBox);
    centerBox.setAlignment(Pos.TOP_LEFT);
    centerBox.setPadding(new Insets(15, 15, 15, 15));
    centerBox.setSpacing(10);

    /**
     * Botom Pane
     */
    Button bok = new Button("    ok    ");
    Button babbrechen = new Button("Abbrechen");
    babbrechen.setCancelButton(true);
    HBox botombox = new HBox(bok, babbrechen);
    botombox.setAlignment(Pos.BOTTOM_RIGHT);
    botombox.setPadding(new Insets(15, 15, 15, 15));
    botombox.setSpacing(10);
    babbrechen.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        close();
      }
    });

    bok.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        key = keyTextBox.getText();
        url = urlTextBox.getText();
        close();
      }
    });

    /**
     * config stage
     */
    BorderPane pane = new BorderPane();
    pane.setTop(topBox);
    pane.setCenter(centerBox);
    pane.setBottom(botombox);
    Scene scene = new Scene(pane, 450, 300);
    setScene(scene);
  }

  /**
   * Gibt den API key zurück.
   * 
   * @return key
   */
  public String getkey() {
    return this.key;
  }

  /**
   * Gibt die URL zurück.
   * 
   * @return url
   */
  public String geturl() {
    return this.url;
  }

}

package de.leipzig.htwk.config;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Properties;


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
  private String key = "", url, pfad = "";

  public EingabeMaske() {
    super();
    setTitle("Konfigurationsmaske");
  }

  final TextField pfadBox = new TextField();

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

    pfadBox.setMinWidth(300);
    pfadBox.setMaxWidth(301);
    urlTextBox.setText("http://www.faroo.com/api?");
    keyTextBox.setText("2CJIbhzsHU4nlSqBVZ2OP3fimb4_");
    pfad = os();
    pfadBox.setText(pfad);
    Label lkey = new Label("Eingabe Faroo Key");
    Label lurl = new Label("Eingabe Faroo URL");
    Label lpfad = new Label("Pfad zu Phantomjs");

    Button browse = new Button("browse");
    HBox CenterBox = new HBox( pfadBox, browse);
    CenterBox.setAlignment(Pos.TOP_LEFT);
    CenterBox.setPadding(new Insets(1, 1, 5, 5));
    CenterBox.setSpacing(10);

    VBox centerBox = new VBox(lkey, keyTextBox, lurl, urlTextBox, lpfad, CenterBox);
    centerBox.setAlignment(Pos.TOP_LEFT);
    centerBox.setPadding(new Insets(15, 15, 15, 15));
    centerBox.setSpacing(10);
    browse.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        pfadBrowser();
      }
    });

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
    Scene scene = new Scene(pane, 450, 400);
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

  public String getPfad() {
    return this.pfad;
  }

private void pfadBrowser() {
  FileChooser fileChooser = new FileChooser();
  File file = fileChooser.showOpenDialog(this);
  pfad = file.getAbsolutePath();
  pfadBox.setText(pfad);
}

  /**
   * Ermittelt das Betriebsystem und gibt den Standardpfad zu phantomjs zurück.
   * @return var Standardpfad zu Phantomjs
   */
  private String os() {
    String os = "os.name";
    Properties prop = System.getProperties();
    String system = prop.getProperty(os);
    String var = null;


    switch (system) {
      case "Linux":
        var = "phantomjs/phantomjsLinux.bin";
        break;
      case "Mac OS X":
        var = "phantomjs/phantomjsMac.bin";
        break;
      default:
        var = "phantomjs/phantomjsWin.exe";
        break;
    }
    return var;
  }


}

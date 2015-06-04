/**
 * @author Ivan Ivanikov
 * @param Liste wird erzeugt indem die Waben überschrieben werden. Aufbau wie bei Google leicht und übersichtlich
 * scrollpane hinzugefügt falls Listen zu groß und unübersichtlich werden
 * Anbindung an Suchergebniss von Christian Schmidt
 */

package de.leipzig.htwk.list;

import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.faroo.api.Results;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.Desktop;

import java.net.URI;
import java.util.ArrayList;

public class Listenausgabe {
  private int width;
  private int height;
  private int xpos;
  private int ypos;
  private Controller mController = new Controller();
  public ArrayList<String> tags = new ArrayList<String>();
  public ArrayList<String> url = new ArrayList<String>();
  public ArrayList<String> kwic = new ArrayList<String>();
  public ArrayList<String> title = new ArrayList<String>();

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setLayoutX(int xpos) { this.xpos = xpos; }

  public void setLayoutY(int ypos) { this.ypos = ypos; }

  /**
   * Erstellen der Liste
   *
   * @author Christian Schmidt
   */
  public Listenausgabe(Results results) {
    for (int i = 0; i < results.getResults().size(); i++) {
      kwic.add(results.getResults().get(i).getKwic());
      title.add(results.getResults().get(i).getTitle());
      url.add(results.getResults().get(i).getUrl());
    }

  }

  public ScrollPane ergebnisausgabe() {
    VBox vbox1 = new VBox();
    VBox vbox2;
    BorderPane pane = new BorderPane();
    ScrollPane rol = new ScrollPane();
    Hyperlink[] link = new Hyperlink[50];
    Label[] label1 = new Label[50];
    Label[] label = new Label[25];
    int anzsucherg = (20 > url.size()) ? url.size() : 20; //lässt sich auch auf unter 20 Ergbnisse erweitern und Funktioniert
    
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    for (int k = 0; k < anzsucherg; k++) {
    	  Hyperlink h = new Hyperlink(url.get(k));
          h.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent link) {
            	  try {
                      Desktop.getDesktop().browse(new URI(h.getText()));//so sehen klickbare Links aus
                  } catch (Exception e) {
                  
            }
                System.out.println(url);
                System.out.println(link);
            }
          });
          link[k] = h;
      label1[k] = new Label(kwic.get(k));
      label[k] = new Label(title.get(k));
      vbox2 = new VBox();
      vbox2.setStyle("-fx-border-width: 2;");
      vbox2.setStyle("-fx-border-color: black;");
      vbox2.getChildren().addAll(label[k], label1[k], link[k]);
      vbox1.getChildren().add(vbox2);
      label[k].setMaxSize(600, 300);
      label[k].setWrapText(true);
      label[k].setStyle("-fx-label-padding: 0 0 10 0;");
      label[k].setStyle("-fx-font-weight: bold;");
      label1[k].setWrapText(true);
      label1[k].setStyle("-fx-label-padding: 0 0 0 0;");
    }
    vbox1.setStyle("-fx-border-width: 2;");
    vbox1.setStyle("-fx-border-color: black;");
    pane.getChildren().clear();
    pane.setCenter(vbox1);
    rol.setPrefSize((double) width, (double) height);
    rol.setLayoutX((double) xpos);
    rol.setLayoutY((double)ypos);
    rol.setContent(pane);
    rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    return rol;
  }


}

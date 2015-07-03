/**
 * Klasse erstellt die Listenausgabe, welche rechts in der Visualisierung erscheint.
 * @author Sebastian Hügelmann, Christian Schmidt, Ivan Ivanikov
 */

package de.leipzig.htwk.list;

import de.leipzig.htwk.searchApi.Results;
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

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

public class Listenausgabe {
  private int width;
  private int height;
  private int xpos;
  private int ypos;

  public ArrayList<String> tags = new ArrayList<String>();
  public ArrayList<String> url = new ArrayList<String>();
  public ArrayList<String> kwic = new ArrayList<String>();
  public ArrayList<String> title = new ArrayList<String>();

  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getWidth() {
	  return this.width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setLayoutX(int xpos) { this.xpos = xpos; }

  public void setLayoutY(int ypos) { this.ypos = ypos; }

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
    
  
    for (int k = 0; k < anzsucherg; k++) {
            String linkString = url.get(k);
            Hyperlink h = new Hyperlink(linkString);
            h.setWrapText(true);
            h.setMaxWidth(300);
            h.setStyle("-fx-padding: 0 0 0 0");
            
            if(linkString.charAt(linkString.length()-1) == 'f' 
                && linkString.charAt(linkString.length()-2) == 'd' 
                && linkString.charAt(linkString.length()-3) == 'p' 
                && linkString.charAt(linkString.length()-4) == '.')
            {
              h.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent link) {
                      try {
                          File pdf = new File (linkString);
                          Desktop.getDesktop().open(pdf);//so sehen klickbare Links aus
                      } catch (Exception e) {
                      
                }
                    System.out.println(url);
                    System.out.println(link);
                }
              });
            } else {
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
            }

            

          link[k] = h;
      label1[k] = new Label(kwic.get(k));
      label[k] = new Label(title.get(k));
      vbox2 = new VBox();
      vbox2.getChildren().addAll(label[k], label1[k], link[k]);
      vbox1.getChildren().add(vbox2);
      label[k].setMaxSize(300, 300);
      label[k].setWrapText(true);
      label[k].setStyle("-fx-label-padding: 15 0 5 0;-fx-font-weight: bold;");
      label1[k].setMaxSize(300,300);
      label1[k].setWrapText(true);
      label1[k].setStyle("-fx-label-padding: 0 0 0 0;");
    }

    pane.getChildren().clear();
    pane.setCenter(vbox1);
    pane.setStyle("-fx-background-color:#FFF;");
    rol.setPrefSize((double) width, (double) height);
    rol.setLayoutX((double) xpos);
    rol.setLayoutY((double) ypos);
    rol.setContent(pane);
    rol.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    rol.setStyle("-fx-background-color:#FFF;");
    return rol;
  }


}

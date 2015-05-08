package de.leipzig.htwk.gui;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Webbrowser für die anzeige der Visualisierung.
 */
class Browser extends Region {

  WebView wv = new WebView();
  WebEngine webEngine = wv.getEngine();
  public boolean isSet = false;
  Path currentRelativePath = Paths.get("");
  String s = currentRelativePath.toAbsolutePath().toString();

  public Browser() {
    // apply the styles
    getStyleClass().add("browser");
    // load the web page

    // add the web view to the scene
    getChildren().add(wv);
    wv.setContextMenuEnabled(false);
    java.net.CookieHandler.setDefault(new java.net.CookieManager());

  }

  public void load() {
    try {
      webEngine.load("file:///" + s + "/src/de/leipzig/htwk/gui/Index.html");
      // webEngine.loadContent(genHtml());
    } catch (Exception e) {

    }
  }


  private Node createSpacer() {
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);
    return spacer;
  }

  private static String toURL(String str) {
    try {
      return new URL(str).toExternalForm();
    } catch (MalformedURLException exception) {
      return null;
    }
  }

  @Override
  protected void layoutChildren() {
    double w = getWidth();
    double h = getHeight();
    layoutInArea(wv, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
  }

  @Override
  protected double computePrefWidth(double height) {
    return 1024;
  }

  @Override
  protected double computePrefHeight(double width) {
    return 500;
  }

}

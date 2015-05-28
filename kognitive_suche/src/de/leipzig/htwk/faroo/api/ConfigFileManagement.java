package de.leipzig.htwk.faroo.api;

import javafx.application.Platform;

import java.io.*;
import java.util.Properties;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * List und schreibt in eine Konfigurationsdatei. Die Fatei beinhaltet den Api key und dessen URL.
 */
public class ConfigFileManagement {


  private String key = "", url = "";
  private EingabeMaske em = new EingabeMaske();

  /**
   * Konstruktor Testet ob das Config file existiert.
   */
  public ConfigFileManagement() {



    Properties properties = new Properties();
    BufferedInputStream stream = null;
    try {

      File file = new File("config.properties");
      if (!file.exists()) {
        em.run();
        em.showAndWait();
        if (!em.getkey().trim().isEmpty()) {
          erstelleConfigFile();
        } else {
          Platform.exit();
        }
      }
      stream = new BufferedInputStream(new FileInputStream("config.properties"));
      properties.load(stream);
      stream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    key = properties.getProperty("key");
    url = properties.getProperty("url");

  }

  /**
   * Diese Methode list eine Config file aus und speichert den API key in einer Variable key.
   *
   * @return key
   */
  public String getKey() {

    return this.key;
  }

  /**
   * Diese Methode list eine Config file aus und speichert die API url in einer Variable url.
   * 
   * @return url
   */
  public String geturl() {
    return this.url;
  }

  /**
   * Erstellt ein config file sofern es das nicht gibt.
   */
  private void erstelleConfigFile() {

    String key = "", url = "";
    Writer fw = null;



    try {
      key = em.getkey();
    } catch (Exception e) {
      // Sollte eigentlich nie passieren
      e.printStackTrace();
    }


    try {
      url = em.geturl();
    } catch (Exception e) {
      // Sollte eigentlich nie passieren
      e.printStackTrace();
    }

    try {
      fw = new FileWriter("config.properties");

      fw.write("# Dies ist der Faroo API Key\n");
      fw.write("key = " + key + "\n");

      fw.write("# Dies ist die Faroo API URL\n");
      fw.write("url = " + url + "\n");

      fw.append(System.getProperty("line.separator")); // e.g. "\n"
    } catch (IOException e) {
      System.err.println("Konnte Datei nicht erstellen");
    } finally {
      if (fw != null) {
        try {
          fw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }


  }

}

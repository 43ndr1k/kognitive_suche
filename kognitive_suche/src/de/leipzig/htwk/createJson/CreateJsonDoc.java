package de.leipzig.htwk.createJson;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * CreateJsonDoc generiert eine Json Datei, die die Datenstruktur für die Visualisierung bereit
 * stellt.
 */
import com.google.gson.Gson;
import de.leipzig.htwk.faroo.api.Results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateJsonDoc {

  /**
   * Notwendige Parameter für die Erzeugung des Dokuments.
   */
  FileWriter writer;
  File file;
  Results results = null;

  /**
   * Annahme der Resultsliste und Erstellung der Vertex Objekte. Diese dienen im anschluss für die
   * Erstellung der Json Datei. Die Datei liegt auf der Festplatte -> Daten.json. Die Erstellung des
   * Vertex Objektes ist die Reihenfolge der Baumstruktur, nur das sie von Innen nach Außen abläuft.
   * 
   * @param results Resultsliste
   */
  public CreateJsonDoc(String query, Results results) {
    this.results = results;
    Gson gson = new Gson();
    Vertex verT = new Vertex(query);
    for (int i = 0; i < results.getResults().size(); i++) {
      Vertex url = new Vertex(results.getResults().get(i).getUrl());
      Vertex domain = new Vertex(results.getResults().get(i).getDomain());
      domain.addVertex(url);
      Vertex title = new Vertex(results.getResults().get(i).getTitle());
      title.addVertex(domain);
      verT.addVertex(title);
    }
    String json = gson.toJson(verT);
    try {
      // File anlegen
      writer = new FileWriter("src/de/leipzig/htwk/gui/Daten.json");
      writer.write(json);
      writer.flush();
      // Schließt den Stream
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

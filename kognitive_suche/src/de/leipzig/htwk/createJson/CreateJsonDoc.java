package de.leipzig.htwk.createJson;
/**
 * Created by hendrik.
 */
import com.google.gson.Gson;
import de.leipzig.htwk.faroo.api.Results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateJsonDoc {

    FileWriter writer;
    File file;
    Results k = null;
    /**
     * Annahme der Resultsliste und Erstellung der Vertex Objekte. Diese dienen im anschluss für die
     * Erstellung der Json Datei. Die Datei liegt auf der
     * Festplatte -> Daten.json.
     * Die Erstellung des Vertex Objektes ist die Reihenfolge der Baumstruktur, nur das sie von Innen nach Außen abläuft.
     * @param k Resultsliste
     */
    public CreateJsonDoc(String query, Results k){
        this.k = k;
        Gson gson = new Gson();
        Vertex obj = new Vertex(query);
        for(int i = 0; i < k.getResults().size();i++){
            Vertex url = new Vertex(k.getResults().get(i).getUrl());
            Vertex domain = new Vertex(k.getResults().get(i).getDomain());
            domain.addVertex(url);
            Vertex title = new Vertex(k.getResults().get(i).getTitle());
            title.addVertex(domain);
            obj.addVertex(title);
        }
        String json = gson.toJson(obj);
        try {
        // File anlegen
            writer = new FileWriter("kognitive_suche/src/de/leipzig/htwk/gui/Daten.json");
            writer.write(json);
            writer.flush();
            // Schließt den Stream
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



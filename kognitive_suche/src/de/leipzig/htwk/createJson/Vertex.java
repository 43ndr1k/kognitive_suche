package de.leipzig.htwk.createJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hendrik.
 */

/**
 * Datenstrukturobjekt, aus dieser Struktur wird das Json File generiert.
 * Beinhaltet eine Liste die den Namen des  Konoten enthält und deren Kinder. Die Kinder sind wiederum eine Liste
 * mit dem Vertex Objekt.
 */
public class Vertex {

    private List<Vertex> children = new ArrayList<Vertex>();
    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public void addVertex(Vertex v) {
        children.add(v);
    }

    public List<Vertex> getChildern(){
        return children;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Vertex [name=" + this.name + "childern=" + children + "]";
    }
}

package kognitive_suche.src.komplexeSuche;

import kognitive_suche.src.de.leipzig.htwk.faroo.api.Results;

/**
 * Created by hendrik.
 */

/**
 * Diese Klasse dient nur der Simulation des komplexen Suchalgorthmusses. Also eine Dummy Klasse.
 */
public class Kommunikation {


    private Results objektList;
    private String query;

    public Kommunikation(Results objektList, String query){
        this.objektList = objektList;
        this.query = query;
    }

    public  Results getObjectList(){
        return objektList;
    }

    public String getQuery(){
        return query;
    }

}

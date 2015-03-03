package de.leipzig.htwk.controller;

import de.leipzig.htwk.faroo.api.APIExecption;
import de.leipzig.htwk.faroo.api.Api;
import de.leipzig.htwk.faroo.api.ConfigFileManagement;
import de.leipzig.htwk.faroo.api.Results;
import de.leipzig.htwk.createJson.CreateJsonDoc;
import simpleAlgorithm.*;

import java.util.ArrayList;
/**
 * @Autor Hendrik Sawade.
 */

/**
 * Der Controller dient zur Vermittlung. Er wickelt die Kommsunikation zwischen den einzelenen Klassen ab.
 */
public class Controller {

    private String language, src;
    private int start = 1;
    private String key,url;
    private Results r;
    private String query;
    /**
     * Ruft das Konfiguationsfile ab. In dieser steht der Faroo Key und die  Faroo API URL.
     */
    public Controller(){
        ConfigFileManagement config = new ConfigFileManagement();
        this.key = config.getKey();
        this.url = config.geturl();
    }

    /**
     * Parameter für die Faroo API
     * @param _src Welche Informationen möchte man haben
     * @param s Startwert ab welchem Suchergebnis man Ergebnisse haben möchte.
     */
    public void setParameter(String l, String _src, int s){
        this.language = l;
        this.src = _src;
        this.start = s;
    }

    /**
     * Die Suchanfrage an Faroo, diese wird von der GUI aufgerufen.
     * @param pQuery
     * @return Results
     */
    public void queryFaroo(String pQuery) {
        Api api = new Api(key, url);
        setQuery(pQuery);
        try {
            Results r = api.query(this.start,pQuery,this.language,src);
            setResultList(r);
            createDocVisual(r, pQuery);

        } catch (APIExecption apiExecption) {
            apiExecption.printStackTrace();
        }
    }

    /**
     * Setzt die Results Liste temporär für den simple Algorithmus.
     * @param r - Results Liste
     */
    private void setResultList(Results r){
        this.r = r;
    }

    /**
     * Stellt die Results Liste zur Verfügung.
     * @return r Results Liste
     */
    public Results getResultList(){
        return this.r;
    }

    /**
     * Setzt das Suchwort
     * @param q query
     */
    private void setQuery(String q){
        this.query = q;
    }
    /**
     * Gibt das Suchwort zurück.
     * @return query
     */
    public String getQuery(){
        return this.query;
    }
    /**
     * Aufruf der berechnung der Komplexen Suche.
     * @param List Results Liste
     */
    private void createDocVisual(Results List, String query){
        CreateJsonDoc c = new CreateJsonDoc(query, List);
    }

    /**
     * Führt den Simple Such Algorithmus aus
     * @return Tag Liste
     */
    public ArrayList<SimAlgTags> GetTags(){
         ObBearbeitung uebergabe = new ObBearbeitung();
        return uebergabe.annahme(this.r);

    }
}


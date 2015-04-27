package de.leipzig.htwk.controller;

import de.leipzig.htwk.faroo.api.APIExecption;
import de.leipzig.htwk.faroo.api.Api;
import de.leipzig.htwk.faroo.api.ConfigFileManagement;
import de.leipzig.htwk.faroo.api.Results;
import de.leipzig.htwk.tests.VisualTest;
import de.leipzig.htwk.createJson.CreateJsonDoc;
import gui.GUI;
import simple.algorithm.*;

import java.util.ArrayList;

import komplexe.suche.TagObjectList;
/**
 * @Autor Hendrik Sawade.
 */

/**
 * Der Controller dient zur Vermittlung. Er wickelt die Kommsunikation zwischen den einzelenen Klassen ab.
 */
public class Controller {

    /**
     * Die Parameter für die weitergabe der einzelden Informationen.
     */
    private String language, src;
    private int start = 1;
    private String key,url;
    private Results results;
    private String query;
    private TagObjectList tags;
    private GUI gui;
    /**
     * Ruft das Konfiguationsfile ab. In dieser steht der Faroo Key und die  Faroo API URL.
     */
    public Controller() {
        ConfigFileManagement config = new ConfigFileManagement();
        this.key = config.getKey();
        this.url = config.geturl();
    }

    /**
     * Parameter für die Faroo API
     * @param _src Welche Informationen möchte man haben
     * @param s Startwert ab welchem Suchergebnis man Ergebnisse haben möchte.
     * @param l Welche Sprache
     */
    public void setParameter(String l, String _src, int s) {
        this.language = l;
        this.src = _src;
        this.start = s;
    }

    /**
     * Die Suchanfrage an Faroo, diese wird von der GUI aufgerufen.
     * @param pQuery Suchwort
     * @return Results Liste mit den Ergebnisse.
     */
    public void queryFaroo(String pQuery) {
        Api api = new Api(key, url);
        setQuery(pQuery);
        try {
            Results results = api.query(this.start,pQuery,this.language,src);
            setResultList(results);
            createDocVisual(results, pQuery);

        } catch (APIExecption apiExecption) {
            apiExecption.printStackTrace();
        }
    }

    /**
     * Setzt die Results Liste temporär für den simple Algorithmus.
     * @param results - Results Liste
     */
    private void setResultList(Results results) {
        this.results = results;
    }

    /**
     * Stellt die Results Liste zur Verfügung.
     * @return results Results Liste
     */
    public Results getResultList() {
        return this.results;
    }

    /**
     * Setzt das Suchwort
     * @param query query
     */
    private void setQuery(String query) {
        this.query = query;
    }
    /**
     * Gibt das Suchwort zurück.
     * @return query
     */
    public String getQuery() {
        return this.query;
    }
    /**
     * Aufruf der berechnung der Komplexen Suche.
     * @param list Results Liste
     * @param query Suchwort
     */
    private void createDocVisual(Results list, String query) {
        CreateJsonDoc c = new CreateJsonDoc(query, list);
    }

    /**
     * Führt den Simple Such Algorithmus aus
     * @return Tag Liste
     */
    public ArrayList<SimAlgTags> getTags() {
        ObBearbeitung uebergabe = new ObBearbeitung();
        return uebergabe.annahme(this.results);

    }
    
//    public TagObjectList getTagsVisualtest() {
//    	VisualTest visual = new VisualTest();
//    	TagObjectList tags = visual.getTags();
//    	return tags;
//    }
//    
//    public TagObjectList setTag(TagObjectList Tags){
//    	this.tags = Tags;
//    	return tags;
//    }
    
    public void initVisual(){
    	
    	//tags = getTagsVisualtest();
    	VisualTest tmp = new VisualTest();
        TagObjectList tags = tmp.getTags();
        
        gui.startVisual(tags);
        
    }

	public void setGUI(GUI gui) {
		this.gui = gui;
	}
}


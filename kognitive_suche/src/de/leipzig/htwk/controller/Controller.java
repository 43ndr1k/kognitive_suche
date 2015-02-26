package kognitive_suche.src.de.leipzig.htwk.controller;

import kognitive_suche.src.de.leipzig.htwk.faroo.api.APIExecption;
import kognitive_suche.src.de.leipzig.htwk.faroo.api.Api;
import kognitive_suche.src.de.leipzig.htwk.faroo.api.ConfigFileManagement;
import kognitive_suche.src.de.leipzig.htwk.faroo.api.Results;
import kognitive_suche.src.de.leipzig.htwk.createJson.CreateJsonDoc;
import kognitive_suche.src.komplexeSuche.Kommunikation;

/**
 * Created by hendrik.
 */

/**
 * Der Controller dient zur Vermittlung. Er wickelt die Kommsunikation zwischen den einzelenen Klassen ab.
 */
public class Controller {

    private String language, src;
    private int start = 1;
    private String key,url;


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
     * @param l - Language
     * @param _src - Welche Informationen möchte man haben
     * @param s - Startwert ab welchem Suchergebnis man Ergebnisse haben möchte.
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
        try {
            KomplexeSuche(api.query(this.start,pQuery,this.language,src), pQuery);
        } catch (APIExecption apiExecption) {
            apiExecption.printStackTrace();
        }

    }

    /**
     * Aufruf der berechnung der Komplexen Suche.
     * @param List - Results Liste
     */
    private void KomplexeSuche(Results List, String query){
        Kommunikation ks = new Kommunikation(List, query);
        CreateJsonDoc c = new CreateJsonDoc(ks);

    }




}

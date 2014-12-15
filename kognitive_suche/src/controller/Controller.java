package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import main.APIResults;
import main.Result;
import Faroo.Result;
import pdfBoxAcces.PDFBoxAccesControler;
import pdfBoxAcces.PDFDocument;
import Faroo.API;
import Faroo.ConfigFileManagement;
import Faroo.APIResults;

/**
 * Dummy Controller<br>
 * TODO: Create Dummy methods to create a simple workflow
 * 
 * The following methods are suggestions only
 * @author tim.delle@stud.htwk-leipzig.de
 */
public class Controller {

	/* CONSTANTS */
	private int test1 = 1; 
    static String pdfbox_path = "Data/PDFBox/pdfbox.jar"; //absolute Path to pdfbox.jar

	/* VARIABLES */
	private String Suchstring = null; // Die Eingabe an Farroo und pdfbox
	private String Config = null; //Erstmal nur zum Test, wird entweder 1 Array oder mehrere Variablen
	
	
	/* CONSTRUCTOR */
	public Controller(String Suchstring, String Config){//Nen Test ob wir Netz haben wï¿½re vlt. noch nï¿½tzlich
		this.Suchstring = Suchstring;
		this.Config = Config;
		
	}
	
	
	
	/* CODE - Auswahl Farroo, PDFBox, Einfacher Suchalg., etc. */
	
	//Durchläufe zaehlen ?
	
	/* METHODS */
	/*public void startSearch(test1){
		switch (test1) {
	
			case1://Farroo-----------------------------------------------------------------------------------
			//Übergabe von Config an Farroo scheint Hendrik schon direkt gemacht zu haben ???
		 	
				API api = new API(config.getKey());
				api.query(Suchstring);
				ConfigFileManagement config = new ConfigFileManagement();

			try {
				APIResults apiResults = api.getResult();
				ArrayList<Result> results = apiResults.getResultsList();
				



			} catch (Exception e) {
			e.printStackTrace();
			}
			break;
			
			case2://Pdfbox-----------------------------------------------------------------------------
				//PDFbox bekommt keine Eingabe von Controller ? - läuft also extern, was für Daten kommen zurück
				public static ArrayList<Result> queryPdfBox(){  // Gleiches Format wie bei Hendrik formatieren oder andersrum ist ja bis jetzt alles String
	    		PDFBoxAccesControler PDFBoxAcces = new PDFBoxAccesControler(pdfbox_path); //Runs the PDFBox tool. CARE: It takes up to 30 seconds until PDFBox is ready.
	    		return PDFBoxAcces.getDocKeywords();
	    		}
				break;
			case3://----------------------------------------------------------------------------------------
				// Aufruf vom SuchAlg. ?
		 		* übergabe von ArrayList<Result> oder anders formatierter Liste
				break;
			case4: 
				// Nochwas ?
				break;
		}
			*/
		

	
	
	private void getconfig(){
		//hier soll Die Config die Config der GUI erhalten - ob nötig kommt später
	}
	//------------------------------------------------
	/*public ArrayList<Result> getAData(){
		
		ArrayList<Result> AllData = results;
		
		return AllData;
	}*/
	
	private Object evaluateGUI(Object pObject){
		return null;
	}
	
	private Object queryFaroo(Object pObject){
		return null;
	}
	
	public static ArrayList<PDFDocument> queryPdfBox(){
	    PDFBoxAccesControler PDFBoxAcces = new PDFBoxAccesControler(pdfbox_path); //Runs the PDFBox tool. CARE: It takes up to 30 seconds until PDFBox is ready.
	    return PDFBoxAcces.getDocKeywords();
	    }
	
	private Object startSimpleAlgorithmn(Object pObject){
		return null;
	}
	
	private Object startComplexAlgorithmn(Object pObject){
		return null;
	}
	
}

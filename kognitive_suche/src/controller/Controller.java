package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;





import komplexeSuche.searchAlgorithm;
import komplexeSuche.suchobjekt;
import komplexeSuche.tags;
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
	
	//Durchlï¿½ufe zaehlen ?
	
	/* METHODS */
	/*public void startSearch(test1){
		switch (test1) {
	
			case1://Farroo-----------------------------------------------------------------------------------
			//ï¿½bergabe von Config an Farroo scheint Hendrik schon direkt gemacht zu haben ???
		 	
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
				//PDFbox bekommt keine Eingabe von Controller ? - lï¿½uft also extern, was fï¿½r Daten kommen zurï¿½ck
				 // Antwort von Fabian:
				 // Es werden keine Daten Ã¼bergeben. Das Stand-Alone Programm PDF-Box wird ausgefÃ¼hrt. Da drinn kann man aussuchen welche PDFs eingelesen werden sollen.
				 // Die Keywords von den ausgewÃ¤hlten PDFs werden dann zurÃ¼ck gegeben.
				 // RÃ¼ckgabe Typ ist ArrayList<PDFDocument>. Das ist ein Objekt was sowohl den Namen des PDF-Files enthÃ¤lt ( String, getDocname()) 
				 // als auch eine ArrayList<PDFKeyword>. Diese ArrayList enthÃ¤lt alle Keywords + dessen weight. (String getTerm(), float getWeight())
	
				public static ArrayList<Result> queryPdfBox(){  // Gleiches Format wie bei Hendrik formatieren oder andersrum ist ja bis jetzt alles String
	    		PDFBoxAccesControler PDFBoxAcces = new PDFBoxAccesControler(pdfbox_path); //Runs the PDFBox tool. CARE: It takes up to 30 seconds until PDFBox is ready.
	    		return PDFBoxAcces.getDocKeywords();
	    		}
				break;
			case3://----------------------------------------------------------------------------------------
				// Aufruf vom SuchAlg. ?
		 		* ï¿½bergabe von ArrayList<Result> oder anders formatierter Liste
				break;
			case4: 
				// Nochwas ?
				break;
		}
			*/
		

	
	
	private void getconfig(){
		//hier soll Die Config die Config der GUI erhalten - ob nï¿½tig kommt spï¿½ter
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
	
	private ArrayList<tags> startComplexAlgorithmn(Object pObject){
	  suchobjekt[] ergebnis =new suchobjekt[2];
      ergebnis[0] = new suchobjekt("https://portal.imn.htwk-leipzig.de/fakultaet/weicker","Karsten Weicker, Prof. Dr. rer. nat. — Fakultät Informatik","Prof. Dr. rer. nat. Karsten Weicker Karsten Weicker, Prof. Dr. rer. nat. Leitungen und Ämter Studienfachberater (Informatik) Studienkommission Informatik (Vorsitzender) Studiendekan (Informatik) Fakultätsrat (Mitglied ) Aufgabenbereiche Lehrgebiet: Praktische Informatik Kontaktinformationen Sprechzeit: nach Vereinbarung Z410  Gustav-Freytag-Str. 42A 04277 Leipzig karsten.weicker [at] htwk-leipzig.de  +49 (0) 341 3076-6395 Lebenslauf1990-1997 Studium der Informatik mit Nebenfach Mathematik, Universität Stuttgart 1995-1997    Studium der Computer Science, University of Massachusetts in Amherst Gutachter für folgende Zeitschriften: IEEE Transactions on Evolutionary Computation, Evolutionary Computation Journal, ACM Computing Surveys, Information Processing Letters, Softcomputing Journal, Genetic Programming and Evolvable Machines");
      ergebnis[1] = new suchobjekt("http://www.weicker.info/","Informationen über die Weicker-Familie","www.weicker.info Informationen über die Weicker-Familie   Karsten Weicker [Filme] Die vollständige Sammlung der Filme, die ich auf großer Leinwand gesehen habe - bald wieder online [Musik]  Eine grobe Sammlung der memorizable live acts [Evolutionäre Algorithmen]    Das Lehrbuch in der 2. Auflage");
      searchAlgorithm suche = new searchAlgorithm();
      
      
		return suche.kognitivSuchen(ergebnis, "Karsten Weicker");
	}
	
}

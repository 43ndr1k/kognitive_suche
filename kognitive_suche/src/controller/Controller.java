package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import komplexeSuche.searchAlgorithm;
import komplexeSuche.suchobjekt;
import komplexeSuche.Keywords;




import komplexeSuche.searchAlgorithm;
import komplexeSuche.suchobjekt;
import komplexeSuche.tags;
import Faroo.Result;
import Faroo.API;
import Faroo.ConfigFileManagement;
import Faroo.APIResults;
import pdfBoxAcces.PDFBoxAccesControler;
import pdfBoxAcces.PDFDocument;
import simpleAlgorithm.ObBearbeitung;
import simpleAlgorithm.SimAlgTags;


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
	//Farroo:
	private ArrayList<String> Title = null;
	private ArrayList<String> Kwic = null; //Keywords
	private ArrayList<String> Author = null;
	private ArrayList<String> Votes = null;
	private ArrayList<String> IsNews = null;
	private ArrayList<String> URL = null;
	private ArrayList<String> Domain = null;
	private ArrayList<String> ImageUrl = null;
	private ArrayList<String> FirstIndexed = null;
	private ArrayList<String> FirstPublished = null;
	//PDFBox:
	private ArrayList<String> DocName = null;
	private ArrayList<String> Keywords1 = null;
	private ArrayList<String> Keywords2 = null;
	private ArrayList<String> Keywords3 = null;
	private ArrayList<Float> Weight1 = null;
	private ArrayList<Float> Weight2 = null;
	private ArrayList<Float> Weight3 = null;
	//Prim. SuchAlg
	String ausgabe = null;
	
	
	/* CONSTRUCTOR */
	public Controller(String Suchstring, String Config){//Nen Test ob wir Netz haben w�re vlt. noch n�tzlich
		this.Suchstring = Suchstring;
		this.Config = Config;	
	}
	public Controller(String Config){//Nen Test ob wir Netz haben w�re vlt. noch n�tzlich
		this.Config = Config;
	}
	/* METHODS */
	public void startSearchF(String Suchtag){
		//Listen vorher Löschen
			ConfigFileManagement config = new ConfigFileManagement();
			API api = new API(config.getKey());	
			try {
				api.query(Suchtag, Config);
				APIResults apiResults = api.getResult();
				ArrayList<Result> results = apiResults.getResultsList();
				Title = new ArrayList<String>();
				Kwic = new ArrayList<String>();
				Author = new ArrayList<String>();
				Votes = new ArrayList<String>();
				IsNews = new ArrayList<String>();
				URL = new ArrayList<String>();
				Domain = new ArrayList<String>();
				ImageUrl= new ArrayList<String>();
				FirstIndexed = new ArrayList<String>();
				FirstPublished = new ArrayList<String>();
				
				for(int i = 0; i < results.size(); i++) {
					Author.add(results.get(i).getAuthor());
					Domain.add(results.get(i).getDomain());
					FirstIndexed.add(results.get(i).getFirstIndexed());
					FirstPublished.add(results.get(i).getFirstPublished());
					ImageUrl.add(results.get(i).getImageUrl());
					IsNews.add(results.get(i).getIsNews());
					Kwic.add(results.get(i).getKwic());
					Title.add(results.get(i).getTitle());
					URL.add(results.get(i).getUrl());
					Votes.add(results.get(i).getVotes());
				}
				ObBearbeitung uebergabe = new ObBearbeitung();
			    ArrayList<SimAlgTags> treffer = new ArrayList<SimAlgTags>();
			    treffer = uebergabe.annahme(results);
			    ArrayList<String> addresses;
			    for (int i = 0; i < treffer.size(); i++) {
			      ausgabe = treffer.get(i).gettag();
			      System.out.println(ausgabe);
			      addresses = treffer.get(i).getlinks();
			      System.out.println(addresses);

			    }
				
			} catch (Exception e) {
			e.printStackTrace();
			}
			
		    
	}
	public void startSearchP(){	//Listen vorher Löschen
				// PDFbox bekommt keine Eingabe von Controller ? - läuft also extern, was f�r Daten kommen zurück
				// Antwort von Fabian:
				// Es werden keine Daten übergeben. Das Stand-Alone Programm PDF-Box wird ausgeführt. Da drinn kann man aussuchen welche PDFs eingelesen werden sollen.
				// Die Keywords von den ausgewählten PDFs werden dann zurück gegeben.
				// Rückgabe Typ ist ArrayList<PDFDocument>. Das ist ein Objekt was sowohl den Namen des PDF-Files enthält ( String, getDocname()) 
				// als auch eine ArrayList<PDFKeyword>. Diese ArrayList enthält alle Keywords + dessen weight. (String getTerm(), float getWeight())
				// Gleiches Format wie bei Hendrik formatieren oder andersrum ist ja bis jetzt alles String
	    		// Runs the PDFBox tool. CARE: It takes up to 30 seconds until PDFBox is ready.
		DocName = new ArrayList<String>();
		Keywords1 = new ArrayList<String>();
		Keywords2 = new ArrayList<String>();
		Keywords3 = new ArrayList<String>();
		Weight1 = new ArrayList<Float>();
		Weight2 = new ArrayList<Float>();
		Weight3 = new ArrayList<Float>();
		
		
				queryPdfBox();
	    		ArrayList<PDFDocument> PDFDocs = new ArrayList<PDFDocument>();
	            PDFDocs = Controller.queryPdfBox();
	            for(int i = 0; i < PDFDocs.size(); i++)
	            {
	            	DocName.add(PDFDocs.get(i).getDocname());
	            	Keywords1.add(PDFDocs.get(i).getKeywords().get(0).getTerm());
	            	Keywords2.add(PDFDocs.get(i).getKeywords().get(0).getTerm());
	            	Keywords3.add(PDFDocs.get(i).getKeywords().get(0).getTerm());
	            	Weight1.add(PDFDocs.get(i).getKeywords().get(0).getWeight());
	            	Weight2.add(PDFDocs.get(i).getKeywords().get(0).getWeight());
	            	Weight3.add(PDFDocs.get(i).getKeywords().get(0).getWeight());
	            	
	            }
	            /*
	            ObBearbeitung uebergabe = new ObBearbeitung();
	            ArrayList<SimAlgTags> treffer = new ArrayList<SimAlgTags>();
	            treffer = uebergabe.annahme(results);
	            String ausgabe;
	            ArrayList<String> addresses;
	            for (int i = 0; i < treffer.size(); i++) {
	              ausgabe = treffer.get(i).gettag();
	              System.out.println(ausgabe);
	              addresses = treffer.get(i).getlinks();
	              System.out.println(addresses);
	              */
	    		
	}
	/*
	private void getconfig(){
		//hier soll Die Config die Config der GUI erhalten - ob n�tig kommt sp�ter
	}*/
	//------------------------------------------------
	public ArrayList<String> getAuthor(){

		return Author;
	}
	public ArrayList<String> getDomain(){

		return Domain;
	}
	public ArrayList<String> getFirstIndexed(){

		return FirstIndexed;
	}
	public ArrayList<String> getFirstPublished(){

		return FirstPublished;
	}
	public ArrayList<String> getImageUrl(){

		return ImageUrl;
	}
	public ArrayList<String> getIsNews(){

		return IsNews;
	}
	public ArrayList<String> getKwic(){

		return Kwic;
	}
	public ArrayList<String> getTitle(){

		return Title;
	}
	public ArrayList<String> getURL(){

		return URL;
	}
	public ArrayList<String> getVotes(){

		return Votes;
	}
	public ArrayList<String> getDocName(){

		return DocName;
	}
	public ArrayList<String> getKeywords1(){

		return Keywords1;
	}
	public ArrayList<String> getKeywords2(){

		return Keywords2;
	}
	public ArrayList<String> getKeywords3(){

		return Keywords3;
	}
	public ArrayList<Float> getWeight1(){

		return Weight1;
	}
	public ArrayList<Float> getWeight2(){

		return Weight2;
	}
	public ArrayList<Float> getWeight3(){
		return Weight3;
	}
	public String getTags(){
		return ausgabe;
	}

	
	
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
	
	private ArrayList<SimAlgTags> startsimpleAlgorithmn(Object pObject){
	
		ObBearbeitung suche = new ObBearbeitung();		
		
		return suche.annahme(null);
	}
	
	private ArrayList<tags> startComplexAlgorithmn(Object pObject){
	  suchobjekt[] ergebnis =new suchobjekt[2];
      ergebnis[0] = new suchobjekt("https://portal.imn.htwk-leipzig.de/fakultaet/weicker","Karsten Weicker, Prof. Dr. rer. nat. � Fakult�t Informatik","Prof. Dr. rer. nat. Karsten Weicker Karsten Weicker, Prof. Dr. rer. nat. Leitungen und �mter Studienfachberater (Informatik) Studienkommission Informatik (Vorsitzender) Studiendekan (Informatik) Fakult�tsrat (Mitglied ) Aufgabenbereiche Lehrgebiet: Praktische Informatik Kontaktinformationen Sprechzeit: nach Vereinbarung Z410  Gustav-Freytag-Str. 42A 04277 Leipzig karsten.weicker [at] htwk-leipzig.de  +49 (0) 341 3076-6395 Lebenslauf1990-1997 Studium der Informatik mit Nebenfach Mathematik, Universit�t Stuttgart 1995-1997    Studium der Computer Science, University of Massachusetts in Amherst Gutachter f�r folgende Zeitschriften: IEEE Transactions on Evolutionary Computation, Evolutionary Computation Journal, ACM Computing Surveys, Information Processing Letters, Softcomputing Journal, Genetic Programming and Evolvable Machines");
      ergebnis[1] = new suchobjekt("http://www.weicker.info/","Informationen �ber die Weicker-Familie","www.weicker.info Informationen über die Weicker-Familie   Karsten Weicker [Filme] Die vollst�ndige Sammlung der Filme, die ich auf gro�er Leinwand gesehen habe - bald wieder online [Musik]  Eine grobe Sammlung der memorizable live acts [Evolution�re Algorithmen]    Das Lehrbuch in der 2. Auflage");

      ergebnis[1] = new suchobjekt("http://www.weicker.info/","Informationen �ber die Weicker-Familie","www.weicker.info Informationen �ber die Weicker-Familie   Karsten Weicker [Filme] Die vollst�ndige Sammlung der Filme, die ich auf gro�er Leinwand gesehen habe - bald wieder online [Musik]  Eine grobe Sammlung der memorizable live acts [Evolution�re Algorithmen]    Das Lehrbuch in der 2. Auflage");

      searchAlgorithm suche = new searchAlgorithm();
      
      
		return suche.kognitivSuchen(ergebnis, "Karsten Weicker");
	}
	
}

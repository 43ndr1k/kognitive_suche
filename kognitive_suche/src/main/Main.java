package main;
import java.util.ArrayList;
import java.util.HashMap;

import controller.Controller;
import pdfBoxAcces.PDFBoxAccesControler;
import pdfBoxAcces.PDFDocument;
import Faroo.API;
import Faroo.ConfigFileManagement;
import Faroo.APIResults;

public class Main {


	/*********************************************************************************************
	 * *******************************************************************************************
	 *
	 * Wichtig!!!
	 * Die Main Klasse
	 * Bevor das Projekt ausgeführt werden kann muss eine config.properties file angelegt werden.
	 * Mit dem Inhalt:
	 *
	 * key = 2CJIbhzsHU4nlSqBVZ2OP3fimb4_
	 *
	 * Die Abfrage für den Key wird in der Console erledigt!
	 *
	 * *******************************************************************************************
	 * *******************************************************************************************
	 *
	 * @param args
	 * 
	 */
	

	public static void main(String[] args) {
		

		// farooTest(); //Aufrufen um Faroo zu testen
	  
	     pdfBoxTest(); //Aufrufen um PDFBox zu Testen
		
	  
	  
	}

 
  private static void pdfBoxTest() {
    // TODO Auto-generated method stub
    /**
     * Demonstriert  den Zugriff auf die PDFBox. Das Programm wird gestarete (das dauert ca. 15 Sekunden) und kann dann vom Nutzer wie gewohnt genutzt werden. 
     * Nach dem schlie�en der PDF Box wird in der Konsole die Anzahl der eingelesenen PDFs angezeigt, sowie die Namen der PDFs und die Anzahl der gefundenen Keywords.
     * 
     * !Achtung durch das ausf�hren der PDFBox werden im kognitive_suche Ordner 2 neue Ordner (index und Database) mit verschiedenen Files angelegt. Um Probleme mit Git zu vermeiden, 
     * sollten diese Ordner vor dem n�chsten commit wieder gel�scht werden!
     */
    
    ArrayList<PDFDocument> PDFDocs = new ArrayList<PDFDocument>();
        PDFDocs = Controller.queryPdfBox();
    
    System.out.println("Es wurden " + PDFDocs.size() + " PDFs eingelesen." );

    for(int i = 0; i < PDFDocs.size(); i++)
      System.out.println("In " + PDFDocs.get(i).getDocname() + " wurden " + PDFDocs.get(i).getKeywords().size() + " Keywords gefunden");
    
  }

  private static void farooTest() {
    /**
     * Demonstriert das Aufrufen der FAROO API und die Ausgabe auf der Konsole
     * Der erste Parameter ist der Faroo API key
     * Mit der Methode query wird die Suchanfrage eingeleitet. Dort übergibt man das Suchwort. Dies ist die einfachste
     * Methode, des weiteren ist es möglich in der Methode query weitere Parameter zu übergeben. (Noch nicht implementiert)
     *
     * Mit der Methode api.getCompleteResults werden alle Ergebnisse die die Suchanfrage liefert zurückgegeben und
     * in eine ArrayList gespeichert. über result.get kann man dann auf die Einzelden Tags zugreifen und sich diese
     * Ausgeben lassen.
     *
     * Die Anweisungen müssen in einem try catch Block stehen da die Methode eine Exeption liefert, wenn es Probleme
     * gibt seitens des Verbindungsaufbaus oder ähnliches.
     *
     *
     * Falls die Methode "query" mit einer bestimmten kombination an Parametern nicht existiert, kann man sie leich implementieren.
     *
     */
    
    ConfigFileManagement config = new ConfigFileManagement();

		API api = new API(config.getKey());

		try {

			api.query("Hallo Welt?", "de");
			api.query("oma", "de", true);
			//api.query("hallo");
			//api.query("foo war");
			//api.query("&&");
			//api.query("Foäöü");
			//api.query("mama");
			//api.query("Huibu");


			APIResults apiResults = api.getResult();
			ArrayList<HashMap<String,String>> results = apiResults.getResultsList();

			for(HashMap<String,String> result: results){
					System.out.println(result.get("title"));
					System.out.println(result.get("url"));
					System.out.println(result.get("domain"));
					System.out.println(result.get("imageUrl"));
					System.out.println(result.get("firstIndexed"));
					System.out.println(result.get("firstPublished"));
					System.out.println(result.get("kwic"));
					System.out.println(result.get("author"));
					System.out.println(result.get("votes"));
					System.out.println(result.get("isNews"));
			}

			} catch (Exception e) {
			e.printStackTrace();
		}
  }

}


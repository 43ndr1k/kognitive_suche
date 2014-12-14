package main;
import java.util.ArrayList;
import java.util.HashMap;

import Faroo.Result;
import controller.Controller;

import pdfBoxAcces.PDFBoxAccesControler;
import pdfBoxAcces.PDFDocument;

import Faroo.API;
import Faroo.ConfigFileManagement;
import Faroo.APIResults;

import GUI.*;

public class Main {


	/*********************************************************************************************
	 * *******************************************************************************************
	 *
	 * Wichtig!!!
	 * Die Main Klasse
	 * Bevor das Projekt ausgefÃ¼hrt werden kann muss eine config.properties file angelegt werden.
	 * Dies wird automatisch erzeugt! Falls dies der Fall ist wird der Key in der Konsole abgefragt!
	 * Mit dem Inhalt:
	 *
	 *
	 * key = 2CJIbhzsHU4nlSqBVZ2OP3fimb4_
	 *
	 *
	 *
	 * *******************************************************************************************
	 * *******************************************************************************************
	 *
	 * @param args
	 * 
	 */
	

	public static void main(String[] args) {
		

		 farooTest(); //Aufrufen um Faroo zu testen

	    // pdfBoxTest(); //Aufrufen um PDFBox zu Testen


	  
	  
	}

 
  private static void pdfBoxTest() {
    // TODO Auto-generated method stub
    /**
     * Demonstriert  den Zugriff auf die PDFBox. Das Programm wird gestarete (das dauert ca. 15 Sekunden) und kann dann vom Nutzer wie gewohnt genutzt werden. 
     * Nach dem schließen der PDF Box wird in der Konsole die Anzahl der eingelesenen PDFs angezeigt, sowie die Namen der PDFs und die Anzahl der gefundenen Keywords.
     * 
     * !Achtung durch das ausführen der PDFBox werden im kognitive_suche Ordner 2 neue Ordner (index und Database) mit verschiedenen Files angelegt. Um Probleme mit Git zu vermeiden, 
     * sollten diese Ordner vor dem nächsten commit wieder gelöscht werden!
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
     * Mit der Methode query wird die Suchanfrage eingeleitet. Dort Ã¼bergibt man das Suchwort. Dies ist die einfachste
     * Methode, des weiteren ist es mÃ¶glich in der Methode query weitere Parameter zu Ã¼bergeben. (Noch nicht implementiert)
     *
     * Mit der Methode api.getCompleteResults werden alle Ergebnisse die die Suchanfrage liefert zurÃ¼ckgegeben und
     * in eine ArrayList gespeichert. Ã¼ber result.get kann man dann auf die Einzelden Tags zugreifen und sich diese
     * Ausgeben lassen.
     *
     * Die Anweisungen mÃ¼ssen in einem try catch Block stehen da die Methode eine Exeption liefert, wenn es Probleme
     * gibt seitens des Verbindungsaufbaus oder Ã¤hnliches.
     *
     *
     * Falls die Methode "query" mit einer bestimmten kombination an Parametern nicht existiert, kann man sie leich implementieren.
     *
     */
    
    ConfigFileManagement config = new ConfigFileManagement();

		API api = new API(config.getKey());

		try {
			System.out.println("Suche..");
			api.query("Hallo Welt?", "de");
			//api.query("test", "de", true);
			//api.query("hallo");
			//api.query("foo war");
			//api.query("&&");
			//api.query("FoÃ¤Ã¶Ã¼");
			//api.query("mama");
			//api.query("Huibu");


			APIResults apiResults = api.getResult();
			ArrayList<Result> results = apiResults.getResultsList();

			for(int i = 0; i < results.size(); i++) {
				System.out.println(results.get(i).getAuthor());
				System.out.println(results.get(i).getDomain());
				System.out.println(results.get(i).getFirstIndexed());
				System.out.println(results.get(i).getFirstPublished());
				System.out.println(results.get(i).getImageUrl());
				System.out.println(results.get(i).getIsNews());
				System.out.println(results.get(i).getKwic());
				System.out.println(results.get(i).getTitle());
				System.out.println(results.get(i).getUrl());
				System.out.println(results.get(i).getVotes());
			}



			} catch (Exception e) {
			e.printStackTrace();
		}
	  System.out.println("Ende..");
  }

}


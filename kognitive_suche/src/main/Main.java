package main;

import java.util.ArrayList;
import java.util.HashMap;

import komplexeSuche.searchAlgorithm;
import komplexeSuche.suchobjekt;
import komplexeSuche.Keywords;
import Faroo.Result;
import controller.Controller;
import pdfBoxAcces.PDFBoxAccesControler;
import pdfBoxAcces.PDFDocument;

import simpleAlgorithm.ObBearbeitung;
import simpleAlgorithm.SimAlgTags;

import Faroo.API;
import Faroo.ConfigFileManagement;
import Faroo.APIResults;
import GUI.*;

public class Main {



  /*********************************************************************************************
   * *******************************************************************************************
   * 
   * Wichtig!!! Die Main Klasse Bevor das Projekt ausgefuehrt werden kann muss eine
   * config.properties file angelegt werden. Dies wird automatisch erzeugt! Falls dies der Fall ist
   * wird der Key in der Konsole abgefragt! Mit dem Inhalt:
   * 
   * 
   * key = 2CJIbhzsHU4nlSqBVZ2OP3fimb4_
   * 
   * Die Abfrage fuer den Key wird in der Console erledigt!
   * 
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


    /**
    farooTest(); // Aufrufen um Faroo zu testen

    pdfBoxTest(); //Aufrufen um PDFBox zu Testen

    // komplexer Suchalg. Test mit "Karsten Weicker"

    System.out.println("\n AUSGABE KOMPLEXE SUCHE//////////////////////////////////////////////");
    suchobjekt[] ergebnis = new suchobjekt[2];
    ergebnis[0] =
        new suchobjekt(
            "https://portal.imn.htwk-leipzig.de/fakultaet/weicker",
            "Karsten Weicker, Prof. Dr. rer. nat. — Fakultät Informatik",
            "Prof. Dr. rer. nat. Karsten Weicker Karsten Weicker, Prof. Dr. rer. nat. Leitungen und Ämter Studienfachberater (Informatik) Studienkommission Informatik (Vorsitzender) Studiendekan (Informatik) Fakultätsrat (Mitglied ) Aufgabenbereiche Lehrgebiet: Praktische Informatik Kontaktinformationen Sprechzeit: nach Vereinbarung Z410  Gustav-Freytag-Str. 42A 04277 Leipzig karsten.weicker [at] htwk-leipzig.de  +49 (0) 341 3076-6395 Lebenslauf1990-1997 Studium der Informatik mit Nebenfach Mathematik, Universität Stuttgart 1995-1997    Studium der Computer Science, University of Massachusetts in Amherst Gutachter für folgende Zeitschriften: IEEE Transactions on Evolutionary Computation, Evolutionary Computation Journal, ACM Computing Surveys, Information Processing Letters, Softcomputing Journal, Genetic Programming and Evolvable Machines");
    ergebnis[1] =
        new suchobjekt(
            "http://www.weicker.info/",
            "Informationen über die Weicker-Familie",
            "www.weicker.info Informationen über die Weicker-Familie   Karsten Weicker [Filme] Die vollständige Sammlung der Filme, die ich auf großer Leinwand gesehen habe - bald wieder online [Musik]  Eine grobe Sammlung der memorizable live acts [Evolutionäre Algorithmen]    Das Lehrbuch in der 2. Auflage");
    searchAlgorithm suche = new searchAlgorithm();

    suche.kognitivSuchen(ergebnis, "Karsten Weicker");

    simpleAlgorithmTest(); // Aufrufen, um den primitiven Algorithmus zu testen

  **/
  }


  private static void simpleAlgorithmTest() {

  /**

    System.out.println("\n AUSGABE SIMPLE ALGORITHM//////////////////////////////////////////////");
    
    ConfigFileManagement config = new ConfigFileManagement();

    API api = new API(config.getKey());
    try {
      System.out.println("Suche nach Hallo Welt");
      api.query("Hallo Welt?", "de");
      APIResults apiResults = api.getResult();
    ArrayList<Result> results = apiResults.getResultsList();
    
 // testuebergabe der Suchergebnisse von Faroo an den simplen Algorithmus

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

    }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Ende..");

    **/

  }



  private static void pdfBoxTest() {

    /**
     * Demonstriert den Zugriff auf die PDFBox. Das Programm wird gestartet (das dauert ca. 15
     * Sekunden) und kann dann vom Nutzer wie gewohnt genutzt werden. Nach dem schließen der PDF Box
     * wird in der Konsole die Anzahl der eingelesenen PDFs angezeigt, sowie die Namen der PDFs und
     * die Anzahl der gefundenen Keywords.
     * 
     * !Achtung durch das ausführen der PDFBox werden im kognitive_suche Ordner 2 neue Ordner (index
     * und Database) mit verschiedenen Files angelegt. Um Probleme mit Git zu vermeiden, sollten
     * diese Ordner vor dem nächsten commit wieder gelöscht werden!
     */

    /**
    System.out.println("\n AUSGABE pdfBox//////////////////////////////////////////////");
    
    ArrayList<PDFDocument> PDFDocs = new ArrayList<PDFDocument>();
    PDFDocs = Controller.queryPdfBox();

    System.out.println("Es wurden " + PDFDocs.size() + " PDFs eingelesen.");

    for (int i = 0; i < PDFDocs.size(); i++)
      System.out.println("In " + PDFDocs.get(i).getDocname() + " wurden "
          + PDFDocs.get(i).getKeywords().size() + " Keywords gefunden");


    for(int i = 0; i < PDFDocs.size(); i++)
    {
      System.out.println("In " + PDFDocs.get(i).getDocname() + " wurden " + PDFDocs.get(i).getKeywords().size() + " Keywords gefunden"); //Besipiel für abrufen des Dokumentennamen
      System.out.println("Das erste Keyword in dieser PDF heißt: " + PDFDocs.get(i).getKeywords().get(1).getTerm()); //Beispiel für abrufen der Keywords
    }
    **/

  }

  private static void farooTest() {
    /**
     * Demonstriert das Aufrufen der FAROO API und die Ausgabe auf der Konsole Der erste Parameter
     * ist der Faroo API key Mit der Methode query wird die Suchanfrage eingeleitet. Dort uebergibt
     * man das Suchwort. Dies ist die einfachste Methode, des weiteren ist es moeglich in der
     * Methode query weitere Parameter zu uebergeben. (Noch nicht implementiert)
     * 
     * Mit der Methode api.getCompleteResults werden alle Ergebnisse die die Suchanfrage liefert
     * zurueckgegeben und in eine ArrayList gespeichert. ueber result.get kann man dann auf die
     * Einzelden Tags zugreifen und sich diese Ausgeben lassen.
     * 
     * Die Anweisungen muessen in einem try catch Block stehen da die Methode eine Exeption liefert,
     * wenn es Probleme gibt seitens des Verbindungsaufbaus oder aenhnliches.
     * 
     * 
     * Falls die Methode "query" mit einer bestimmten kombination an Parametern nicht existiert,
     * kann man sie leich implementieren.
     * 
     */
/**
    System.out.println("\n AUSGABE FAROO//////////////////////////////////////////////");
    ConfigFileManagement config = new ConfigFileManagement();

    API api = new API(config.getKey());

    try {
      System.out.println("Suche..");
      api.query("Hallo Welt?", "de");
      // api.query("test", "de", true);
      // api.query("hallo");
      // api.query("foo war");
      // api.query("kkk");
      // api.query("k");
      // api.query("mama");
      // api.query("Huibu");


      APIResults apiResults = api.getResult();
      ArrayList<Result> results = apiResults.getResultsList();

      for (int i = 0; i < results.size(); i++) {
        System.out.println("Autor:          " + results.get(i).getAuthor());
        System.out.println("Domain:         " + results.get(i).getDomain());
        System.out.println("FirstIndexed:   " + results.get(i).getFirstIndexed());
        System.out.println("FirstPublished: " + results.get(i).getFirstPublished());
        System.out.println("ImageUrl:       " + results.get(i).getImageUrl());
        System.out.println("IsNews:         " + results.get(i).getIsNews());
        System.out.println("Kwic:           " + results.get(i).getKwic());
        System.out.println("Title:          " + results.get(i).getTitle());
        System.out.println("Url:            " + results.get(i).getUrl());
        System.out.println("Votes:          " + results.get(i).getVotes());
        System.out.println();

      }


    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Ende..");
 **/
  }

}

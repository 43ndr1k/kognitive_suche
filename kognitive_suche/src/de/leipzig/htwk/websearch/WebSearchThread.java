package de.leipzig.htwk.websearch;

import java.util.ArrayList;

import komplexe.suche.Statics;

public class WebSearchThread extends Thread{

	/** 
	 * Abgeleitet von Thread und ist somit einer der parallel auzuführenden Vorgänge. 
	 * Hier sollten die Funktion zum aufrufen der URL bishin zum herausfiltern der Tags aufgerufen werden! 
	 * 
	 * @author Franz Schwarzer
	 * @param url Url der Webseite
	 * @param urlNumber Um das wievielte Suchergebniss handelt es sich?
	 * @param keys Sollte irgendwann die Tags der URL wiederspiegeln
	 */

	  String url;
	  int urlNumber;
	  String[] keys;


	  WebSearchThread(String url, int urlNumber) {
	    this.url = url;
	    this.urlNumber = urlNumber;
	  }

	  @Override
	  public void run() {
	    System.out.println(this.url);
	    this.keys = setTags();
	  }



	  String[] setTags() {
		  
		  /**@return Rückgabe wird später durch das entsprechende Objekt ersetzt
		   *  Hier müssen ausserdem die Funktionen des CognitiveSearch mit eingebunden werden
		   * 
		   */
		  
		 HTMLTools html=new HTMLTools();
		 String sc= html.getHTMLSourceCode(url);
		 String[] mk=html.getMetaKeys(sc);
		 
	     return null; 

	  }
	
}

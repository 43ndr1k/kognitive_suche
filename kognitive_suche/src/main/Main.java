package main;
import java.util.ArrayList;
import java.util.HashMap;

import Faroo.API;
import Faroo.ConfigFileManagement;


public class Main {


	/*********************************************************************************************
	 * *******************************************************************************************
	 * Wichtig!!!
	 * Die Main Klasse
	 * Bevor das Projekt ausgeführt werden kann muss eine config.properties file angelegt werden.
	 * Mit dem Inhalt:
	 * key = Der Key
	 *
	 * *******************************************************************************************
	 * *******************************************************************************************
	 * @param args
	 * 
	 */
	

	public static void main(String[] args) {
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
		 */

		ConfigFileManagement config = new ConfigFileManagement();

		API api = new API(config.getKey());
		try {
			api.query("Foo");
			ArrayList<HashMap<String,String>> foo = api.getCompleteResults();
			for(HashMap<String,String> result: foo){
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


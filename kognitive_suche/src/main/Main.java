package main;
import java.util.ArrayList;
import java.util.HashMap;

import Faroo.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

		String key = leseConfig();
		API api = new API(key);
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


	/**
	 * Diese Methode list eine Config file aus und speichert den API key in einer Variable key.
	 *
	 * @return key
	 */
	private static  String leseConfig(){
		
		String key = null;
		Properties properties = new Properties();
		BufferedInputStream stream = null;
		try {

			stream = new BufferedInputStream(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		try {
			properties.load(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			key = properties.getProperty("key");
		
		
		return key;
	}
}


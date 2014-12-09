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


	/**
	 * @param args
	 * 
	 */
	

	public static void main(String[] args) {
		/**
		 * Demonstriert das Aufrufen der FAROO API und die Ausgabe auf der Konsole
		 * Der erste Parameter ist der Faroo API key
		 * Der zweite Parameter ist das Suchwort
		 * Man kann noch weitere Parameter Übergeben (Noch nicht implementiert)
		 */
		String key = leseConfig();
		API a = new API(key);
		a.query("Foo");
		ArrayList<HashMap<String,String>> foo = a.getCompleteResults();
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
		try {
			a.query("bar",-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("nghjgjg");	

			e.printStackTrace();
		}
		foo = a.getCompleteResults();
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
	}
	
	private static  String leseConfig(){
		
		String key = null;
		Properties properties = new Properties();
		BufferedInputStream stream = null;
		try {

			stream = new BufferedInputStream(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			properties.load(stream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			key = properties.getProperty("key");
		
		
		return key;
	}
}


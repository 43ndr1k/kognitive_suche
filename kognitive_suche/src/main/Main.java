package main;
import java.util.ArrayList;
import java.util.HashMap;

import Faroo.API;

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
		API a = new API("2CJIbhzsHU4nlSqBVZ2OP3fimb4_","foobar");
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
	}
}


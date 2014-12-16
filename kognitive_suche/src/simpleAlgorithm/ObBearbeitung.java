package simpleAlgorithm;
import java.util.ArrayList;

import komplexeSuche.tags;
import java.net.*;
import java.io.*;
// import Faroo.APIResults;
import Faroo.Result;

public class ObBearbeitung {
	
/*	

	public class URLReader {
	    public static void main(String[] args) throws Exception {

	        URL oracle = new URL("http://www.oracle.com/");
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(oracle.openStream()));

	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
	    }
	}
	
*/	
	
	

	public ArrayList<tags> annahme (ArrayList<Result> results){
		ArrayList<tags> rueckgabe = new ArrayList<tags>();
		
		String[] tags;
		String erstesWort;
		String Website;
		for(int i = 0; i< results.size(); i++){
			Website = results.get(i).getUrl();
			tags = results.get(i).getTitle().split(" ");
			erstesWort = tags[0];
			rueckgabe.add(i, new tags(erstesWort, Website));			
		}
		
		
		return rueckgabe;
	}
	
	
	

}

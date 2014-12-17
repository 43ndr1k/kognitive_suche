package simpleAlgorithm;
import java.util.ArrayList;

//import java.net.*;
//import java.io.*;
// import Faroo.APIResults;
import Faroo.Result;

public class ObBearbeitung {
		
	
	/*
	 * Die Methode nimmt Resultate von Faroo und erstes Wort von URL
	 */
	public ArrayList<Keywoords> annahme (ArrayList<Result> results){
		ArrayList<Keywoords> rueckgabe = new ArrayList<Keywoords>();
		
		String[] tags;
		String erstesWort;
		String Website;
		for(int i = 0; i< results.size(); i++){
			Website = results.get(i).getUrl();
			tags = results.get(i).getTitle().split(" ");
			erstesWort = tags[0];
			rueckgabe.add(i, new Keywoords(erstesWort, Website));			
		}
		
		
		return rueckgabe;
	}
	
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
	
	

	
	
	
	

}

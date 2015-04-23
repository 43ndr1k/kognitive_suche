package simpleAlgorithm;

import java.util.ArrayList;

// import java.net.*;
// import java.io.*;

import de.leipzig.htwk.faroo.api.Results;

public class ObBearbeitung {

  /**
   * @author Vadzim Naumchyk & Sadik Ulusan
   */
  /*
   * /* Die Methode nimmt Resultate von Faroo und erstes Wort von URL
   */
  public ArrayList<SimAlgTags> annahme(Results results) {
    ArrayList<SimAlgTags> rueckgabe = new ArrayList<SimAlgTags>();

    String[] tags;
    String erstesWort;
    String Website;
    for (int i = 0; i < results.getResults().size(); i++) {
      Website = results.getResults().get(i).getUrl();
      tags = results.getResults().get(i).getTitle().split(" ");
      erstesWort = tags[0];
      rueckgabe.add(i, new SimAlgTags(erstesWort, Website));
    }


    return rueckgabe;
  }

  /*
   * 
   * public class URLReader { public static void main(String[] args) throws Exception {
   * 
   * URL oracle = new URL("http://www.oracle.com/"); BufferedReader in = new BufferedReader( new
   * InputStreamReader(oracle.openStream()));
   * 
   * String inputLine; while ((inputLine = in.readLine()) != null) System.out.println(inputLine);
   * in.close(); } }
   */



}

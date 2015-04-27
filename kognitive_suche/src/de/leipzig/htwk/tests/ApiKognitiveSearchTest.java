package de.leipzig.htwk.tests;


import java.io.IOException;

import de.leipzig.htwk.websearch.HTMLTools;
import general.functions.TxtReader;
import kognitver.algorithmus.ApiCognitiveSearch;
import kognitver.algorithmus.ReturnTagList;

public class ApiKognitiveSearchTest {



  public static void main(String[] args) {

    ReturnTagList testlist = new ReturnTagList(null);
    long zstVorher;
    long zstNachher;


    ApiCognitiveSearch test = new ApiCognitiveSearch();
    String[] text = getTest();
    zstVorher = System.currentTimeMillis();
    test.ApiCognitiveSearch(text, "Sigma Sport");
    zstNachher = System.currentTimeMillis();
    System.out.println("Zeit benötigt: Kognitiver Algorithmus: "
        + ((zstNachher - zstVorher)) + " millisec");
    String s = "se  in.";

    // s = s.replaceAll("[.,!?]", " ");
    s = s.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");

    TxtReader tr = new TxtReader();

    try {
      s = tr.readFile("stoplist_de.txt");
    } catch (IOException e) {
      System.out.println("Das hat leider nicht geklappt");
    }

  }

  private static String[] getTest() {
    String[] url =
        {"http://www.sigma-foto.de/home.html", "http://www.sigmasport.de/de/startseite/?flash=1",
            "https://www.sigmaaldrich.com/", "http://de.wikipedia.org/wiki/Sigma",
            "http://www.sigmasport.com/de/produkte/pulscomputer/running_computer/rc_1411/?flash=1",
            "http://www.sigmaphoto.com/"

        };
    HTMLTools web = new HTMLTools();
    String tmp;
    for (int i = 0; i < url.length; i++) {
      long zstVorher;
      long zstNachher;

      zstVorher = System.currentTimeMillis();
      tmp = web.getHTMLSourceCode(url[i]);
      tmp = web.filterHTML(tmp);
      url[i] = tmp;
      zstNachher = System.currentTimeMillis();
      System.out.println("Zeit benötigt: Webseite "+i+": " + ((zstNachher - zstVorher) ) + " millisec");
    }
    return url;
  }

}

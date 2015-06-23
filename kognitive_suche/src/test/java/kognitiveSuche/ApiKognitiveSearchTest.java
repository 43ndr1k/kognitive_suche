package kognitiveSuche;


import de.leipzig.htwk.cognitive.search.ReturnTagList;
import de.leipzig.htwk.general.functions.TxtReader;
import de.leipzig.htwk.websearch.HTMLTools;

public class ApiKognitiveSearchTest {



  public static void main(String[] args) {

    ReturnTagList testlist = new ReturnTagList(null);
    long zstVorher;
    long zstNachher;



    // String[] text = getTest();
    zstVorher = System.currentTimeMillis();
    String[] texet = {"Test", "Test"};



    TxtReader tr = new TxtReader();


  }

  private static String[] getTest() {
    String[] url =
        {"http://www.sigma-foto.de/home.html", "http://www.sigmasport.de/de/startseite/?flash=1",
            "https://www.sigmaaldrich.com/", "http://de.wikipedia.org/wiki/Sigma",
            "http://www.sigmaphoto.com/"};
    HTMLTools web = new HTMLTools();
    String tmp;
    System.out.println(url.length);
    for (int i = 0; i < url.length; i++) {
      long zstVorher;
      long zstNachher;

      zstVorher = System.currentTimeMillis();
    
      zstNachher = System.currentTimeMillis();
      System.out.println("Zeit benötigt: Webseite " + i + ": " + ((zstNachher - zstVorher))
          + " millisec");
    }
    return url;
  }

}

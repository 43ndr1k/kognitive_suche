package komplexe.suche;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import de.leipzig.htwk.websearch.HTMLTools;


public class Tag_Meta extends Thread {

  // @author Franz Schwarzer

  String url;
  int urlNumber;
  String[] keys;


  Tag_Meta(String url, int urlNumber) {
    this.url = url;
    this.urlNumber = urlNumber;
  }

  @Override
  public void run() {
    System.out.println(this.url);
    this.keys = getMetaKeys();
    Statics.keys = this.keys;
    Statics.urlkeys[urlNumber] = keys;
    Statics.url[urlNumber] = url;
    System.out.println(Statics.urlkeys[urlNumber][0]);
  }



  String[] getMetaKeys() {
	 HTMLTools html=new HTMLTools();
	 String sc= html.getHTMLSourceCode(url);

    // Suche nach Meta Key
    Statics.pageText[urlNumber] = sc;
    return html.getMetaKeys(sc);

  }

  ArrayList<String[]> UrlKeys(String[] urls) {


    ArrayList<String[]> rueckgabe = new ArrayList<String[]>();


    for (int i = 0; i < url.length(); i++) {


    }

    return rueckgabe;
  }

}

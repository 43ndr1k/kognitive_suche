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
    int index = sc.indexOf("<meta name=\"keywords\" content=");
    String sub = sc.substring(index);
    index = sub.indexOf("/>");
    sub = sub.substring(0, index);

    for (int i = 0; i < 2; i++) {
      index = sub.indexOf("\"", sub.indexOf("\"") + 1);
      sub = sub.substring(index);
    }

    sub = sub.substring(1, sub.length() - 2);
    String[] keys = sub.split(", ");

    return keys;

  }

  ArrayList<String[]> UrlKeys(String[] urls) {


    ArrayList<String[]> rueckgabe = new ArrayList<String[]>();


    for (int i = 0; i < url.length(); i++) {


    }

    return rueckgabe;
  }

}

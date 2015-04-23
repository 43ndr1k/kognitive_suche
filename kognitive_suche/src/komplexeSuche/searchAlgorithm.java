package komplexeSuche;


import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class searchAlgorithm {



  /**
   * Algorithmus zur Erkennung von Schl�sselbegriffen Komplexit�t n�
   * 
   * @author Tobias Lenz
   */

  private String text;



  public ArrayList<tags> kognitivSuchen(suchobjekt[] ergebnis, String searchword) {

    boolean flag = true;
    int anzTags = 0;
    int numbcontsearchword = 0; // Anzahl der im Text entahltenen Suchw�rter
    int range = 5;

    ArrayList<tags> tagfrequency = new ArrayList<tags>(); // Datentyp f�r h�ufigste Suchw�rter
    ArrayList<taglist> tagnearby = new ArrayList<taglist>(); // Datentyp f�r Umgebungssuchw�rter
    ArrayList<tags> retlist = new ArrayList<tags>(); // Datentyp f�r die R�ckgabe
    for (int i = 0; i < ergebnis.length; i++) {

      text = ergebnis[i].getsearchtext();
      text = text.replaceAll("[^a-zA-Z0-9 .�������@]", ""); // hier werden alle
                                                                          // Zeichen aus dem Text
                                                                          // gel�scht, welche
                                                                          // weder Zahlen,
                                                                          // Buchstaben, . oder
                                                                          // Leerzeichen sind
      // Hinweis: "Regul�re Ausdr�cke"
      String[] parts = text.split(" ");

      for (int j = 0; j < parts.length; j++) {
        if (doescontain(searchword, parts[j])) {
          tagnearby.add(numbcontsearchword, new taglist(ergebnis[i].getlink(), parts[j]));
          int num = 0;
          for (int l = 1; l < range; l++) {
            if (j - l >= 0) {
              tagnearby.get(numbcontsearchword).addtag(num, parts[j - l]);
              num++;
            }

            if (j + l < parts.length) {
              tagnearby.get(numbcontsearchword).addtag(num, parts[j + l]);
              num++;
            }
          }
          numbcontsearchword++;

        }
        flag = true;
        for (int k = 0; k < tagfrequency.size(); k++) {



          if (parts[j].equals(tagfrequency.get(k).gettag()) && !parts[j].equals("")
              && !badWord(parts[j]) && !doescontain(searchword, parts[j])) {
            flag = false;
            tagfrequency.get(k).addaddress(ergebnis[i].getlink());
            tagfrequency.get(k).setcount();
            break;
          }


        }
        if (flag) {
          tagfrequency.add(anzTags, new tags(parts[j], ergebnis[i].getlink()));
          anzTags++;
        }
      }

    }
    Collections.sort(tagfrequency, new SortTags());
    retlist = merge(tagfrequency, tagnearby, ergebnis, searchword);
    for (int i = 0; i < 5; i++) {
      int n = tagfrequency.size() - 1 - i;
      // System.out.println(tagfrequency.get(n).gettag());
      // System.out.println(tagnearby.get(i).gettag(i));
    }

    return retlist;


  }



  private boolean badWord(String string) {
    /**
     * @author Steffen Schreiber
     */
    String wordList[] =
        {"der", "die", "das", "dem", "den", "ein", "eines", "eine", "einem", "einen", "des", "da",
            "wo", "von", "den", "und", "dieser", "jener", "kein", "deren", "dessen", "oder",
            "auch", "wir", "ihr", "er", "sie", "Sie", "es", "euch", "keiner", "jeder", "uns",
            "ihnen", "Ihnen", "denen", "dabei", "während", "für", "von", "mit", "nicht", "ist",
            "dass", "wie", "wer", "wann", "warum", "weshalb", "im", "auf", "durch", "unter",
            "sehr", "selbst", "schon", "hier", "bis", "habe", "ihre", "seine", "seiner", "muss",
            "alle", "wieder", "meine", "Zeit", "gegen", "vom", "ganz", "einzelnen", "einzeln",
            "ohne", "können", "sei", "zur", "hatte", "man", "aber", "zum", "soll", "worden",
            "Jahr", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun",
            "null", "zwischen", "immer", "Jahren", "sagte", "sagt", "wurde", "so", "solange",
            "vor", "über", "In", "in", "hat", "am", "sich", "als", "werden", "wollen", "müssen",
            "würden", "neu", "rund", "groß", "klein", "alt", "jung", "möglich", "deutlich",
            "weit", "viel", "fest", "weich", "flüssig", "gut", "lang", "knapp", "künftig",
            "schwer", "genau", "sicher", "ihrer", "ihrem", "mich", "mir", "ihm", "unser", "ich",
            "du", "häufig", "wenig", "wenige", "einzelner", "lassen", "gehen", "laufen", "rennen",
            "fliegen", "heißen", "warm", "kalt", "wärmer", "kälter", "heiß", "gilt", "gelten",
            "stehen", "schwimmen",};

    /**
     * @author Tobias Lenz
     */

    for (int i = 0; i < wordList.length; i++) {
      if (string.equals(wordList[i])) {
        return true;
      }
    }

    return false;
  }



  /**
   * Zusammenf�hrung der Tagsuche und der Metakeys
   * 
   * @author Steffen Schreiber
   */


  private ArrayList<tags> merge(ArrayList<tags> tagfrequency, ArrayList<taglist> tagnearby,
      suchobjekt[] ergebnis, String searchword) {

    ArrayList<String> returnlist = new ArrayList<String>();
    ArrayList<String> haeufigeTags = new ArrayList<String>();
    ArrayList<String> naheTags = new ArrayList<String>();
    ArrayList<String> UrlKeys = new ArrayList<String>();

    /**
     * Vorf�hrung mit festgelegten Metakeys
     * 
     */
    UrlKeys.add("URL");
    UrlKeys.add("Karsten");
    UrlKeys.add("Sammlung");
    UrlKeys.add("Weicker");
    UrlKeys.add("Dr.");
    UrlKeys.add("�mter");

    for (int i = 0; i < 15; i++) {
      haeufigeTags.add(tagfrequency.get(i).gettag());
    }

    for (int i = 0; i < 6; i++) {
      naheTags.add(tagnearby.get(i).gettag(i));
    }

    for (int x = 0; x < haeufigeTags.size(); x++) {
      for (int y = 0; y < UrlKeys.size(); y++) {
        if (haeufigeTags.get(x).equalsIgnoreCase(UrlKeys.get(y))) {
          returnlist.add(haeufigeTags.get(x));
          // Set<String> set = new LinkedHashSet<String>(returnlist);
          // returnlist = new ArrayList<String>(set);
          // System.out.println("Die gleichen Tags sind: " + returnlist);
        }
      }
    }

    for (int x = 0; x < naheTags.size(); x++) {
      for (int y = 0; y < UrlKeys.size(); y++) {
        if (naheTags.get(x).equalsIgnoreCase(UrlKeys.get(y))) {
          returnlist.add(naheTags.get(x));
        }
      }
    }

    for (int x = 0; x < haeufigeTags.size(); x++) {
      for (int y = 0; y < naheTags.size(); y++) {
        if (haeufigeTags.get(x).equalsIgnoreCase(naheTags.get(y))) {
          returnlist.add(haeufigeTags.get(x));
        }
      }
    }
    Set<String> set = new LinkedHashSet<String>(returnlist);
    returnlist = new ArrayList<String>(set);

    int n = 6;

    while (returnlist.size() < 6) {
      returnlist.add(haeufigeTags.get(n));
      Set<String> set2 = new LinkedHashSet<String>(returnlist);
      returnlist = new ArrayList<String>(set2);
      n++;
    }

    System.out.println(returnlist);

    return null;
  }



  /**
   * @author Tobias Lenz
   */

  private boolean doescontain(String searchword, String string) {
    String[] parts = searchword.split(" ");
    for (int i = 0; i < parts.length; i++) {
      if (parts[i].equals(string)) {
        return true;
      }
    }
    return false;
  }

}

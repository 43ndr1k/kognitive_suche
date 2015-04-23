package kognitver.algorithmus;

import java.util.ArrayList;
import java.util.Collections;

public class WordCount {

  private String text;
  private ArrayList<Keywords> tagFrequency = new ArrayList<Keywords>(); // Datentyp für häufigste
                                                                        // Suchwörter
  private ArrayList<Tag> tagNearby = new ArrayList<Tag>(); // Datentyp für Umgebungssuchwörter

  public void addText(String[] searchText, String searchWord) {


    boolean flag = true;
    int numTags = 0;
    int numbContSearchWord = 0; // Anzahl der im Text enthaltenen Suchwörter
    int numbOfWords = 0;
    int RANGE = 5;



    for (int i = 0; i < searchText.length; i++) {

      text = searchText[i];
      text = text.replaceAll("[^a-zA-Z0-9 .äöüÄÖÜß?!@]", ""); // hier werden alle Zeichen aus
                                                                     // dem
      // Text gelöscht, welche weder Zahlen,
      // Buchstaben, . oder Leerzeichen sind
      // Hinweis: "Reguläre Ausdrücke"
      String[] parts = text.split(" ");

      for (int j = 0; j < parts.length; j++) {
        if (doescontain(searchWord, parts[j])) {
          tagNearby.add(numbContSearchWord, new Tag(i, parts[j]));
          int num = 0;
          for (int l = 1; l < RANGE; l++) {
            if (j - l >= 0 && !badWord(parts[j - l])) {
              tagNearby.get(numbContSearchWord).addtag(num, parts[j - l]);
              num++;
            }

            if (j + l < parts.length && !badWord(parts[j + l])/* && parts[j+l] != "[^.!?]" */) {
              tagNearby.get(numbContSearchWord).addtag(num, parts[j + l]);
              num++;
            }
          }
          numbContSearchWord++;

        }
        flag = true;

        for (int k = 0; k < tagFrequency.size(); k++) {

          if (parts[j].equals(tagFrequency.get(k).gettag()) && !parts[j].equals("")
              && !badWord(parts[j]) && !doescontain(searchWord, parts[j])) {
            flag = false;
            tagFrequency.get(k).addaddress(i);
            tagFrequency.get(k).setcount();
            break;
          }

        }
        if (flag) {
          tagFrequency.add(numTags, new Keywords(parts[j], i));
          numTags++;
        }
      }
    }
    Collections.sort(tagFrequency, new SortKeywords()); // Die Suchbegriffe werden nach Häufigkeit
                                                        // sortiert
    for (int i = 0; i < 5; i++) { // Testausgabe
      int n = tagFrequency.size() - 1 - i;
      System.out.println(tagFrequency.get(n).gettag());
      // System.out.println(tagNearby.get(i).gettag(i));
    }

  }


  private boolean doescontain(String searchword, String string) {
    String[] parts = searchword.split(" ");
    for (int i = 0; i < parts.length; i++) {
      if (parts[i].equalsIgnoreCase(string)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 
   * @param word - Wort, welches in der Wortliste überprüft werden soll
   * @return true - wenn das Wort in der Wortliste enthalten ist false - falls nicht
   */
  private boolean badWord(String word) {
    /**
     * @author Steffen Schreiber
     */
    String wordList[] =
        {"der", "die", "das", "dem", "den", "ein", "eines", "eine", "einem", "einen", "einer",
            "des", "da", "wo", "von", "den", "und", "dieser", "jener", "kein", "deren", "dessen",
            "oder", "auch", "wir", "ihr", "er", "sie", "Sie", "es", "euch", "keiner", "jeder",
            "uns", "ihnen", "Ihnen", "denen", "dabei", "während", "für", "von", "mit", "nicht",
            "ist", "dass", "wie", "wer", "wann", "warum", "weshalb", "im", "auf", "durch", "unter",
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
     *
     */

    for (int i = 0; i < wordList.length; i++) {
      if (word.equalsIgnoreCase(wordList[i])) {
        return true;
      }
    }

    return false;
  }

  public ArrayList<Keywords> gettagFrequency() {
    return tagFrequency;
  }

  public ArrayList<Tag> gettagNearby() {
    return tagNearby;
  }
}

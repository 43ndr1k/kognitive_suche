package kognitver.algorithmus;

import general.functions.TxtReader;

import java.io.IOException;
import java.util.ArrayList;

public class WordCount {

  private String text;
  private ReturnTagList tagFrequency = new ReturnTagList();// Datentyp für häufigste
  // Suchwörter
  private ArrayList<Tag> tagNearby = new ArrayList<Tag>(); // Datentyp für Umgebungssuchwörter

  public void addText(String[] searchText, String searchWord) {


    int numTags = 0;
    int numbContSearchWord = 0; // Anzahl der im Text enthaltenen Suchwörter
    int numbOfWords = 0;
    final int RANGE = 5;

    for (int i = 0; i < searchText.length; i++) { // Alle Textblöcke werden nacheinander durchsucht

      text = searchText[i];
      text = text.replaceAll(" +", " "); // mehrere Leerzeichen werden durch ein einzelnes ersetzt
      text = text.replaceAll("[^\\w .äöüÄÖÜß?!@]", ""); // hier werden alle Zeichen aus
                                                        // dem Text gelöscht, welche weder
                                                        // Zahlen, Buchstaben, . oder
                                                        // Leerzeichen sind Hinweis:
                                                        // "Reguläre Ausdrücke"
      String[] parts = text.split(" ");

      for (int j = 0; j < parts.length; j++) { // Der Textblock wird durchsucht
        if (doescontain(searchWord, parts[j])) { // Falls Das Suchwort vorkommt, wird findTagNearby
                                                 // aufgerufen
          findTagNearby(parts, j, i, searchWord, RANGE, numbContSearchWord);
          numbContSearchWord++;
        } else {
          if (!parts[j].equals(" ") && !badWord(parts[j])) { // Wörter
                                                             // werden
                                                             // gezählt
                                                             // und
                                                             // als
                                                             // Tag
                                                             // hinzugefügt
            tagFrequency.addTagObject(parts[j], i);
            tagFrequency.getTagByTagName(parts[j]).addPriority(1);
            numTags++;
          }
        }
      }
    }

  }

  private void findTagNearby(String[] parts, int j, int i, String searchWord, int RANGE,
      int numbContSearchWord) {
    {
      tagNearby.add(numbContSearchWord, new Tag(i, parts[j]));
      for (int l = 1; l < RANGE; l++) {
        if (j - l >= 0 && !badWord(parts[j - l])) {
          tagNearby.get(numbContSearchWord).addtag(parts[j - l]);
        }

        if (j + l < parts.length && !badWord(parts[j + l])/* && parts[j+l] != "[^.!?]" */) {
          tagNearby.get(numbContSearchWord).addtag(parts[j + l]);
        }
      }
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
            "würden", "neu", "rund", "groß", "klein", "alt", "jung", "möglich", "deutlich", "weit",
            "viel", "fest", "weich", "flüssig", "gut", "lang", "knapp", "künftig", "schwer",
            "genau", "sicher", "ihrer", "ihrem", "mich", "mir", "ihm", "unser", "ich", "du",
            "häufig", "wenig", "wenige", "einzelner", "lassen", "gehen", "laufen", "rennen",
            "fliegen", "heißen", "warm", "kalt", "wärmer", "kälter", "heiß", "gilt", "gelten",
            "stehen", "schwimmen",};

    /**
     * @author Tobias Lenz
     *
     */
    TxtReader tr = new TxtReader();
    String tmp = "";;
    try {
      tmp = tr.readFile("stoplist_de.txt");
    } catch (IOException e) {
      System.out.println("Stoplist datei nicht gefunden");
    }
    wordList = tmp.split(" ");
    for (int i = 0; i < wordList.length; i++) {
      word.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
      if (word.equalsIgnoreCase(wordList[i])) {
        return true;
      }
    }

    return false;
  }

  public ArrayList<Tag> gettagNearby() {
    return tagNearby;
  }

  public ReturnTagList getTagFrequency() {
    return tagFrequency;
  }
}

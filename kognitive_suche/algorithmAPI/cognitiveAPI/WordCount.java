package cognitiveAPI;


import java.util.ArrayList;

public class WordCount {

  final int range = 3;
  private String text;
  private ReturnTagList tagFrequency = new ReturnTagList();// Datentyp für häufigste Suchwörter
  private ArrayList<Tag> tagNearby = new ArrayList<Tag>(); // Datentyp für Umgebungssuchwörter

  /**
   * 
   * @param searchText
   * @param searchWord
   */
  public void analyseText(String[] searchText, String searchWord) {

    int numbContSearchWord = 0; // Anzahl der im Text enthaltenen Suchwörter

    for (int i = 0; i < searchText.length; i++) { // Alle Textblöcke werden nacheinander durchsucht

      text = searchText[i];
      text = text.replaceAll(" +", " "); // mehrere Leerzeichen werden durch ein einzelnes ersetzt
      text = text.replaceAll("[^\\w. äöüÄÖÜß?!@]", ""); // hier werden alle Zeichen aus
                                                        // dem Text gelöscht, welche weder
                                                        // Zahlen, Buchstaben, . oder
                                                        // Leerzeichen sind Hinweis:
                                                        // "Reguläre Ausdrücke"
      String[] parts = text.split(" ");

      for (int j = 0; j < parts.length; j++) { // Der Textblock wird durchsucht
        if (doesContain(searchWord, parts[j])) { // Falls Das Suchwort vorkommt, wird findTagNearby
                                                 // aufgerufen
          findTagNearby(parts, j, i, searchWord, numbContSearchWord);
          numbContSearchWord++;
        } else {
          if (!parts[j].equals(" +")) { // Wörter
                                        // werden
                                        // gezählt
                                        // und
                                        // als
                                        // Tag
                                        // hinzugefügt
            tagFrequency.addTagObject(parts[j], i, 1);
          }
        }
      }
    }

  }



  private void findTagNearby(String[] parts, int j, int i, String searchWord, int numbContSearchWord) {
    {
      tagNearby.add(numbContSearchWord, new Tag(i, parts[j]));
      for (int l = 1; l < range; l++) {
        if (j - l >= 0) {
          tagNearby.get(numbContSearchWord).addtag(parts[j - l]);
        }

        if (j + l < parts.length) {
          tagNearby.get(numbContSearchWord).addtag(parts[j + l]);
        }
      }
    }

  }

  /**
   * 
   * @param searchword
   * @param word - Word, welches auf Vorkommen im Suchstring geprüft werden soll
   * @return true - falls Word enthalten false - sonst
   */
  //private
  public boolean doesContain(String searchword, String word) {
    String[] parts = searchword.split(" ");
    for (int i = 0; i < parts.length; i++) {	
       text = parts[i];
       text = text.replaceAll("[?.!/^#:;]", "");
       parts[i] = text;
    }
    for (int i = 0; i < parts.length; i++) {
      if (parts[i].equalsIgnoreCase(word)) {
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

package de.leipzig.htwk.cognitive.search;

import java.util.ArrayList;

public class Tag {

  /**
   * @author Tobias Lenz
   */

  private ArrayList<String> tags = new ArrayList<String>();
  private int textBlocNumber;
  private String searchWord;

  Tag(int textBlocNumber, String searchword) {
    this.textBlocNumber = textBlocNumber;
    this.searchWord = searchword;
  }

  public int getTextBlocNumber() {
    return textBlocNumber;
  }

  public String getsearchword() {
    return searchWord;
  }

  public void addtag(String tag) {
    tags.add(tag);
  }

  public String getTag(int number) {
    return tags.get(number);
  }

  public int getSize() {
    return tags.size();
  }


  /**
   * prüfen, ob ein Buchstabe in der Liste der Gruppen des Kölner Phonetik Algorithmus enthalten
   * ist.
   * 
   * @author Steffen Schreiber
   */

  static boolean contains1(char c, char[] array) {
    for (char x : array) {
      if (x == c) {
        return true;
      }
    }
    return false;
  }
}

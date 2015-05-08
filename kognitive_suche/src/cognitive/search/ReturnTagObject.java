package cognitive.search;


import java.util.ArrayList;

public class ReturnTagObject {

  /**
   * @author Tobias Lenz Objekt zur Weitergabe von Tags mit den dazugehörigen URLS
   * 
   * @param tagName - Name des Tags
   * @param priority - Wertigkeit des Tags
   * @param bloc_number - Liste von Textblöcken, in denen der Tag vorhanden ist.
   */

  private ArrayList<Integer> blocNumbers = new ArrayList<Integer>();
  private String tag;
  private double priority = 0;

  public ReturnTagObject(String tag) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    this.tag = tag;
  }

  public String getTag() {
    return tag;
  }

  public void addBlocNumber(int blocNumber) {
    if (checkBlocNumber(blocNumber)) {
      return;
    }
    blocNumbers.add(blocNumber);
  }

  /**
   * Funktion zur Überprüfung, ob der Tag in einer Textblocknummer vorhanden ist.
   * 
   * @param number - Textblocknummer, welche geprüft werden soll.
   * @return true - wenn Tag in der textblocknummer enthalten ist false - wenn Tag nicht in der
   *         Textblocknummer enthalten ist.
   */
  public Boolean checkBlocNumber(int number) {
    for (int i = 0; i < blocNumbers.size(); i++) {
      if (blocNumbers.get(i) == number) {
        return true;
      }
    }
    return false;
  }

  public int getNumOfBlocs() {
    return blocNumbers.size();
  }

  public ArrayList<Integer> getBlocNumbers() {
    return blocNumbers;
  }

  public void addPriority(double priority) {
    this.priority += priority;
  }

  public Double getPriority() {
    return priority;

  }
}

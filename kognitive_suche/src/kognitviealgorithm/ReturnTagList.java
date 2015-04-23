package kognitviealgorithm;


import java.util.ArrayList;

public class ReturnTagList {
  /**
   * @author Tobias Lenz
   * 
   *         Objekt zur Übergabe von Taginformatinen enthält eine ArrayList von TagObjects Bei der
   *         Initialisierung soll das entsprechende Suchwort übergeben werden
   * @param searchWord - Suchwort
   * @param tabObjects - Lister der Tags mit Wertigkeit und "Vorkommnissliste"
   */
  private static String searchword;

  private ArrayList<ReturnTagObject> tagObjects = new ArrayList<ReturnTagObject>();

  public ReturnTagList(String searchword) {
    this.searchword = searchword;
  }

  String getsearchword() {
    return searchword;
  }
/**
 * 
 * @param keyword
 * @param textBlocNumber
 */
  public void addTagObject(String keyword, int textBlocNumber) {
    if (getTagByTagName(keyword) == null) {
      tagObjects.add(new ReturnTagObject(keyword));
    }
    getTagByTagName(keyword).addBlocNumber(textBlocNumber);
  }
/**
 * 
 * @param keyword
 * @param textBlocNumber
 * @param priority
 */
  public void addTagObject(String keyword, int textBlocNumber, double priority) {
    if (getTagByTagName(keyword) == null) {
      tagObjects.add(new ReturnTagObject(keyword));
    }
    getTagByTagName(keyword).addBlocNumber(textBlocNumber);
    getTagByTagName(keyword).addPriority(priority);
  }
/**
 * 
 * @param keyword
 * @param textBlocNumber
 * @param priority
 */
  public void addTagObject(String keyword, ArrayList<Integer> textBlocNumber, double priority) {
    if (getTagByTagName(keyword) == null) {
      tagObjects.add(new ReturnTagObject(keyword));
    }
    for (int i = 0; i < textBlocNumber.size(); i++) {
      getTagByTagName(keyword).addBlocNumber(textBlocNumber.get(i));
    }
    getTagByTagName(keyword).addPriority(priority);
  }

  public ReturnTagObject getTagObject(int num) {
    return tagObjects.get(num);
  }

  public int getsize() {
    return tagObjects.size();
  }
/**
 * 
 * @param name
 * @return
 */
  public ReturnTagObject getTagByTagName(String name) {
    for (int i = 0; i < tagObjects.size(); i++) {
      if (name.equals(tagObjects.get(i).gettag())) {
        return tagObjects.get(i);
      }
    }
    return null;
  }

}

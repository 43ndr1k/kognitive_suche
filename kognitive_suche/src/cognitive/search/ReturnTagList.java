package cognitive.search;


import java.util.ArrayList;
import java.util.Collections;


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
    ReturnTagList.searchword = searchword;
  }

  public ReturnTagList() {}

  public static String getSearchword() {
    return searchword;
  }

  public ArrayList<ReturnTagObject> getTagObjects() {
    return tagObjects;
  }

  public void setTagObjects(ArrayList<ReturnTagObject> tagObjects) {
    this.tagObjects = tagObjects;
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

  public int getSize() {
    return tagObjects.size();
  }

  /**
   * 
   * @param tag
   * @return
   */
  public ReturnTagObject getTagByTagName(String tag) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    for (int i = 0; i < tagObjects.size(); i++) {
      if (tag.equalsIgnoreCase(tagObjects.get(i).getTag())) {
        return tagObjects.get(i);
      }
    }
    return null;
  }

  public void renameTag(String oldTag, String newTag) {
    ReturnTagObject tmp = getTagByTagName(oldTag);
    if (tmp != null) {
      addTagObject(newTag, tmp.getBlocNumbers(), tmp.getPriority());
      deleteTag(oldTag);
    }

  }

  void deleteTag(String tag) {
    tagObjects.remove(getTagByTagName(tag));
  }

  public ArrayList<ReturnTagObject> getTags() {
    sortTagsByPriority();
    return tagObjects;
  }
/**
 * hier werden die Tags nach Priorität geordnet
 */
  public void sortTagsByPriority() {
    Collections.sort(tagObjects, new ReturnTagListSort());
  }
  public void testOutput(int num){
    if(num > tagObjects.size()) num = tagObjects.size();
    for(int i = 0; i < num; i++){
      System.out.println(tagObjects.get(i).getTag()+" Priority: "+Math.round(tagObjects.get(i).getPriority()));
    }
  }

}

package cognitiveAPI;


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

  private ArrayList<ReturnTagObject> tags = new ArrayList<ReturnTagObject>();

  public ReturnTagList(String searchword) {
    ReturnTagList.searchword = searchword;
  }

  public ReturnTagList() {}

  /**
   * 
   * @param tag
   * @param textBlocNumber
   */
  public void addTagObject(String tag, int textBlocNumber) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    if (getTagByTagName(tag) == null) {
      tags.add(getInsertPosition(tag), new ReturnTagObject(tag));
    }
    getTagByTagName(tag).addBlocNumber(textBlocNumber);
  }

  /**
   * 
   * @param tag
   * @param textBlocNumber
   * @param priority
   */
  public void addTagObject(String tag, int textBlocNumber, double priority) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    if (getTagByTagName(tag) == null) {
      tags.add(getInsertPosition(tag), new ReturnTagObject(tag));
    }
    getTagByTagName(tag).addBlocNumber(textBlocNumber);
    getTagByTagName(tag).addPriority(priority);
  }
  //private
  public int getInsertPosition(String tag) {
    int mitte = 0;
    int links = 0;
    int rechts = tags.size() - 1;
    int tmp;

    while (links <= rechts) {
      mitte = links + ((rechts - links) / 2);
      tmp = tag.compareToIgnoreCase(tags.get(mitte).getTag());
      if (tmp < 0) { // Tag befindet sich in der linken Hälfte
        rechts = mitte - 1;
      } else if (tmp > 0) { // Tag befindet sich in der rechten Hälfte
        links = mitte + 1;
      }
    }

    if (rechts > links)
      return rechts;
    else
      return links;

  }

  /**
   * 
   * @param tag
   * @param textBlocNumber
   * @param priority
   */
  public void addTagObject(String tag, ArrayList<Integer> textBlocNumber, double priority) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    if (getTagByTagName(tag) == null) {
      tags.add(getInsertPosition(tag), new ReturnTagObject(tag));
    }
    for (int i = 0; i < textBlocNumber.size(); i++) {
      getTagByTagName(tag).addBlocNumber(textBlocNumber.get(i));
    }
    getTagByTagName(tag).addPriority(priority);
  }

  public ReturnTagObject getTagObject(int num) {
    return tags.get(num);
  }

  public int getSize() {
    return tags.size();
  }

  /**
   * 
   * @param tag
   * @return
   */
  public ReturnTagObject getTagByTagName(String tag) {
    tag = tag.replaceAll("[^a-zA-Z0-9 äöüÄÖÜß]", " ");
    int mitte;
    int links = 0;
    int rechts = tags.size() - 1;
    int tmp;

    while (links <= rechts) {
      mitte = links + ((rechts - links) / 2);
      tmp = tag.compareToIgnoreCase(tags.get(mitte).getTag());
      if (tmp == 0) {
        return tags.get(mitte);
      } else if (tmp < 0) { // Tag befindet sich in der linken Hälfte
        rechts = mitte - 1;
      } else if (tmp > 0) { // Tag befindet sich in der rechten Hälfte
        links = mitte + 1;
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

  public void deleteTag(String tag) {
    tags.remove(getTagByTagName(tag));
  }
  
  public void deleteTag(int num){
    tags.remove(num);
  }

  public ArrayList<ReturnTagObject> getTags() {
    return tags;
  }

  /**
   * hier werden die Tags nach Priorität geordnet
   */
  public void sortTagsByPriority() {
    Collections.sort(tags, new ReturnTagListSort());
  }

  public void testOutput(int num) {
    if (num > tags.size()) {
      num = tags.size();
    }
    for (int i = 0; i < num; i++) {
      System.out.println(tags.get(i).getTag() + " Priority: "
          + Math.round(tags.get(i).getPriority()));
    }
  }

  public static String getSearchword() {
    return searchword;
  }

  public ArrayList<ReturnTagObject> getTagObjects() {
    return tags;
  }

  public void setTagObjects(ArrayList<ReturnTagObject> tagObjects) {
    this.tags = tagObjects;
  }
}

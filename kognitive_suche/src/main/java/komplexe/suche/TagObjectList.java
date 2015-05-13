package komplexe.suche;


import java.util.ArrayList;

public class TagObjectList {
  /**
   * @author Tobias Lenz
   * 
   * 
   *         Objekt zur Uebergabe von Taginformatinen enth�lt eine ArrayList von TagObjects Bei der
   *         Initialisierung soll das entsprechende Suchwort uebergeben werden
   * 
   */
  private static String searchword;
  private ArrayList<TagObject> tagObjects = new ArrayList<TagObject>();

  public TagObjectList(String searchword) {
    this.searchword = searchword;
  }

  public void addTagObject(TagObject tag, int num) {
    tagObjects.add(num, tag);
  }

  String getsearchword() {
    return searchword;
  }

  public TagObject getTagObject(int num) {
    return tagObjects.get(num);
  }

  public int getsize() {
    return tagObjects.size();
  }

  public TagObject getTagByTagName(String name) {
    for (int i = 0; i < tagObjects.size(); i++) {
      if (name.equals(tagObjects.get(i).gettag())) {
        return tagObjects.get(i);
      }
    }
    return null;
  }

}

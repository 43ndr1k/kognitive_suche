package kognitver.algorithmus;

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

  public String gettag(int number) {
    return tags.get(number);
  }

  public int getsize() {
    return tags.size();
  }

}

package komplexeSuche;

import java.util.ArrayList;

public class taglist {

  /**
   * @author Tobias Lenz
   */

  private ArrayList<String> tags = new ArrayList<String>();
  private String address;
  private String searchword;

  taglist(String address, String searchword) {
    this.address = address;
    this.searchword = searchword;
  }

  public String getaddress() {
    return address;
  }

  public String getsearchword() {
    return searchword;
  }

  public void addtag(int number, String tag) {
    tags.add(number, tag);
  }

  public String gettag(int number) {
    return tags.get(number);
  }

}

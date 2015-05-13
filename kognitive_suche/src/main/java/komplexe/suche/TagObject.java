package komplexe.suche;


import java.util.ArrayList;

public class TagObject {

  /**
   * @author Tobias Lenz Objekt zur Weitergabe von Tags mit den dazugeh�rigen URLS
   */

  private ArrayList<String> url = new ArrayList<String>();
  private String tag;

  public TagObject(String tag) {
    this.tag = tag;
  }

  public String gettag() {
    return tag;
  }

  public void addurl(int number, String link) {
    url.add(number, link);
  }

  public String geturl(int number) {
    return url.get(number);
  }

  public int getNumOfURLs() {
    return url.size();
  }

}

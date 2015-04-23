package komplexeSuche;

import java.util.ArrayList;

// @author Franz Schwarzer

public class UrlObjekt {

  private ArrayList<String> tags = new ArrayList<String>();
  private String url;

  public UrlObjekt(String url) {
    this.setUrl(url);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ArrayList<String> getTags() {
    return tags;
  }

  public void setTags(ArrayList<String> tags) {
    this.tags = tags;
  }

  public void addTag(String tag) {
    tags.add(tag);
  }

  public int getNumOfTags() {
    return tags.size();
  }

}

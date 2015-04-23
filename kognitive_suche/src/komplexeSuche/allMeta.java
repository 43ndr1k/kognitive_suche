package komplexeSuche;

import java.util.ArrayList;

import de.leipzig.htwk.controller.Controller;
import de.leipzig.htwk.faroo.api.Results;

// @author Franz Schwarzer

public class allMeta {

  Tag_Meta[] meta = new Tag_Meta[9];
  ArrayList<String[]> keys;

  UrlObjektList allMeta(Results results, String searchword) {

    for (int i = 0; i < results.getResults().size(); i++) {
      meta[i] = new Tag_Meta(results.getResults().get(i).getUrl(), i);
      meta[i].start();

    }

    while (ready() == false) {

    }

    UrlObjektList list = new UrlObjektList(searchword);
    for (int i = 0; i < results.getResults().size(); i++) {
      UrlObjekt urlObjekt = new UrlObjekt(Statics.url[i]);
      for (int z = 0; z < Statics.urlkeys[i].length; z++) {
        urlObjekt.addTag(Statics.urlkeys[i][z]);
      }
      list.addUrlObject(urlObjekt);

    }
    return list;
  }

  public boolean ready() {
    for (int i = 0; i < 9; i++) {
      if (meta[i].isAlive() == false) {
        return false;
      }
    }
    return true;

  }

}

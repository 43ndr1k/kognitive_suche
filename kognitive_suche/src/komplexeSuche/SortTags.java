package komplexeSuche;

import java.util.Comparator;


public class SortTags implements Comparator<tags> {


  /**
   * @author Tobias Lenz
   */

  @Override
  public int compare(tags t1, tags t2) {

    return t1.getcount().compareTo(t2.getcount());
  }
}

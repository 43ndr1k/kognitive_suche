package komplexe.suche;

import java.util.Comparator;


public class SortTags implements Comparator<Tags> {


  /**
   * @author Tobias Lenz
   */

  @Override
  public int compare(Tags t1, Tags t2) {

    return t1.getcount().compareTo(t2.getcount());
  }
}

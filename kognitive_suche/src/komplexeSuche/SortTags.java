package komplexeSuche;

import java.util.Comparator;

public class SortTags implements Comparator<Keywords>{
  
  /**
   * @author Tobias Lenz
   */

	@Override
	public int compare(Keywords t1, Keywords t2){
		return t1.getcount().compareTo(t2.getcount());
	}
}

package cognitive.search;

import java.util.Comparator;

/**
 * 
 * @author Tobias Lenz
 *Hilfsklasse, um die Klasse ReturnTagList vergleichbar zu machen. Dadurch kann sie sortiert werden.
 */
public class ReturnTagListSort implements Comparator<ReturnTagObject> {
  @Override
public int compare(ReturnTagObject t1, ReturnTagObject t2){
  return t2.getPriority().compareTo(t1.getPriority());
}
}

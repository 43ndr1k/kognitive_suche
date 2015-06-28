package cognitiveAPI;

import java.util.Comparator;

/**
 * Hilfsklasse, um die Klasse {@code ReturnTagList} vergleichbar zu machen. Dadurch kann sie sortiert werden.
 * 
 * @author Tobias Lenz         
 */
public class ReturnTagListSort implements Comparator<ReturnTagObject> {
  @Override
  public int compare(ReturnTagObject t1, ReturnTagObject t2) {
    return t2.getPriority().compareTo(t1.getPriority());
  }
}

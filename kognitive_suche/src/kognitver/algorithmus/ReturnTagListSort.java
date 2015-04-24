package kognitver.algorithmus;

import java.util.Comparator;

/**
 * 
 * @author Tobias Lenz
 *
 */
public class ReturnTagListSort implements Comparator<ReturnTagObject> {
  @Override
public int compare(ReturnTagObject t1, ReturnTagObject t2){
  return t2.getPriority().compareTo(t1.getPriority());
}
}

package History;

import static org.junit.Assert.assertEquals;
/**
 * 
 * 
 * 
 * @author Sadik Ulusan
 * 
 *
 */

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import de.leipzig.htwk.search.history.HistoryObject;
import de.leipzig.htwk.search.history.SearchHistory;

public class SearchHistoryTest {

  static SearchHistory testHistory;

  @BeforeClass
  public static void prepare() {
    testHistory = new SearchHistory();

  }

  @Test
  public void testHistory() {
    int length;
    HistoryObject foo;
   
    ArrayList<HistoryObject> searchHistory;
    searchHistory = testHistory.getSearches();
    length = searchHistory.size();
    testHistory.addSearch("JUnit Test");
    assertEquals(length + 1, searchHistory.size());

    searchHistory = testHistory.getSearches();
    foo = searchHistory.get(searchHistory.size() - 1); // Foo = das letzte Element der Liste
    assertEquals("JUnit Test", foo.searchWord);

    testHistory.historyData.remove(testHistory.historyData.size() - 1); // letzter Testfall wird aus
                                                                        // der searchHistory
                                                                        // geloescht
    testHistory.saveSearch();
  }
}

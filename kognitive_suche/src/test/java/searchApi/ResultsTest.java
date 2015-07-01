package searchApi;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.leipzig.htwk.searchApi.Result;
import de.leipzig.htwk.searchApi.Results;

/**
 * @author Sadik Ulusan
 */

public class ResultsTest {

  @Test
  public void test() {
    Results testObject;
    testObject = new Results();

    ArrayList<Result> ArrayListTest;
    ArrayListTest = new ArrayList<Result>();

    assertNull(testObject.getResults());

    testObject.setResults(ArrayListTest);
    assertEquals(ArrayListTest, testObject.getResults());


  }


}

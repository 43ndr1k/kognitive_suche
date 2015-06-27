package de.leipzig.htwk.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.leipzig.htwk.searchApi.Result;
import de.leipzig.htwk.searchApi.Results;

public class ResultsTest {

  @Test
  public void test() {
    Results testObject;
    testObject = new Results();
    
    List<Result> ArrayListTest;
    ArrayListTest = new ArrayList<Result>();
    
    assertNull( testObject.getResults());
    
    testObject.setResults(ArrayListTest);
    assertEquals(ArrayListTest ,testObject.getResults());
    
    
  }
 

}

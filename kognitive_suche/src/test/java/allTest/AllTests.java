package allTest;

import kognitiveSuche.ApiKognitiveSearchTest;

import kognitiveSuche.CognitiveTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.leipzig.htwk.tests.DeleteTagTest;
import History.SearchHistoryTest;
import searchApi.ResultTest;
import searchApi.ResultsTest;
import tagList.ReturnTagListTest;
import tagList.TxtReaderTest;
import faroo.api.ApiTestFaroo;

/**
 * @author Sadik Ulusan
 */

@RunWith(Suite.class)
@SuiteClasses({ApiTestFaroo.class, ApiKognitiveSearchTest.class, DeleteTagTest.class,
    ReturnTagListTest.class, CognitiveTest.class, SearchHistoryTest.class, TxtReaderTest.class,
    ResultTest.class, ResultsTest.class})
public class AllTests {

}

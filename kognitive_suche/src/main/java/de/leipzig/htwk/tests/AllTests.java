package de.leipzig.htwk.tests;

import kognitiveSuche.ApiKognitiveSearchTest;
import kognitiveSuche.CognitiveTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import History.SearchHistoryTest;
import tagList.ReturnTagListTest;
import tagList.TxtReaderTest;
import faroo.api.ApiTestFaroo;

@RunWith(Suite.class)
@SuiteClasses({ApiTestFaroo.class, ApiKognitiveSearchTest.class, DeleteTagTest.class, 
    ReturnTagListTest.class, CognitiveTest.class, SearchHistoryTest.class,
    TxtReaderTest.class, ResultTest.class, ResultsTest.class})
public class AllTests {

}

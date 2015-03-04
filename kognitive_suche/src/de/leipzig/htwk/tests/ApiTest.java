package de.leipzig.htwk.tests;


import de.leipzig.htwk.faroo.api.APIExecption;
import de.leipzig.htwk.faroo.api.Api;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Testklasse für die Api.
 */

public class ApiTest {

        private String key = "2CJIbhzsHU4nlSqBVZ2OP3fimb4_";
        private String url = "http://www.faroo.com/api?";


        /**
         * Hier werden die verschiedenen query Methoden getestet.
         * @throws Exception
         */

        @Test
        public void testQuery_with_query() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World").getResults().size() > 0));
        }

         @Ignore
        public void testQuery_with_query2() throws Exception {
             Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("hfgf").getResults().size() > 0));
        }

        @Test
        public void testQuery_with_query_Language() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", "en").getResults().size() > 0));
        }

        @Test
        public void testQuery_with_query_Length() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 5).getResults().size() > 0));
        }

        @Test
        public void testQuery_with_query_start_length() throws Exception {
            Api api = new Api(key, url);
            Assert.assertFalse("Liste ist gefüllt", (api.query("Hello World", 4, 7).getResults().size() != 7));
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 5, 7).getResults().size() == 7));
            try {
                api.query("Hello World",-1,7).getResults();
                fail();
            }
            catch (APIExecption e){}
        }

    @Ignore
    public void testQuery_with_start_query_language_src() throws Exception {
        Api api = new Api(key, url);
        Assert.assertTrue("Liste ist gefüllt", (api.query(1, "Hello World", "de", "web").getResults().size() != 7));
        Assert.assertTrue("Liste ist gefüllt", (api.query(1, "Hello World", "de", "web").getResults().size() == 10));
        try {
            api.query(-5,"Hello World","de", "src").getResults();
            fail();
        }
        catch (APIExecption e){}
    }

    @Test
    public void testQuery_with_start_query_language_src_keinErgebnis() throws Exception {
        Api api = new Api(key, url);
        Assert.assertTrue("Liste ist gefüllt", (api.query(1, "hfzdgsers", "de", "web").getResults().size() == 1));
    }

    @Test
    public void testQuery_with_start_query_language_src_keinErgebnis2() throws Exception {
        Api api = new Api(key, url);
        Assert.assertTrue("Liste ist gefüllt", (api.query(1, "hfzdgsersgdghfdgdgdgdgddfd", "de", "web").getResults().size()==1));
    }

    @Test
    public void testQuery_with_start_query_language_src_keinErgebnis3() throws Exception {
        Api api = new Api(key, url);
        String a = api.query(1, "hfzdgsersgdghfdgdgdgdgddfd", "de", "web").getResults().get(0).getTitle();
        Assert.assertEquals("Liste ist gefüllt", "Kein Ergebnis", a);
    }


        @Ignore
        public void testQuery_with_query_start_length_language() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 4, 5, "en").getResults().size() > 0));
        }

        @Ignore
        public void testQuery_with_query_start_length_language_src() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 2, 10, "en", "news").getResults().size() > 0));
        }
        @Ignore
         public void testQuery_with_query_start_length_language_src_kwic() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 2, 8, "en", "news", "false").getResults().size() > 0));
        }

        @Ignore
         public void testQuery_with_query_start_length_language_src_kwic_i() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 7, 10, "en", "news", "false", true).getResults().size() > 0));
        }

        @Ignore
         public void testQuery_with_query_start_language_src_kwic_i() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 7, "en", "news", "false", true).getResults().size() > 0));
        }

        @Ignore
         public void testQuery_with_query_language_src_kwic_i() throws Exception {
            Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", "en", "news", "false", true).getResults().size() > 0));
        }

          @Ignore
         public void testQuery_with_query_start_src_kwic() throws Exception {
              Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", 5, "news", "false").getResults().size() <= 0));
        }

           @Ignore
         public void testQuery_with_query_language_i() throws Exception {
               Api api = new Api(key, url);
            Assert.assertTrue("Liste ist gefüllt", (api.query("Hello World", "en", true).getResults().size() <= 0));
        }
    }

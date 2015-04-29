package de.leipzig.htwk.searchApi;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;

public class SearchApi {
    public static void main(String[] args) throws InterruptedException {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface,



        // Declaring and initialising the HtmlUnitWebDriver
        HtmlUnitDriver unitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);

        //unitDriver.setJavascriptEnabled(true);

        //?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=Harry%20Potter


        unitDriver.get("https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=ente");
/*
        Sendet die Suchanfrage und klickt auf den Suchbutton
        WebElement query = unitDriver.findElement(By.name("q"));
        query.sendKeys("ente");
        query.submit();*/


        String domainName = unitDriver.getTitle();
        System.out.println("Domain name is " + domainName);

        // List<WebElement> test = unitDriver.findElements(By.className("links_main links_deep"));
      /*  List<WebElement> test1 = unitDriver.findElements(By.id("links"));
        //System.out.println(test);

        for (WebElement tt : test1) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________################______________");

            //System.out.println(tt.toString());
        }*/

        List<WebElement> test2 = unitDriver.findElements(By.className("large"));

         for (WebElement tt : test2) {
            System.out.println(tt.getText().toString());
            System.out.println("??????????????????????????????");

            //System.out.println(tt.toString());
        }

        List<WebElement> test3 = unitDriver.findElements(By.className("url"));

        for (WebElement tt : test3) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________");

            //System.out.println(tt.toString());
        }

        List<WebElement> test4 = unitDriver.findElements(By.className("snippet"));

        for (WebElement tt : test4) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________");

            //System.out.println(tt.toString());
        }

        System.out.println("########################################################################################");

  /*      WebElement next = unitDriver.findElement(By.className("navbutton"));
        next.click();
        //Thread.sleep(5000);

        List<WebElement> test2 = unitDriver.findElements(By.id("links_wrapper"));
        //System.out.println(test);

        for (WebElement tt : test2) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________");
            System.out.println(tt.toString());
        }
*/
    }

    /**
     * Dienen für die korrekte Darstellung des Suchbegriffes. Muss in html verträgliche Darstellung gebracht werden.
     */
    private static String[] REPLACEMENTS = { "%", "%25", " ", "%20", "!",
            "%21", "#", "%23", "\\$", "%24", "\"", "%22", "&", "%26", "’",
            "%27", "\\(", "%28", "\\)", "%29", "\\*", "%2A", "\\+", "%2B", ",",
            "%2C", "/", "%2F", ":", "%3A", ";", "%3B", "=", "%3D", "\\?",
            "%3F", "@", "%40", "\\[", "%5B", "]", "%5D" };

    /**
     * Diese Methode codiert die query nach URL-Envording Richtlien
     * @param query query
     * @return url Bearbeitete query String
     */
    private String encoding(String query) {

        for (int i = 0; i < REPLACEMENTS.length - 1; i = i + 2) {
            query = query.replaceAll(REPLACEMENTS[i], REPLACEMENTS[i + 1]);
        }
        return query;
    }

    /**
     * Anfrage an die Suchmaschine stellen, url wird zusammen gesezt und codiert.
     * @param query - Suchwort
     * @return Results Liste
     * @throws SearchApiExecption
     */
    public SearchResults query(String query) throws SearchApiExecption {
        query = encoding(query);
        return Searching(getURL() + "&q=" + query);
    }

    /**
     *
     * @return url Beinhaltet die URL zu der Suchmaschine.
     */
    public String getURL(){
        String url = "https://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1";
        return url;
    }

    private SearchResults Searching(String url){

        HtmlUnitDriver unitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        unitDriver.get(url);

        List<WebElement> test1 = unitDriver.findElements(By.id("links"));


        for (WebElement tt : test1) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________################______________");


        }

        List<WebElement> test2 = unitDriver.findElements(By.className("large"));

        for (WebElement tt : test2) {
            System.out.println(tt.getText().toString());
            System.out.println("??????????????????????");


        }

        List<WebElement> test3 = unitDriver.findElements(By.className("url"));

        for (WebElement tt : test3) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________");


        }

        List<WebElement> test4 = unitDriver.findElements(By.className("snippet"));

        for (WebElement tt : test4) {
            System.out.println(tt.getText().toString());
            System.out.println("_________________________________");


        }
        return null;
    }

}




//-------------

//____________







      /*



        WebClient webClient = new WebClient();
        //driver.setJavascriptEnabled(true);



        //System.out.println(driver.isJavascriptEnabled());

        //driver.setJavascriptEnabled(true);
        // And now use this to visit Google
        //driver.get("http://blog.fastreboot.de");

        try {
            HtmlPage page = webClient.getPage("http://blog.fastreboot.de");
            page.getTitleText();
            page.getElementsByName("entry-content");
        } catch (IOException e) {
            e.printStackTrace();
        }


*/

//driver.get("https://duckduckgo.com/?q=test&ia=about");
// Enter the query string "Cheese"
//WebElement query = driver.findElement(By.name("q"));
//query.sendKeys("ente");

//System.out.println(driver.g.getTitle().toString());



// Sleep until the div we want is visible or 5 seconds is over
/*            long end = System.currentTimeMillis() + 5000;
            List<WebElement> resultsDiv;
            while (System.currentTimeMillis() < end) {
                resultsDiv = driver.findElements(By.id("results"));

            }
            List<WebElement> test = driver.findElements(By.className("results"));
            for (WebElement suggestion : test) {
                System.out.println(suggestion.getText());
            }*/

// And now list the suggestions
/*
        //List<WebElement> test = driver.findElements(By.className("entry-content"));
        List<WebElement> test = driver.findElements(By.className("c-info__body"));
        System.out.println(test);

        List<WebElement> allSuggestions = driver.findElements(By.xpath("//*[@class='entry-content']"));
            System.out.println(allSuggestions);

        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
        }

        driver.quit();

        */

//    }

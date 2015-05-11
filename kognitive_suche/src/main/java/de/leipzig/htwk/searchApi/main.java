package de.leipzig.htwk.searchApi;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.net.MalformedURLException;

/**
 * @Autor Hendrik Sawade.
 */
public class main {
    public static void main(String[] args) throws InterruptedException, SearchApiExecption, MalformedURLException {

        PhantomJSDriver driver;
        PhantomjsDriver dr = new PhantomjsDriver();
        driver = dr.getDriver();
        DuckDuckGoSearchApi d = new DuckDuckGoSearchApi("ente", 80, driver); // Maximal 79 Ergebnisse zurzeit!!
        System.out.println("Gesamanzahl an Ergebnissen: " + d.getResultList().getResults().size());

        for (int i = 0; i < d.getResultList().getResults().size();i++) {
            System.out.println("Nr.: " + i);
            System.out.println(d.getResultList().getResults().get(i).getTitle());
            System.out.println(d.getResultList().getResults().get(i).getKwic());
            System.out.println(d.getResultList().getResults().get(i).getUrl());
            System.out.println("#########################################################");
        }
    }

}

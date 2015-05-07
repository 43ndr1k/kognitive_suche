package de.leipzig.htwk.searchApi;

import java.net.MalformedURLException;

/**
 * @Autor Hendrik Sawade.
 */
public class main {
    public static void main(String[] args) throws InterruptedException, SearchApiExecption, MalformedURLException {

        DuckDuckGoSearchApi d = new DuckDuckGoSearchApi("ente", 300);
        System.out.println("Gesamanzahl an Ergebnissen: " + d.getDuckDuckGoResults().getResults().size());

        for (int i = 0; i < d.getDuckDuckGoResults().getResults().size();i++) {
            System.out.println("Nr.: " + i);
            System.out.println(d.getDuckDuckGoResults().getResults().get(i).getTitle());
            System.out.println(d.getDuckDuckGoResults().getResults().get(i).getKwic());
            System.out.println(d.getDuckDuckGoResults().getResults().get(i).getUrl());
            System.out.println("#########################################################");
        }
    }

}

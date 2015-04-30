package de.leipzig.htwk.searchApi;

/**
 * Created by hendrik on 30.04.15.
 */
public class main {
    public static void main(String[] args) throws InterruptedException, SearchApiExecption {

        DuckDuckGo d = new DuckDuckGo("ente", 60);
        System.out.println(d.getDuckDuckGoResults().getResults().size());
    }

}

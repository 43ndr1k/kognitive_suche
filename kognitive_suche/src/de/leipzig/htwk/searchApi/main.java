package de.leipzig.htwk.searchApi;

/**
 * @Autor Hendrik Sawade.
 */
public class main {
    public static void main(String[] args) throws InterruptedException, SearchApiExecption {

        DuckDuckGoSearchApi d = new DuckDuckGoSearchApi("Hallo Welt", 40);
        System.out.println(d.getDuckDuckGoResults().getResults().size());

        for (int i = 0; i < d.getDuckDuckGoResults().getResults().size();i++) {
            System.out.println(d.getDuckDuckGoResults().getResults().get(i).getTitle());
            System.out.println(d.getDuckDuckGoResults().getResults().get(i).getKwic());
            System.out.println(d.getDuckDuckGoResults().getResults().get(i).getUrl());
            System.out.println("#########################################################");
        }
    }

}

package de.leipzig.htwk.searchApi;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Hier wird ein Objekt der Suchergebnisse generiert mir den entsprechenden getter Methoden. Parameter sind folgende:
 *
 * @param title
 * @param kwic
 * @param url
 */
public class Result {


    String title = "", kwic = "";
    String url = null;


    /**
     * Hier wird ein Objekt der Suchergebnisse generiert mir den entsprechenden getter Methoden.
     * @param title Titel der Seite
     * @param kwic Erster Satz der Seite
     * @param url komplette url
     */
    public Result(String title, String kwic, String url) {
        this.title = title;
        this.kwic = kwic;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getKwic() {
        return kwic;
    }

    public String getUrl() {
        return url;
    }

}


package de.leipzig.htwk.searchApi;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Sellt verschidene Exeption für die Api bereit.
 */
public class SearchApiExecption extends Exception {

    /**
     * Exception Methode für Fehlerabfänge für den Faroo Server.
     *
     * @param code Fehlercode
     */
    public SearchApiExecption(int code) {
        super("Server antwortete mit CODE: " + code);
    }

    /**
     * Nimmt den Fehlercode an und sendet ihn an super weiter.
     * @param wort
     */

    public SearchApiExecption(String wort) {
        super(wort);
    }
}

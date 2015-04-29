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
     * Eine Exeption die sagt, dass die result Liste leer ist.
     */
    public SearchApiExecption() {
        super("Result Liste ist leer");
    }

    /**
     * Nimmt den Fehlercode an und sendet ihnan super weiter.
     * @param wort
     */

    public SearchApiExecption(String wort) {
        super(wort);
    }
}

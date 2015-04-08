package de.leipzig.htwk.faroo.api;

/**
 * @Autor Hendrik Sawade.
 */

/**
 * Sellt verschidene Exeption für die Api bereit.
 */
public class APIExecption extends Exception {

    /**
     * Exception Methode für Fehlerabfänge für den Faroo Server.
     *
     * @param code Fehlercode
     */
    public APIExecption(int code) {
        super("Server antwortete mit CODE: " + code);
    }

    /**
     * Eine Exeption die sagt, dass die result Liste leer ist.
     */
    public APIExecption() {
        super("Result Liste ist leer");
    }

    /**
     * Nimmt den Fehlercode an und sendet ihnan super weiter.
     * @param wort
     */

    public APIExecption(String wort) {
        super(wort);
    }
}

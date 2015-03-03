package de.leipzig.htwk.faroo.api;

/**
 * Created by hendrik.
 */
public class APIExecption extends Exception {

    /**
     * Exception Methode für Fehlerabfänge für den Faroo Server.
     *
     * @param code
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
    public APIExecption(String a) {
        super(a);
    }
}

package Faroo;

/**
 * Created by hendrik on 09.12.14.
 */
public class APIExecption extends Exception {

    /**
     * Exception Methode für Fehlerabfänge für den Faroo Server.
     * @param code
     */
    public APIExecption(int code) {
        super("Server antwortete mit CODE: " + code);
    }
}

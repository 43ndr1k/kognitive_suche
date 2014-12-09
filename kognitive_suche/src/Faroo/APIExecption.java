package Faroo;

/**
 * Created by hendrik on 09.12.14.
 */
public class APIExecption extends Exception {

    public APIExecption(int code) {
        super("Server antwortete mit CODE: " + code);
    }
}

package ar.fiuba.tdd.tp.server.exceptions;

/**
 * Created by jorlando on 28/04/16.
 */
public class BadGameNameException extends Exception {

    public BadGameNameException() {
        super();
    }

    public BadGameNameException(String message) {
        super(message);
    }

    public BadGameNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadGameNameException(Throwable cause) {
        super(cause);
    }
}

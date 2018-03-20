package by.kozik.quest.exception;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
public class WrongParameterException extends Exception {

    public WrongParameterException() {
    }

    public WrongParameterException(String message) {
        super(message);
    }

    public WrongParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongParameterException(Throwable cause) {
        super(cause);
    }

    public WrongParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

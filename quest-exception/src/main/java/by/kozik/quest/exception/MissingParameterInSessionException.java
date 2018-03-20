package by.kozik.quest.exception;

/**
 * Created by Serge_Kozik on 3/2/2017.
 */
public class MissingParameterInSessionException extends Exception {
    public MissingParameterInSessionException() {
    }

    public MissingParameterInSessionException(String message) {
        super(message);
    }

    public MissingParameterInSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingParameterInSessionException(Throwable cause) {
        super(cause);
    }

    public MissingParameterInSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

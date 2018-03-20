package by.kozik.quest.exception;

/**
 * Created by Serge_Kozik on 3/2/2017.
 */
public class SaveBeanException extends Exception {
    public SaveBeanException() {
    }

    public SaveBeanException(String message) {
        super(message);
    }

    public SaveBeanException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveBeanException(Throwable cause) {
        super(cause);
    }

    public SaveBeanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

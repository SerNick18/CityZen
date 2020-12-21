package controller.gestioneUtenza;
/**
 * Questa servlet server per gestire i messaggi di errore
 * lanciati dal nostro sistema.
 * */
public class MyRuntimeException extends RuntimeException{
    public MyRuntimeException() { }
    public MyRuntimeException(String message) {
        super(message);
    }
    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    public MyRuntimeException(Throwable cause) {
        super(cause);
    }
    public MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

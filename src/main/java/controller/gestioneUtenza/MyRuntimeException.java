package controller.gestioneUtenza;
/**
 * Questa servlet server per gestire i messaggi di errore
 * lanciati dal nostro sistema.
 * */
public class MyRuntimeException extends RuntimeException {
    public MyRuntimeException() {
        super();
    }
    public MyRuntimeException(String message) {
        super(message);
    }
}

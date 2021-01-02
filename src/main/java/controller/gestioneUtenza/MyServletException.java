package controller.gestioneUtenza;

import javax.servlet.ServletException;
/**
 * Questa servlet server per gestire i messaggi di errore
 * lanciati dal nostro sistema.
 *
 */
public class MyServletException extends ServletException {
    /**
     * Costruttore che richiama il costruttore della superclasse.
     */
    public MyServletException() {
        super();
    }
    /**
     * Costruttore che richiama il costruttore della superclasse con
     * parametro esplicito.
     * @param message messaggio da passare al costruttore della superclasse
     */
    public MyServletException(String message) {
        super(message);
    }
    /**
     * Costruttore che richiama il costruttore della superclasse con
     * parametri espliciti.
     * @param message messaggio da passare al costruttore della superclasse
     * @param rootCause rootCause
     */
    public MyServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
    /**
     * Costruttore che richiama il costruttore della superclasse con
     * parametro esplicito.
     * @param rootCause rootCause
     */
    public MyServletException(Throwable rootCause) {
        super(rootCause);
    }
}

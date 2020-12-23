package controller.gestioneUtenza;
/**
 * Questa servlet server per gestire i messaggi di errore
 * lanciati dal nostro sistema.
 * */
public class MyRuntimeException extends RuntimeException {
    /**.
     * Costruttore che richiama il costruttore della superclasse
     */
    public MyRuntimeException() {
        super();
    }
    /**.
     * Costruttore che richiama il costruttore della superclasse con
     * parametro esplicito
     * @param message messaggio da passare al costruttore della superclasse
     */
    public MyRuntimeException(String message) {
        super(message);
    }
}

package model.gestioneDati.modelObjects;

/**
 * Interfaccia che fornisce il metodo di
 * notifica per il design pattern observer.
 */
public interface Observer {
    /**
     * Firma del metodo per la notifica.
     * @param s segnalazione
     */
    void update(AbstractSegnalazione s);
}

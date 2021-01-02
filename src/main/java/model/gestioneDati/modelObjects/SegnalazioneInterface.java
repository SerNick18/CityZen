package model.gestioneDati.modelObjects;

import java.util.Date;

/**
 * Interfaccia per i metodi del design pattern Proxy.
 */
public interface SegnalazioneInterface {
    /**
     * Firma del metodo per recuperare l'id.
     * @return id
     */
    int getId();
    /**
     * Firma del metodo per recuperare l'oggetto.
     * @return oggetto
     */
    String getOggetto();

    /**
     * Firma del metodo per recuperare la priorità.
     * @return int
     */
    int getPriorita();

    /**
     * Firma del metodo per recuperare il nome del cittadino.
     * @return nome del cittadino
     */
    String getNomeCittadino();

    /**
     * Firma del metodo per recuperare il numero di solleciti.
     * @return numero solleciti
     */
    int getNumSolleciti();

    /**
     * Firma del metodo per recuperare la via della segnalazione.
     * @return via
     */
    String getVia();

    /**
     * Firma del metodo per recuperare il civico della segnalazione.
     * @return civico
     */
    int getCivico();

    /**
     * Firma del metodo per recuperare lo stato della segnalazione.
     * @return stato
     */
    String getStato();

    /**
     * Firma del metodo per recuperare la data dell'inoltro della segnalazione.
     * @return data
     */
    Date getDataSegnalazione();

    /**
     * Firma del metodo per recuperare la descrizione della segnalazione.
     * @return descrizione
     */
    String getDescrizione();

    /**
     * Firma del metodo per recuperare la foto della segnalazione.
     * @return foto
     */
    String getFoto();

    /**
     * Firma del metodo per recuperare
     * il cittadino associato alla segnalazione.
     * @return Cittadino
     */
    Cittadino getCittadino();

    /**
     * Firma del metodo per recuperare
     * l'id della segnalazione chiusa a cui fa riferimento.
     * @return int
     */
    int getRiaperta();

}

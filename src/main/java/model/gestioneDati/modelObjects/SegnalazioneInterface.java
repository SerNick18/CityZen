package model.gestioneDati.modelObjects;

import java.util.Date;

/**
 *
 */
public interface SegnalazioneInterface {
    /**
     *
     * @return id
     */
    int getId();
    /**
     *
     * @return oggetto
     */
    String getOggetto();

    /**
     *
     * @return int
     */
    int getPriorita();

    /**
     *
     * @return nome del cittadino
     */
    String getNomeCittadino();

    /**
     *
     * @return numero solleciti
     */
    int getNumSolleciti();

    /**
     *
     * @return via
     */
    String getVia();

    /**
     *
     * @return civico
     */
    int getCivico();

    /**
     *
     * @return stato
     */
    String getStato();

    /**
     *
     * @return data
     */
    Date getDataSegnalazione();

    /**
     *
     * @return descrizione
     */
    String getDescrizione();

    /**
     *
     * @return foto
     */
    String getFoto();

    /**
     *
     * @return Cittadino
     */
    Cittadino getCittadino();

    /**
     *
     * @return int
     */
    int getRiaperta();

}

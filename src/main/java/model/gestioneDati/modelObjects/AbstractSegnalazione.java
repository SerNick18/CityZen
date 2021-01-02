package model.gestioneDati.modelObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che fornisce metodi per il design pattern observer.
 */
public abstract class AbstractSegnalazione {
    /**
     * Lista di osservatori collegati.
     */
    private List<Observer> observer = new ArrayList<Observer>();

    /**
     * Metodo per aggiungere un osservatore.
     * @param o osservatore - precondizioni: o != null
     * postcondizione: observer.get(o) != null
     */
    public void addObserver(Observer o) {
        observer.add(o);
    }

    /**
     * Metodo per rimuovere un osservatore.
     * @param o osservatore - precondizioni: o != null
     * postcondizione: observer.get(o) == null
     */
    public void removeObserver(Observer o) {
        observer.remove(o);
    }

    /**
     * Metodo che notifica gli osservatori.
     */
    public void notifyObservers() {
        for (Observer o:observer) {
            o.update(this);
        }
    }
}

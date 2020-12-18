package model.gestioneDati.modelObjects;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSegnalazione {
    private List<Observer> observer = new ArrayList<Observer>();

    public void addObserver(Observer o){
        observer.add(o);
    }

    public void removeObserver(Observer o){
        observer.remove(o);
    }

    public void notifyObservers(){
        for(Observer o:observer)
            o.update(this);
    }
}

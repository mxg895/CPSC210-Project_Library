//Commented out to for P11 for simplicity of current GUI implementation -- to be added into future GUI
package observables;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    transient List<OnHoldBookObserver> usersOnHold = new ArrayList<>();

    //MODIFIES: this
    //EFFECT: add the given observer to the list of observers
    protected void addObserver(OnHoldBookObserver userOnHold){
        if(!usersOnHold.contains(userOnHold)){
            usersOnHold.add(userOnHold);
        }
    }

    //MODIFIES: this
    //EFFECT: remove the observer from the list of observers
    protected void removeObserver(OnHoldBookObserver userOnHold){
        usersOnHold.remove(userOnHold);
    }

    //EFFECTS: notify the first observer in line about checking out the book, and telling the rest observers not yet
    //remove the first observer from the list of observers
    //set the book's loaned to user as the first observer
    public void notifyObservers(Book book){
        if(!usersOnHold.isEmpty()){
            usersOnHold.get(0).updateFirstInLine(book);
            removeObserver(usersOnHold.get(0));
            for (OnHoldBookObserver o : usersOnHold) {
                o.updateRest(book);
            }
        }
    }
}
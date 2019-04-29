//Commented out to for P11 for simplicity of current GUI implementation -- to be added into future GUI
package observables;

import model.Book;

public interface OnHoldBookObserver {
    void updateFirstInLine(Book book);
    void updateRest(Book book);
}
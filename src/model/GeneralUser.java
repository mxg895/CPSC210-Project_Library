package model;

import observables.OnHoldBookObserver;

import java.util.ArrayList;

public class GeneralUser /*implements OnHoldBookObserver*/ {
    private String userID;
    transient ArrayList<Book> booksOnLoan;
//    COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//    transient OnHoldBooks booksOnHold = new OnHoldBooks();

    public GeneralUser(String userID){
        this.userID = userID;
        this.booksOnLoan = new ArrayList<>();
    }

    //getters
    public String getID() { return userID; }
    public ArrayList<Book> getBooksOnLoan() { return booksOnLoan; }

    //setters
    public void setUserID(String ID) { this.userID = ID; }
    public void setNewBooksOnLoan(){this.booksOnLoan = new ArrayList<>();}

    //REQUIRES: the given book is not in the library
    //MODIFIES: this & book
    //EFFECTS: add the given book to the user's list of books on loan, and add the user to the
    public void addBookToOnLoan(Book book){
        if (!booksOnLoan.contains(book)){
            book.setStatus("on-loan");
            booksOnLoan.add(book);
            book.addUserToLoan(this);
        }
    }

    //REQUIRES: the given book is already in the library
    //MODIFIES: this & book
    //EFFECTS: remove the given book to the user's list of books on loan, remove add the user to the
    public void removeBookFromOnLoan(Book book){
        if (booksOnLoan.contains(book)){
            booksOnLoan.remove(book);
            book.removeUserFromLoan(this);
        }
    }

//    COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//    //MODIFIES: this
//    //EFFECTS: add the given book to books on hold if not not already included, and
//    // add this user to the given book's onHold list
//    public void addBookToOnHold(Book book) {
//        booksOnHold.addBook(book, this);
//    }
//
//    @Override
//    public void updateFirstInLine(Book book) {
//        System.out.println("User " + this.getID() + ": your hold for book " +
//                book.getTitle() +  " is available for check out!");
//    }
//
//    //EFFECTS: update the rest of the users who have placed the book on hold
//    @Override
//    public void updateRest(Book book) {
//        System.out.println("Your position in line for book: " +
//                book.getTitle() + " has moved forward by 1 place. Almost there!");
//    }
//
//    //    Commented out to for P11 for simplicity of current GUI implementation -- to be added into future GUI
//    //EFFECTS: remove the given book from on-hold
//    public void removeBookFromOnHold(Book toRemove){
//        booksOnHold.removeBook(toRemove, this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralUser that = (GeneralUser) o;

        return userID != null ? userID.equals(that.userID) : that.userID == null;
    }

    @Override
    public int hashCode() {
        return userID != null ? userID.hashCode() : 0;
    }
}

//COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OnHoldBooks {
//    private List<Book> booksOnHold = new ArrayList<>();
//
//    //Getters & setters
//    public List<Book> getBooksOnHold() { return booksOnHold; }
//    public void setBooksOnHold(List<Book> booksOnHold) {
//        this.booksOnHold = booksOnHold;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: add the given book to books on hold if not not already included, and
//    // add the user to the given book's onHold list
//    public void addBook(Book book, GeneralUser generalUser) {
//        if (!booksOnHold.contains(book)){
//            this.booksOnHold.add(book);
//            book.addUserToOnHold(generalUser);
//            System.out.println("You have placed book: " + book.getTitle() + " on hold.");
//        }
//        else{
//            System.out.println("You already have book: " + book.getTitle() + " on hold.");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: remove the given book from books on hold
//    // remove the user from the given book's onHold list
//    // set the user to the book's currently loaned to user
//    public void removeBook(Book book, GeneralUser generalUser){
//        booksOnHold.remove(book);
//        book.removeUserFromOnHold(generalUser);
//    }
//}
package ui;

import exceptions.*;
import jSONTools.JSONFileLoader;
import jSONTools.JSONFileSaver;
import model.Book;
import model.GeneralUser;
import model.UsersListManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LibraryBooksManger {
    private Map<Book, GeneralUser> libraryBooks = new HashMap<>();
    /*private ArrayList<GeneralUser> libraryUsers = new ArrayList<>();*/
    private GeneralUser user;
    private JSONFileLoader fileLoader = new JSONFileLoader();
    private UsersListManager usersManager;



    public LibraryBooksManger() throws IOException {
        this.user = new GeneralUser("");
        /*this.libraryUsers = loadLibraryUsers("Users List.txt");
        this.libraryBooks = generateBooks("Library's Book List.txt");*/
        /*this.libraryUsers = loadUsers();*/
        this.libraryBooks = fileLoader.loadLibraryBookList("Library's Book List.json");
        this.usersManager = new UsersListManager(getLibraryBooks());
    }

    public Map<Book, GeneralUser> getLibraryBooks(){return libraryBooks;}
    public GeneralUser getCurrentUser(){return user;}

    //MODIFIES: user
    //EFFECTS: add the user to the
    public void setUpUser(String userID){
        /*user.setUserID(ID);*/
        GeneralUser user = new GeneralUser(userID);
        checkAddToLibraryUsers(user);
    }


    //MODIFIES: this
    //EFFECTS: add to the total library users' list if not already in it
    private void checkAddToLibraryUsers(GeneralUser user){
        if(!usersManager.contains(user)){
            this.user = user;
            this.usersManager.addUser(this.user);
        } else {
            for (GeneralUser ru : usersManager) {
                if (ru.equals(user)){
                    this.user = ru;
                }
            }
        }
    }

    //EFFECTS: print the list of loaned books
    public void printLoanedBooks() {
        System.out.println("You have loaned the following book(s): ");
        for (Book b : user.getBooksOnLoan()) {
            System.out.println(b.getTitle() + ":" + b.getAvailability());
        }
    }


    public void saveUsers() throws IOException {
        FileWriter fw = new FileWriter("Users List.txt");
        PrintWriter pw = new PrintWriter(fw);
        for (GeneralUser ru : usersManager) {
            pw.println(ru.getID());
        }
        pw.close();
        this.usersManager.saveUsers();
    }

    //MODIFIES: libraryBooks
    //EFFECTS: add a book to the onLoan & change the book's status to on-loan only if
    //         the given book title is found in libraryBooks and not in the on-loan list.
    //         throw new UnavailableBookException if book is already on-loan
    //         throw new BookNotFoundException if if the book is not in library
    public void loanBook(Book bookToAdd) throws UnavailableBookException {
        if (libraryBooks.containsKey(bookToAdd)){
            checkLoanBookMethod(bookToAdd);
        } else {
            throw new UnavailableBookException();
        }
    }

    //Helper method for loanBook
    private void checkLoanBookMethod(Book bookToAdd) throws UnavailableBookException {
        if (libraryBooks.get(bookToAdd) == null) {
            bookToAdd = getBookFromMap(bookToAdd);
            user.addBookToOnLoan(bookToAdd);
            libraryBooks.put(bookToAdd, bookToAdd.getLoanedTo());
        }
//        COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//        else if (bookToAdd.getAvailability()=="on-hold" &&
//                bookToAdd.getLoanedTo().equals(user)){
//            user.removeBookFromOnHold(bookToAdd);
//            user.addBookToOnLoan(bookToAdd);
//            libraryBooks.put(bookToAdd, bookToAdd.getLoanedTo());
//        }
        else {
            throw new UnavailableBookException();
        }
        System.out.println("Book " + bookToAdd.getTitle() + " has been added to your account");
    }


    private Book getBookFromMap(Book bookToFind){
        for (Book b: libraryBooks.keySet()) {
            if(b.equals(bookToFind)){
                return b;
            }
        }
        return null;
    }

    //MODIFIES: total & onLoan
    //EFFECTS: if a book is found in onLoan, change the book's status to available &
    //         remove from loaned books
    //         throw new UnavailableBookException if book is not in onLoan
    //         throw new BookNotFoundException if book is not in the library
    public void returnBook(Book bookToReturn) throws UnavailableBookException {
        try {
            if (libraryBooks.get(bookToReturn).equals(user)){
                bookToReturn = getBookFromMap(bookToReturn);
                user.removeBookFromOnLoan(bookToReturn);
                System.out.println("You have returned book " + bookToReturn.getTitle() + " to the library");
//                COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//                bookToReturn.notifyObservers();
                libraryBooks.put(bookToReturn, bookToReturn.getLoanedTo());
            } else {throw new UnavailableBookException();}
        }
        catch (NullPointerException e){
            throw new UnavailableBookException();
        }
    }

//    COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//    //EFFECTS: place the given book to the User's on hold
//    public void placeHold(Book bookToHold){
//        if(!user.getBooksOnLoan().contains(bookToHold)){
//            user.addBookToOnHold(bookToHold);
//        }
//    }

    public void saveBooks() throws IOException {
        FileWriter fw = new FileWriter("Library's Book List.txt");
        PrintWriter pw = new PrintWriter(fw);
        libraryBooks.forEach((book,user)->
                pw.println(book.getTitle() + ":" + book.getAvailability() + " " + printIDNotNull(user)));
        pw.close();
        JSONFileSaver save = new JSONFileSaver();
        save.saveBooksAsJSon(this);
    }


    private String printIDNotNull(GeneralUser user) {
        if (user != null) {
            return user.getID();
        } else {
            return "none";
        }
    }

    //MODIFIES: this
    //EFFECTS: create a new book with the given title and an available status, then
    //         add it to books if it isn't found in the list.
    public void addToAllBooks(Book bookToAdd) throws DuplicateBookException {
        if (libraryBooks.containsKey(bookToAdd)) {
            throw new DuplicateBookException();
        } else {
            libraryBooks.put(bookToAdd, bookToAdd.getLoanedTo());
            System.out.println(bookToAdd.getTitle() + " has been added!");
        }
    }

    //REQUIRES: the book with the given title is already in bookLIst
    //MODIFIES: bookList
    //EFFECTS: change the status of a book in a bookList
    public void changeBookStatus(Book bookToSetAvailable)
            throws BookNotFoundException {
        if(libraryBooks.containsKey(bookToSetAvailable)) {
            libraryBooks.put(bookToSetAvailable,null);
        }
        else{
            throw new BookNotFoundException();
        }
    }

    protected void printBooks() {
        System.out.println("This library's book list includes: ");
        libraryBooks.forEach((book,user)->
                System.out.println(book.getTitle() + ": " + book.getAvailability()));
    }

    public List<Book> getBooksFromKeys(){
        List<Book> libraryBooksToJson = new ArrayList<>(libraryBooks.keySet());
        return  libraryBooksToJson;
    }
}

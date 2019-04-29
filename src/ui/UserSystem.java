//package ui;
//
//import tools.*;
//import exceptions.BookNotFoundException;
//import exceptions.DuplicateBookException;
//import exceptions.UnavailableBookException;
//import model.Book;
//import model.GeneralUser;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//
//public abstract class UserSystem {
//    protected Scanner input = new Scanner(System.in);
//
//    protected FileLoader loader;
//    protected FileSaver saver;
//    protected ArrayList<GeneralUser> libraryUsers = new ArrayList<>();
//    protected Map<Book, GeneralUser> libraryBooks = new HashMap<>();
//
//    protected UserSystem() throws IOException {
//        this.loader = new FileLoader();
//        this.saver = new FileSaver();
//        this.libraryUsers = loader.loadLibraryUsers("Users List.txt");
//        this.libraryBooks = loader.generateBooks
//                ("Library's Book List.txt", libraryUsers, libraryBooks);
//    }
//
//    //EFFECTS: asks the user to register or log in to an account
//    public void runUserOptions() throws IOException {
//        String userOption = "";
//        System.out.println("Please select from the following options");
//        /*loader.loadLibraryBooks("Library's Book List.txt", libraryBooks);*/
//        while (!userOption.equals("quit")) {
//            printUserOptions();
//            userOption = input.next();
//            if (userOption.equals("Account")) {
//                manageAccount();
//            }
//        }
//    }
//
//    //EFFECTS: print out a list of options to choose from
//    protected void printUserOptions() {
//        System.out.println("\n" +
//                "Enter Account to manage your account.\n" +
//                "Enter quit to exit");
//    }
//
//    //REQUIRES: Book, books, are non-null
//    //MODIFIES: books
//    //EFFECTS: allows user to manage its book list until quit is entered
//    //Catch DuplicateBook, UnavailableBok, and BookNotFoundException in this method
//    protected void manageAccount() throws IOException {
//        String accountOption = "";
//        while (!accountOption.equals("quit")) {
//            printAccountOptions();
//            accountOption= input.next();
//            try {
//                userChoices(accountOption);
//            } catch (DuplicateBookException e) {
//                System.out.println("This book is already in your library. Please add a new one!");
//            } catch (UnavailableBookException e){
//                System.out.println("This book is not available!Please choose a different book!");
//            } catch (BookNotFoundException e) {
//                System.out.println("This library does not have the book you want.");
//            } finally {
//                System.out.println("Please select your next option");
//            }
//        }
//    }
//
//    //print options for managing the account after log in
//    protected abstract void printAccountOptions();
//
//    //provides interactions based on the account userOption chosen by caller
//    protected abstract void userChoices(String accountOption) throws
//            DuplicateBookException, UnavailableBookException, BookNotFoundException, IOException;
//
//    //MODIFIES: this
//    //EFFECTS: create a new book with the given title and an available status, then
//    //         add it to books if it isn't found in the list.
//    public void addToAllBooks(Book bookToAdd) throws DuplicateBookException {
//        if (libraryBooks.containsKey(bookToAdd)) {
//            throw new DuplicateBookException();
//        } else {
//            libraryBooks.put(bookToAdd, bookToAdd.getLoanedTo());
//            System.out.println(bookToAdd.getTitle() + " has been added!");
//        }
//    }
//
//
//    //EFFECTS: print the list of all books in the library ***TO BE DELETED*
//    protected void printBooks(Map<Book, GeneralUser> allBookUsers) {
//        System.out.println("This library's book list includes: ");
//        allBookUsers.forEach((book,user)->
//                System.out.println(book.getTitle() + ": " + book.getAvailability()));
//    }
//
//    //REQUIRES: the book with the given title is already in bookLIst
//    //MODIFIES: bookList
//    //EFFECTS: change the status of a book in a bookList
//    public void changeBookStatus(Book bookToSetAvailable)
//            throws BookNotFoundException {
//        if(libraryBooks.containsKey(bookToSetAvailable)) {
//            libraryBooks.put(bookToSetAvailable,null);
//        }
//        else{
//            throw new BookNotFoundException();
//        }
//    }
//
//}
package ui;

import exceptions.BookNotFoundException;
import exceptions.DuplicateBookException;
import model.Book;

import java.io.IOException;
import java.util.Scanner;

public class AdminSystem {

    private Scanner input = new Scanner(System.in);

    private LibraryBooksManger booksManger;

    public AdminSystem() throws IOException {
        this.booksManger = new LibraryBooksManger();
    }

    //REQUIRES: Book, books, are non-null
    //MODIFIES: books
    //EFFECTS: allows user to manage its book list until quit is entered
    //Catch DuplicateBook, UnavailableBok, and BookNotFoundException in this method
    protected void manageAccount() throws IOException {
        String accountOption = "";
        while (!accountOption.equals("quit")) {
            printAccountOptions();
            accountOption= input.next();
            try {
                userChoices(accountOption);
            } catch (DuplicateBookException e) {
                System.out.println("This book is already in your library. Please add a new one!");
            } /*catch (UnavailableBookException e){
                System.out.println("This book is not available!Please choose a different book!");
            } */catch (BookNotFoundException e) {
                System.out.println("This library does not have the book you want.");
            } finally {
                System.out.println("Please select your next option");
            }
        }
    }

    //EFFECTS: print out the options for admin
    protected void printAccountOptions() {
        System.out.println("\n" +
                "Enter Add to add a book to the library.\n" +
                "Enter CA to change a book's status to available.\n" +
                "Enter quit to exit");
    }

    private void userChoices(String accountOption) throws DuplicateBookException, BookNotFoundException, IOException {
        if (!accountOption.equals("quit")) {
            Book book = new Book("", "available");
            if (accountOption.equals("Add")) {
                book.requestBookTitle();
                book.setTitle(input.next());
                addToAllBooks(book);
            } else if (accountOption.equals("CA")) {
                book.requestBookTitle();
                book.setTitle(input.next());
                book.setStatus("available");
                changeBookStatus(book);
            }
        }else {
            printBooks();
            saveBooks("Library's Book List.txt");
        }

    }

    //MODIFIES: this
    //EFFECTS: create a new book with the given title and an available status, then
    //         add it to books if it isn't found in the list.
    private void addToAllBooks(Book bookToAdd) throws DuplicateBookException {
        booksManger.addToAllBooks(bookToAdd);
    }

    //REQUIRES: the book with the given title is already in bookLIst
    //MODIFIES: bookList
    //EFFECTS: change the status of a book in a bookList
    private void changeBookStatus(Book bookToSetAvailable) throws BookNotFoundException {
        booksManger.changeBookStatus(bookToSetAvailable);
    }

    //EFFECTS: print the list of all books in the library ***TO BE DELETED*
    private void printBooks() {
        booksManger.printBooks();
    }

    private void saveBooks(String booksFileName) throws IOException {
        booksManger.saveBooks();
    }

}
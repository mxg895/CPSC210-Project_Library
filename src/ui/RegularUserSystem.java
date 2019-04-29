package ui;


import exceptions.UnavailableBookException;
import model.Book;

import java.io.IOException;
import java.util.*;


public class RegularUserSystem {
    private Scanner input = new Scanner(System.in);
    private LibraryBooksManger booksManager;

    public RegularUserSystem() throws IOException {
        this.booksManager = new LibraryBooksManger();
    }

    /*//EFFECTS: asks the user to register or log in to an account
    public void runUserOptions() throws IOException {
        String userOption = "";
        System.out.println("Please select from the following options");
        while (!userOption.equals("quit")) {
            printUserOptions();
            userOption = input.next();
            if (userOption.equals("Account")) {
                manageUserAccount();
            }
        }
    }*/

    /*//EFFECTS: print out a list of options to choose from
    private void printUserOptions() {
        System.out.println("\n" +
                "Enter Account to manage your account.\n" +
                "Enter quit to exit");
    }*/

    public void manageUserAccount() throws IOException {
        setUpUser();
        manageAccount();
    }

    //REQUIRES: Book, books, are non-null
    //MODIFIES: books
    //EFFECTS: allows user to manage its book list until quit is entered
    //Catch DuplicateBook, UnavailableBok, and BookNotFoundException in this method
    private void manageAccount() throws IOException {
        String accountOption = "";
        while (!accountOption.equals("quit")) {
            printAccountOptions();
            accountOption= input.next();
            try {
                userChoices(accountOption);
            } /*catch (DuplicateBookException e) {
                System.out.println("This book is already in your library. Please add a new one!");
            }*/ catch (UnavailableBookException e){
                System.out.println("This book is not available!Please choose a different book!");
            }/* catch (BookNotFoundException e) {
                System.out.println("This library does not have the book you want.");
            } */finally {
                System.out.println("Please select your next option");
            }
        }
    }

    //MODIFIES: user
    //EFFECTS: add the user to the list of users if not already on there
    private void setUpUser(){
        System.out.println("Please enter your ID: ");
        Scanner info = new Scanner(System.in);
        String id = info.next();
        booksManager.setUpUser(id);

    }


    //EFFECTS: print out account managing options for the member
    private void printAccountOptions() {
        System.out.println("\n" +
                "Enter Loan to loan a book.\n" +
                "Enter Return to return a book.\n" +
//                "Enter Hold to place hold on a book. \n" +
                "Enter quit to exit");
    }


    private void userChoices(String accountOption) throws UnavailableBookException, IOException {
        if(!accountOption.equals("quit")){
            Book book = new Book("","available");
            if (accountOption.equals("Loan")) {
                book.requestBookTitle();
                book.setTitle(input.next());
                loanBook(book);
            }
            else if (accountOption.equals("Return")){
                book.requestBookTitle();
                book.setTitle(input.next());
                returnBook(book);
            }
//            COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//            else if(accountOption.equals("Hold")){
//                book.requestBookTitle();
//                book.setTitle(input.next());
//                placeHold(book);
//            }
        } else {
            printLoanedBooks();
            saveBooks();
            saveUsers();
            /*user = new GeneralUser("");*/
        }
    }

    //MODIFIES: libraryBooks
    //EFFECTS: add a book to the onLoan & change the book's status to on-loan only if
    //         the given book title is found in libraryBooks and not in the on-loan list.
    //         throw new UnavailableBookException if book is already on-loan
    //         throw new BookNotFoundException if if the book is not in library
    private void loanBook(Book bookToAdd) throws UnavailableBookException{
        booksManager.loanBook(bookToAdd);
    }

    //MODIFIES: total & onLoan
    //EFFECTS: if a book is found in onLoan, change the book's status to available &
    //         remove from loaned books
    //         throw new UnavailableBookException if book is not in onLoan
    //         throw new BookNotFoundException if book is not in the library
    private void returnBook(Book bookToReturn) throws UnavailableBookException {
        booksManager.returnBook(bookToReturn);
    }

//    COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//    //EFFECTS: place the given book on hold
//    private void placeHold (Book bookToHold){
//        booksManager.placeHold(bookToHold);
//    }

    //EFFECTS: print the list of loaned books
    private void printLoanedBooks() {
        booksManager.printLoanedBooks();
    }

    public void saveBooks() throws IOException {
        booksManager.saveBooks();
}

    public void saveUsers() throws IOException {
        booksManager.saveUsers();
    }
}
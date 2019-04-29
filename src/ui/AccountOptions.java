/*
package ui;

import tools.FileLoader;
import tools.FileSaver;
import exceptions.BookNotFoundException;
import exceptions.DuplicateBookException;
import exceptions.UnavailableBookException;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccountOptions {

    private InstructionPrinter printer = new InstructionPrinter();
    private Scanner input = new Scanner(System.in);
    private FileSaver saver;
    private GeneralUser user;
    private FileLoader loader;
    private ArrayList<GeneralUser> libraryUsers = new ArrayList<>();
    private Map<Book, GeneralUser> libraryBooks = new HashMap<>();
    private UserAccountManager uManager = new UserAccountManager();
    private AdminAccountManager aManager = new AdminAccountManager();

    public AccountOptions() throws IOException {
        this.saver = new FileSaver();
        this.user = new GeneralUser("");
        this.loader = new FileLoader();
        this.libraryUsers = loader.loadLibraryUsers("Users List.txt");
        this.libraryBooks = loader.generateBooks
                ("Library's Book List.txt", libraryUsers, libraryBooks);
    }


    //MODIFIES: library's books
    //EFFECTS: allows user to manage the library's based on access to eah account until quit is entered
    //Catch DuplicateBook, UnavailableBok, and BookNotFoundException in this method
    public void requestAccountOptions(String memberOrAdmin)
            throws IOException {
        if (memberOrAdmin == "member"){
            this.user = setUpUser(user);
        }
        String accountOption = "";
        while (!accountOption.equals("quit")) {
            printer.printAccountOptions(memberOrAdmin);
            accountOption= input.next();
            try {
                toRightAccountChoices(accountOption, memberOrAdmin);
            } catch (DuplicateBookException e) {
                System.out.println("This book is already in your library. Please add a new one!");
            } catch (UnavailableBookException e){
                System.out.println("This book is not available!Please choose a different book!");
            } catch (BookNotFoundException e) {
                System.out.println("This library does not have the book you want.");
            } finally {
                System.out.println("Please select your next option");
            }
        }
    }

    private void toRightAccountChoices(String accountOption, String memberOrAdmin)
            throws IOException, BookNotFoundException, DuplicateBookException, UnavailableBookException {
        if (memberOrAdmin == "member"){
            userChoices(accountOption, libraryBooks, user,libraryUsers);
        } else {
            adminChoices(accountOption, libraryBooks);
        }
    }

    //MODIFIES: user
    //EFFECTS: add the user to the list of library users if not already in the list
    private GeneralUser setUpUser(GeneralUser user){
        System.out.println("Please enter your ID: ");
        Scanner info = new Scanner(System.in);
        String ID = info.next();
        user.setUserID(ID);
        user = user.checkAddToLibraryUsers(libraryUsers, user);
        return user;
    }

    public void userChoices(String accountOption, Map<Book, GeneralUser> libraryBooks, GeneralUser user,
                            ArrayList<GeneralUser> libraryUsers)
            throws UnavailableBookException, IOException, DuplicateBookException, BookNotFoundException {
        if(!accountOption.equals("quit")){
            uManager.manageAccountByOption(accountOption, libraryBooks, user);
        } else {
            printer.printLoanedBooks(user);
            saver.saveAllBooks("Library's Book List.txt", libraryBooks);
            saver.saveAllUserIDs("Users List.txt", libraryUsers);
            user = new GeneralUser("");
        }
    }

    public void adminChoices(String accountOption, Map<Book, GeneralUser> libraryBooks)
            throws DuplicateBookException, BookNotFoundException, IOException, UnavailableBookException {
        if (!accountOption.equals("quit")) {
            aManager.manageAdminByOption(accountOption, libraryBooks);
        } else {
            printer.printBooks(libraryBooks);
            saver.saveAllBooks("Library's Book List.txt", libraryBooks);
        }

    }

}
*/

package model;

import java.util.Map;

public class InstructionPrinter {
    //EFFECTS: print out a list of options to choose from
    public void printStartOptions(){
        System.out.println("\n" +
                "Enter Admin for staff login.\n" +
                "Enter General User for user login.\n" +
                "Enter quit to exit");
    }

    //EFFECTS: print out a list of options to choose from
    public void printUserOptions(String showAllowedAccess) {
        System.out.println("\n" +
                "Enter Account to " + showAllowedAccess + ".\n" +
                "Enter quit to exit");
    }

    public void printAccountOptions(String memberOrAdmin){
        if (memberOrAdmin == "member"){
            printMemberAccountOptions();
        } else {
            printAdminAccountOptions();
        }
    }
    //Helper method for print account options, when member is selected
    private void printMemberAccountOptions(){
        System.out.println("\n" +
                "Enter Loan to loan a book.\n" +
                "Enter Return to return a book.\n" +
                "Enter quit to exit");
    }
    //Helper method for print account options, when admin is selected
    private void printAdminAccountOptions(){
        System.out.println("\n" +
                "Enter Add to add a book to the library.\n" +
                "Enter CA to change a book's status to available.\n" +
                "Enter quit to exit");
    }

    public void requestBookTitle() {
        System.out.println("Please enter the title of the book:");
    }

    //EFFECTS: print the list of loaned books
    public void printLoanedBooks(GeneralUser user) {
        System.out.println("You have loaned the following book(s): ");
        for (Book b : user.getBooksOnLoan()) {
            System.out.println(b.getTitle() + ":" + b.getAvailability());
        }
    }

    //EFFECTS: print the list of all books in the library
    public void printBooks(Map<Book, GeneralUser> allBookUsers) {
        System.out.println("This library's book list includes: ");
        allBookUsers.forEach((book,user)->
                System.out.println(book.getTitle() + ": " + book.getAvailability()));
    }
}
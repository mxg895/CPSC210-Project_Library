package ui;

/*import ui.AdminSystem;*/

import model.InstructionPrinter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Library {
    private Scanner scanner = new Scanner(System.in);
    private String libraryOption;
    private InstructionPrinter printer = new InstructionPrinter();
    /*private AccountOptions accountOptions = new AccountOptions();*/

    //EFFECTS: constructor for Library
    public Library() throws IOException {
        runOptions();
    }

    //REQUIRES:
    //MODIFIES: this, admin, and member
    //EFFECTS: directs the caller to different access options (admin vs. member) based on choice
    public void runOptions() throws IOException {
        libraryOption = "";
        System.out.println("Welcome to your Personal Library. " +
                "Please select from the following options");
        while (!libraryOption.equals("quit")) {
            printer.printStartOptions();
            libraryOption = scanner.nextLine();
            if (libraryOption.equals("Admin")) {
                AdminSystem admin = new AdminSystem();
                admin.manageAccount();
            } else if (libraryOption.equals("General User")) {
                RegularUserSystem user = new RegularUserSystem();
                user.manageUserAccount();
            }
        }
    }


    /*//EFFECTS: asks the user to register or log in to an account
    public void runUserOptions(String passToPrint, String memberOrAdmin) throws IOException {
        String userOption = "";
        System.out.println("Please select from the following options");
        while (!userOption.equals("quit")) {
            printer.printUserOptions(passToPrint);
            userOption = scanner.next();
            if (userOption.equals("Account")) {
                *//*accountOptions.requestAccountOptions(memberOrAdmin)*//*;
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        new Library();
    }

}

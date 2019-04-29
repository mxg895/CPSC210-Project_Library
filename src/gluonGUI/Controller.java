package gluonGUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import exceptions.UnavailableBookException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Book;
import model.GeneralUser;
import ui.LibraryBooksManger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Label userIDLabel;
    public Button logOutButton;
    public AnchorPane logOutPane;


    public Button returnButton;
    public Button loanBookButton;

    public JFXTextField bookSearchInput;
    public AnchorPane bookSearchPanel;

    public AnchorPane logInPanel;
    public JFXTextField userIDInput;
    public JFXButton enterButton;
    public Label logInLabel;

    protected LibraryBooksManger lbm = new LibraryBooksManger();

    public Controller() throws IOException {
    }

    //EFFECTS: validate login input and change display to show logout
    // and book-managing options after login
    public void enterButtonClicked(){
        if(userIDInput.validate()){
            String userID = userIDInput.getText();
            confirmLogin(userID);
            displayOptionsAfterLogIn();
            System.out.println("user: " + userID + " logged in!");
        }
    }
    private void confirmLogin(String userID){
        lbm.setUpUser(userID);
    }
    private void displayOptionsAfterLogIn(){
        resetTextfield(userIDInput);
        logInPanel.setVisible(false);
        userIDLabel.setText("Welcome, " + lbm.getCurrentUser().getID() + "!");
        logOutPane.setVisible(true);
        bookSearchPanel.setVisible(true);
    }

    public void loanBookClicked(){
        if(bookSearchInput.validate()){
            String bookName = bookSearchInput.getText();
            Book book = new Book(bookName,"available");
            try {
                lbm.loanBook(book);
                NotificationBox.display("Book Loaned",
                        book.getTitle() + " has been loaned to you");
            } catch (UnavailableBookException e) {
                NotificationBox.display("Book Loan",
                        book.getTitle() + " is not available");;
            } finally {
                resetTextfield(bookSearchInput);
            }
        }
    }

    public void returnBookClicked(){
        if(bookSearchInput.validate()){
            String bookName = bookSearchInput.getText();
            Book book = new Book(bookName,"available");
            try {
                lbm.returnBook(book);
                NotificationBox.display("Book Returned",
                        book.getTitle() + " has been returned");
            } catch (UnavailableBookException e) {
                NotificationBox.display("Book Return",
                        book.getTitle() + " is not available");
            }
            finally {
                resetTextfield(bookSearchInput);
            }
        }
    }

    public void logOutClicked(){
        try {
            lbm.saveBooks();
            lbm.saveBooks();
            resetTextfield(bookSearchInput);
            logInPanel.setVisible(true);
            onlyLogInVisible();
//            new Controller();
            String onLoanReport = getOnLoanReport();
            NotificationBox.display("Your On-Loan List Report", onLoanReport);
        } catch (IOException e) {
            NotificationBox.display("Error","Progress cannot be saved. Please contact Admin.");
        }
    }

    private void resetTextfield(JFXTextField textField){
        textField.setText("");
    }

    private String getOnLoanReport(){
        GeneralUser user = lbm.getCurrentUser();
        String onLoanReport = "You have the following books on-loan: \n";
        for (Book b:user.getBooksOnLoan()){
            onLoanReport = onLoanReport + b.getTitle() + "\n";
        }
        return onLoanReport;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onlyLogInVisible();
        //Number validator
        NumberValidator numValid = new NumberValidator();
        numValid.setMessage("ID must be a number!");
        userIDInput.getValidators().add(numValid);
        userIDInput.focusedProperty().addListener((o, oldVal, newVal)->{
            if(!newVal){
                if (userIDInput.getText().length()!=7){
                    userIDInput.validate();
                }
                userIDInput.validate();
            }
        });

        //Required fields validator
        RequiredFieldValidator required = new RequiredFieldValidator();
        required.setMessage("Missing entry!");
        userIDInput.getValidators().add(required);
        userIDInput.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) userIDInput.validate();
        });

        bookSearchInput.getValidators().add(required);
        bookSearchInput.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) userIDInput.validate();
        });
    }

    private void onlyLogInVisible() {
        //Make listed fields invisible
        bookSearchPanel.setVisible(false);
        logOutPane.setVisible(false);
    }
}

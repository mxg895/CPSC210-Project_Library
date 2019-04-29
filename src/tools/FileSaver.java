package tools;

import model.Book;
import model.GeneralUser;
import model.UsersListManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileSaver {
    /*//MODIFIES: file with the given file name
    //EFFECTS: add the user ID from each user in the list of users to the file.
    public void saveAllUserIDs(String fileName, UsersListManager ulm) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        PrintWriter pw = new PrintWriter(fw);
        for (GeneralUser ru : ulm) {
            pw.println(ru.getID());
        }
        pw.close();
    }

    public void saveAllBooks(String fileName, Map<Book, GeneralUser> bookInfo) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        PrintWriter pw = new PrintWriter(fw);
        bookInfo.forEach((book,user)->
                pw.println(book.getTitle() + ":" + book.getAvailability() + " " + printIDNotNull(user)));
        pw.close();
    }

    private String printAvailability(Book book){
        if(book.getAvailability()){
            return "available";
        } else { return "on-loan";}
    }

    private String printIDNotNull(GeneralUser user) {
        if (user != null) {
            return user.getID();
        } else {
            return "none";
        }
    }
*/
}
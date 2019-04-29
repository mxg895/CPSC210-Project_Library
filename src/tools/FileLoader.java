package tools;

import model.Book;
import model.GeneralUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileLoader {


    //EFFECTS: create and return a list of users from the given file, with each line as a new user
    public ArrayList<GeneralUser> loadLibraryUsers(String usersListFile) throws IOException {
        ArrayList<GeneralUser> users = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(usersListFile));
        if(!lines.isEmpty()){
            for (String line:lines) {
                GeneralUser user = new GeneralUser(line);
                users.add(user);
            }
        }
        return users;
    }

    //MODIFIES: the given hash maps of book and user
    //EFFECTS: turn each line in the given file into a map of book and its current user
    //method written following the FileReaderWriter, with modification for Book

    public Map<Book, GeneralUser> generateBooks
            (String librarysBookListFile, ArrayList<GeneralUser> allLibraryUsers,
             Map<Book, GeneralUser> allBooksAndUsers) throws IOException {
        List<String> toGenerate = Files.readAllLines(Paths.get(librarysBookListFile));
        if(!toGenerate.isEmpty()){
            for (String line : toGenerate) {
                ArrayList<String> sublines = splitOnSpace(line);
                String bookInfo = sublines.get(0);
                Book b = stringToBook(bookInfo);
                String userID = sublines.get(1);
                GeneralUser ru = generateUser(userID);
                assignBookToAllUsers(b, ru, allLibraryUsers, allBooksAndUsers);
            }
        }
        return allBooksAndUsers;
    }


    //EFFECTS: returns a new book from the given string
    private Book stringToBook(String bookInfo){
        ArrayList<String> splitByParameter = splitOnColon(bookInfo);
        String bookName = splitByParameter.get(0);
        String availability = splitByParameter.get(1);
        /*boolean isAvailable = stringToBoolean(availability);*/
        Book b = new Book(bookName, availability);
        return b;
    }

    //EFFECTS: create and return an array list of users from given lines
    private GeneralUser generateUser(String toGenerate){
        GeneralUser ru = createUserFromString(toGenerate);
        return ru;
    }

    //EFFECTS: assign a book to the corresponding user in library's users
    public ArrayList<GeneralUser> assignBookToAllUsers
    (Book toAdd, GeneralUser toAssign, ArrayList<GeneralUser> allLibraryUsers,
     Map<Book, GeneralUser> allBookAndUser) throws IOException {
        if(toAssign!= null){
            for (GeneralUser ru : allLibraryUsers) {
                if(ru.equals(toAssign)){
                    ru.addBookToOnLoan(toAdd);
                    allBookAndUser.put(toAdd,toAdd.getLoanedTo());
                }
            }
        } else {allBookAndUser.put(toAdd, null);}
        return allLibraryUsers;
    }


    //EFFECTS: returns true if availability is available, otherwise false
    private boolean stringToBoolean(String bookStatus){
        if(bookStatus.equals("available")){
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns the user if given string is not none, return null otherwise
    private GeneralUser createUserFromString(String userID) {
        if (!userID.equals("none")) {
            GeneralUser user = new GeneralUser(userID);
            return user;
        } else {
            return null;
        }
    }


    private ArrayList<String> splitOnColon(String line) {
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
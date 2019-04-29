package jSONTools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Book;
import model.GeneralUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class JSONFileLoader {
    private List<Book> bookListFromFile = loadListOfLibraryBooksFromFile("Library's Book List.json");
    private List<GeneralUser> usersToLoad = new ArrayList<>();

    //EFFECTS: return a list of all books from the given file
    private List<Book> loadListOfLibraryBooksFromFile(String fileName){
        Gson gson = new Gson();
        List<Book> libraryBooksList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();
            return gson.fromJson(br, bookListType);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return libraryBooksList;
    }

    private List<GeneralUser> loadGeneralUsersFromFile(String fileName) {
        Gson gson = new Gson();
        List<GeneralUser> usersList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            Type usersListType = new TypeToken<ArrayList<GeneralUser>>() {
            }.getType();
            return gson.fromJson(br, usersListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    //EFFECTS: returns a HashMap of all books in the library and to whom the book is currently loaned to (if any)
    public Map<Book,GeneralUser> loadLibraryBookList(String fileName){
        setUpBookUserRelations();
        return mapBookList();
    }

    private void setUpBookUserRelations(){
        for(Book b : bookListFromFile){
            GeneralUser gu = b.getLoanedTo();
            if(gu!=null){
                if(usersToLoad.contains(gu)){
                    gu = returnMatchingUser(gu);
                    gu.addBookToOnLoan(b);
                }else{
                    gu.setNewBooksOnLoan();
                    gu.addBookToOnLoan(b);
                    this.usersToLoad.add(gu);
                }
            }
        }
    }

    private GeneralUser returnMatchingUser(GeneralUser user){
        for(GeneralUser gUser: usersToLoad){
            if(gUser.equals(user)){
                user = gUser;
            }
        }
        return user;
    }

    private Map<Book,GeneralUser> mapBookList(){
        Map<Book,GeneralUser> libraryBooks = new HashMap<>();
        for (Book book:bookListFromFile) {
            libraryBooks.put(book,book.getLoanedTo());
        }
        return libraryBooks;
    }

    public List<GeneralUser> loadUsers(Map<Book, GeneralUser> booksHashMap){
        List<GeneralUser> usersFromFile = loadGeneralUsersFromFile("General Users List.json");
        Collection<GeneralUser> usersFromHashMap = booksHashMap.values();
        for(GeneralUser userFromFile:usersFromFile){
            if(!usersFromHashMap.contains(userFromFile)){
                userFromFile.setNewBooksOnLoan();
                this.usersToLoad.add(userFromFile);
            }else{
                for(GeneralUser userFromHashMap:usersFromHashMap){
                    if (userFromFile.equals(userFromHashMap) && !usersToLoad.contains(userFromHashMap)){
                        this.usersToLoad.add(userFromHashMap);
                    }
                }
            }
        }
        return usersToLoad;
    }
}

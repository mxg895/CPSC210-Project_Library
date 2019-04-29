package jSONTools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Book;
import model.GeneralUser;
import ui.LibraryBooksManger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONFileSaver {

    //EFFECTS: convert the list of library books as String to be save to JSON File
    public void saveBooksAsJSon(LibraryBooksManger lbm) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        List<Book> booksToJson = lbm.getBooksFromKeys();
        String booksJson = gson.toJson(booksToJson);
        System.out.println(booksJson);
        saveToJson("Library's Book List.json", booksJson);
    }


    //EFFECTS: convert the list of users into String to be saved to a JSON file
    public void saveAllUsers(List<GeneralUser> users){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        List<GeneralUser> generalUsersToJson = users;
        String generalUsersJson = gson.toJson(generalUsersToJson);
        System.out.println(generalUsersJson);
        saveToJson("General Users List.json", generalUsersJson);
    }

    //MODIFIES: file with the given file name
    //EFFECTS: save the given JSON string to the given file
    private void saveToJson(String fileName, String jsonString){
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package model;

import jSONTools.JSONFileLoader;
import jSONTools.JSONFileSaver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UsersListManager implements Iterable<GeneralUser> {
    private List<GeneralUser> libraryUsers = new ArrayList<>();
    private JSONFileSaver fileSaver = new JSONFileSaver();
    private JSONFileLoader jsonfileLoader = new JSONFileLoader();

    public UsersListManager(Map<Book, GeneralUser> libraryBooks) {
        libraryUsers = jsonfileLoader.loadUsers(libraryBooks);
    }

    //*****To be deleted after completing the Gson load & save methods*****
    private void loadUsers(){
        GeneralUser gu1 = new GeneralUser("2293594");
        GeneralUser gu2 = new GeneralUser("2395049");
        GeneralUser gu3 = new GeneralUser("7920395");
        addUser(gu1);
        addUser(gu2);
        addUser(gu3);
    }

    //Getter & setter
    public List<GeneralUser> getLibraryUsers() {
        return libraryUsers;
    }
    public void setLibraryUsers(List<GeneralUser> libraryUsers) {
        this.libraryUsers = libraryUsers;
    }
    public GeneralUser getUserAtIndex(int index){return libraryUsers.get(index);}

    //MODIFIES: this
    //EFFECTS: add the given user to list of users
    public void addUser(GeneralUser user){
        if(!this.libraryUsers.contains(user)){
            this.libraryUsers.add(user);
        };
    }

    //EFFECTS: return true if the given user is in the list of users
    public boolean contains(GeneralUser user){
        return libraryUsers.contains(user);
    }

    //EFFECTS: save the list of users to file
    public void saveUsers() throws IOException {
        fileSaver.saveAllUsers(this.libraryUsers);
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<GeneralUser> iterator() {
        return libraryUsers.iterator();
    }
}

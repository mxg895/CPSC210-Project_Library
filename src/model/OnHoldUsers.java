//COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//package model;
//
//import observables.Subject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OnHoldUsers extends Subject{
//    private List<GeneralUser> onHoldUsers = new ArrayList<>();
//
//    public List<GeneralUser> getOnHoldUsers() {
//        return onHoldUsers;
//    }
//    public void setOnHoldUsers(List<GeneralUser> onHoldUsers) {
//        this.onHoldUsers = onHoldUsers;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: add the given user to the on hold users list if not already in the list, and
//    //add this book to the user's on hold books list
//    public void addUsers(GeneralUser uToAdd, Book book){
//        if(!onHoldUsers.contains(uToAdd)){
//            this.onHoldUsers.add(uToAdd);
//            super.addObserver(uToAdd);
//            if(book.getAvailability().equals("available")) {
//                uToAdd.addBookToOnHold(book);
//                notifyObservers(book);
//            } else {
//                uToAdd.addBookToOnHold(book);
//            }
//            book.setStatus("on-hold");
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: remove the given user from the users list, and remove the user from the list
//    public void removeUser(GeneralUser uToRemove, Book book){
//        this.onHoldUsers.remove(uToRemove);
//        if(onHoldUsers.isEmpty()){
//            book.setStatus("available");
//        }
//        /*removeObserver(uToRemove);*/
//    }
//
//    @Override
//    //EFFECTS: notify the first observer in line about checking out the book, and telling the rest observers not yet
//    //remove the first observer from the list of observers
//    //set the book's loaned to user as the first observer
//    public void notifyObservers(Book book){
//        super.notifyObservers(book);
//        book.setLoanedTo(onHoldUsers.get(0));
//        book.setStatus("on-hold");
//        removeUser(onHoldUsers.get(0),book);
//    }
//}
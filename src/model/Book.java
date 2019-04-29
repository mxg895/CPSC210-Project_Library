package model;


public class Book {
    private String title;
    private String availability = "";
    private GeneralUser loanedTo;
//    COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//    private OnHoldUsers onHold;


    //MODIFIES: this
    //EFFECTS: set a book with a title and its availability availability--true for availability, otherwise false
    public Book (String title, String initial_status){
        this.title = title;
        this.availability = initial_status;
        this.loanedTo = null;
//        COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//        this.onHold = new OnHoldUsers();
    }

    //getters
    public String getTitle(){return this.title; }
    public String getAvailability() {return this.availability; }
    public GeneralUser getLoanedTo() { return loanedTo; }

    //setters:
    public void setTitle(String name){this.title = name;}
    /*public void setOnLoan(){this.availability = ""; }*/
    public void setStatus(String availability){this.availability = availability;}
    public void setLoanedTo(GeneralUser gu) {this.loanedTo = gu;}


    public static void requestBookTitle() {
        System.out.println("Please enter the title of the book:");
    }

    //REQUIRES: given user is not null
    //MODIFIES: this, the given user's list of loaned books
    //EFFECTS: set the given user as the person to whom the book is loaned, and add the book to the
    // user's loaned list of books
    public void addUserToLoan(GeneralUser user){
        this.loanedTo = user;
        this.setStatus("on-loan");
        user.addBookToOnLoan(this);
    }

    //MODIFIES: this
    //EFFECTS: set user for the book to null
    public void removeUserFromLoan(GeneralUser user){
        this.loanedTo = null;
        this.setStatus("available");
        user.removeBookFromOnLoan(this);
    }

//    COMMENTED OUT FOR P11 for simplicity of current GUI implementation -- to be added into future GUI
//    //MODIFIES: this
//    //EFFECTS: add the given user to the on hold users list if not already included, and
//    //add this book to the user's on hold books list
//    public void addUserToOnHold(GeneralUser user){
//        onHold.addUsers(user, this);
//    }
//
//    //EFFECTS: notifies the first user in line about the book becoming available for check out
//    //set the first user in line as the user to whom the book is currently loaned
//    //remove the first user in line from onHold
//    public void notifyObservers(){
//        onHold.notifyObservers(this);
//    }
//
//    public void removeUserFromOnHold(GeneralUser user){
//        onHold.removeUser(user, this);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return title != null ? title.equals(book.title) : book.title == null;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}

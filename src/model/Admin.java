package model;

public class Admin {
    private String adminID;

    public Admin(String ID){
        this.adminID = ID;
    }

    //setter & getter
    public String getAdminID() { return adminID; }
    public void setAdminID(String adminID) { this.adminID = adminID; }
}

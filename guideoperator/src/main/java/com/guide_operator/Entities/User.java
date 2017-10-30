package com.guide_operator.Entities;

public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String adress;
    private String phonenumber;
    private String email;



    private int is_guide;


    public int getIs_guide() {
        return is_guide;
    }

    public void setIs_guide(int is_guide) {
        this.is_guide = is_guide;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public User(int id, String username, String password, String fullname, String adress, String phonenumber, String email) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.fullname = fullname;
//        this.adress = adress;
//        this.phonenumber = phonenumber;
//        this.email = email;
//    }
public User(int id, String username, String password, String fullname, String adress, String phonenumber, String email, int is_guide) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullname = fullname;
    this.adress = adress;
    this.phonenumber = phonenumber;
    this.email = email;
    this.is_guide = is_guide;
}
    public User(String username, String password, String fullname, String adress, String phonenumber, String email, int is_guide) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.adress = adress;
        this.phonenumber = phonenumber;
        this.email = email;
        this.is_guide = is_guide;
    }
    public  User(){}


}

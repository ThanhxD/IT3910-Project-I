package com.guide_operator.Entities;

import java.util.Date;

public class Guide extends User {



    private int id;
    private int userid;
    private String cardnumber;
    private String cardtype;
    private  String status;
    private Date expirationdate;
    private String experience;
    private String gender;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Guide(int id,int userid,String  username, String password, String fullname, String adress, String phonenumber, String email, int is_guide, String cardnumber, String cardtype, String status, Date expirationdate, String experience, String gender) {
        super(username, password, fullname, adress, phonenumber, email, is_guide);
        this.id= id;
        this.userid=userid;
        this.cardnumber = cardnumber;
        this.cardtype = cardtype;
        this.status = status;
        this.expirationdate = expirationdate;
        this.experience = experience;
        this.gender = gender;
    }
    public Guide(){
    }
}

package com.guide_operator.Entities;

public class Operator extends User{
    private int id;
    private int userid;
    private String company_name_viet;
    private String company_name_eng;

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

    public String getCompany_name_viet() {
        return company_name_viet;
    }

    public void setCompany_name_viet(String company_name_viet) {
        this.company_name_viet = company_name_viet;
    }

    public String getCompany_name_eng() {
        return company_name_eng;
    }

    public void setCompany_name_eng(String company_name_eng) {
        this.company_name_eng = company_name_eng;
    }

    public Operator(int id, int userid, String username, String password, String fullname, String adress, String phonenumber, String email, int is_guide, String company_name_viet, String company_name_eng) {
        super(username, password, fullname, adress, phonenumber, email, is_guide);
        this.id= id;
        this.userid=userid;
        this.company_name_viet = company_name_viet;
        this.company_name_eng = company_name_eng;
    }


    public Operator(){}


}

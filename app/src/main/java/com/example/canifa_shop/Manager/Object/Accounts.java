package com.example.canifa_shop.Manager.Object;

public class Accounts {
    int ID;
    private String userName, password, fullname, birth, sex, phonenumber, email;

    public Accounts(int ID,String userName, String password, String fullname, String birth, String sex, String phonenumber, String email) {
        this.ID=ID;
        this.userName = userName;
        this.password = password;
        this.fullname = fullname;
        this.birth = birth;
        this.sex = sex;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public Accounts() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}

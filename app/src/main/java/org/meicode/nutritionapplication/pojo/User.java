package org.meicode.nutritionapplication.pojo;

public class User {
    private String username;
    private String password;
    private String insulin;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //this.insulin = insulin;


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

    public String getInsulin() {
        return insulin;
    }

    public void setInsulin(String insulin) {
        this.insulin = insulin;
    }




}

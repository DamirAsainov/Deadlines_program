package com.example.endterm;

public class User {
    protected int id;
    protected String login;
    protected String password;
    protected String group;
    protected String name;
    public User(){}
    public User(String login, String password){
        this.login = login;
        this.password = password;
    }
    public  User(String login, String password, String name, String group, int id){
        this.login = login;
        this.password = password;
        this.name = name;
        this.group = group;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

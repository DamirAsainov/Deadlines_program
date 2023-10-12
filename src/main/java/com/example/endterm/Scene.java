package com.example.endterm;

public abstract class Scene {
    protected User user;
    protected void setUserScene(User user){
        UserDB db = new UserDB();
        this.user = db.getUser(user);
    }
}

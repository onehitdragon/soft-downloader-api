package com.example.softdownloaderapi.model;

public class CheckingUserResult {
    private User user;
    private boolean result;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    
    public CheckingUserResult() {
    }
    
    public CheckingUserResult(boolean result) {
        this.result = result;
    }

    public CheckingUserResult(User user, boolean result) {
        this.user = user;
        this.result = result;
    }
}

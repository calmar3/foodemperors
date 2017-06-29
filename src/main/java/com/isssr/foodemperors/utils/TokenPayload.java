package com.isssr.foodemperors.utils;

/**
 * Created by marco on 29/06/17.
 */
public class TokenPayload {

    private String username;
    private String role;

    public TokenPayload(){
    }
    public TokenPayload(String username,String role){
        this.username = username;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "TokenPayload{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

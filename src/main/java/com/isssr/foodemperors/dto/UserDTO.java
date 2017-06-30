package com.isssr.foodemperors.dto;

import com.isssr.foodemperors.model.User;

/**
 * Created by Caim03 on 29/06/17.
 */
public class UserDTO {

    private User user;
    private String token;

    public UserDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

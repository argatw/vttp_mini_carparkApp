package com.example.miniProject.Model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class User {
    String id;
    String email;
    String password;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static User create(SqlRowSet rowSet) {
        User user = new User();
        user.setId(rowSet.getString("userId"));
        user.setEmail(rowSet.getString("email"));
        user.setPassword(rowSet.getString("password"));
        return user;
    }
    

}

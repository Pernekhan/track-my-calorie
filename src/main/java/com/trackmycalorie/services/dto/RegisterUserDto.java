package com.trackmycalorie.services.dto;

public class RegisterUserDto {

    String username;
    String email;
    String password;
    String fullname;
    Integer caloriesDaily;

    public RegisterUserDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getCaloriesDaily() {
        return caloriesDaily;
    }

    public void setCaloriesDaily(Integer caloriesDaily) {
        this.caloriesDaily = caloriesDaily;
    }
}

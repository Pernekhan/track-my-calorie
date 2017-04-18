package com.trackmycalorie.services.dto;

public class RegisterUserDto {

    String username;
    String email;
    String password;
    String fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCaloriesDaily() {
        return caloriesDaily;
    }

    public void setCaloriesDaily(Integer caloriesDaily) {
        this.caloriesDaily = caloriesDaily;
    }
}

package com.trackmycalorie.services.dto;

public class UserDto {

    String fullName;

    Integer caloriesDaily;

    public UserDto() {
    }

    public Integer getCaloriesDaily() {
        return caloriesDaily;
    }

    public void setCaloriesDaily(Integer caloriesDaily) {
        this.caloriesDaily = caloriesDaily;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

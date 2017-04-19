package com.trackmycalorie.services.dto;

import java.util.Date;

public class DateCaloriesDto {
    private Date date;
    private Long calories;

    public DateCaloriesDto() {

    }

    public DateCaloriesDto(Date date, Long calories) {
        this.date = date;
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCalories() {
        return calories;
    }

    public void setCalories(Long calories) {
        this.calories = calories;
    }
}

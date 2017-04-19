package com.trackmycalorie.dao.api;

import com.trackmycalorie.dao.api.crud.Dao;
import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.services.dto.DateCaloriesDto;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface MealDao extends Dao<Meal, Long> {
    List<Meal> getAll(Long userId);
    List<Meal> filterByDateAndTime(Long userId, Date fromDate, Date toDate);
    List<DateCaloriesDto> getSumByDateAndTime(Long userId, Date fromDate, Date toDate) throws ParseException;
}

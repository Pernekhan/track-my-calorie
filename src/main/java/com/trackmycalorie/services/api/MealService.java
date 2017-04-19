package com.trackmycalorie.services.api;

import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.dto.DateCaloriesDto;
import com.trackmycalorie.services.dto.MealDto;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface MealService {
    List<Meal> getAll(Long userId);

    Meal findMeal(Long mealId);

    void createMeal(MealDto mealDto, User user);

    void deleteMeal(Meal meal);

    void updateMeal(Long mealId, MealDto mealDto);

    List<Meal> filterByDateAndTime(Long userId, Date fromDate, Date toDate);

    List<DateCaloriesDto> getSumByDateAndTime(Long id, Date fromDate, Date toDate) throws ParseException;
}

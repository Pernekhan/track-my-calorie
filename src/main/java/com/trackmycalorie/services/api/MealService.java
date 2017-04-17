package com.trackmycalorie.services.api;

import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.dto.MealDto;

import java.util.List;

public interface MealService {
    List<Meal> getAll(Long userId);

    Meal findMeal(Long mealId);

    void createMeal(MealDto mealDto, User user);

    void deleteMeal(Meal meal);

    void updateMeal(Long mealId, MealDto mealDto);
}

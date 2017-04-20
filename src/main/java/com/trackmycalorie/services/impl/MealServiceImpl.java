package com.trackmycalorie.services.impl;

import com.trackmycalorie.dao.api.MealDao;
import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.dao.impl.MealDaoImpl;
import com.trackmycalorie.services.api.MealService;
import com.trackmycalorie.services.dto.DateCaloriesDto;
import com.trackmycalorie.services.dto.MealDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MealServiceImpl implements MealService {

    @Autowired
    MealDao mealDao;

    @Override
    public List<Meal> getAll(Long userId) {
        return mealDao.getAll(userId);
    }

    @Override
    public Meal findMeal(Long mealId) {
        return mealDao.find(mealId);
    }

    @Override
    public void createMeal(MealDto mealDto, User user) {
        Meal meal = new Meal();
        meal.setDate(
                mealDto.getDate() != null ? mealDto.getDate() : new Date()
        );
        meal.setCalories(
                mealDto.getCalories() != null ? mealDto.getCalories() : 0
        );
        meal.setDescription(
                mealDto.getDescription() != null ? mealDto.getDescription() : "Unknown"
        );
        meal.setUser(user);
        mealDao.create(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealDao.remove(meal);
    }

    @Override
    public void updateMeal(Long mealId, MealDto mealDto) {
        Meal meal = mealDao.find(mealId);
        if (mealDto.getDate() != null)
            meal.setDate(mealDto.getDate());
        if (mealDto.getCalories() != null)
            meal.setCalories(mealDto.getCalories());
        if (mealDto.getDescription() != null)
            meal.setDescription(mealDto.getDescription());
        mealDao.save(meal);
    }

    @Override
    public List<Meal> filterByDateAndTime(Long userId, Date fromDate, Date toDate) {
        return mealDao.filterByDateAndTime(userId, fromDate, toDate);
    }

    @Override
    public List<DateCaloriesDto> getSumByDateAndTime(Long id, Date fromDate, Date toDate) throws ParseException {
        return mealDao.getSumByDateAndTime(id, fromDate, toDate);
    }
}

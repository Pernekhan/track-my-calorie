package com.trackmycalorie.dao.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackmycalorie.dao.config.DaoConfigTest;
import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.dao.entity.Role;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.dto.DateCaloriesDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DaoConfigTest.class)
@Transactional
@SpringBootTest
public class MealDaoTest {

    @Autowired
    MealDao mealDao;

    @Autowired
    UserDao userDao;

    @Test
    public void getMeal() {
        User user = new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER);
        userDao.create(user);
        Meal m = new Meal("banana", 15, new Date(), user);
        mealDao.create(m);
        assertEquals(m.getId(), mealDao.find(m.getId()).getId());
    }

    @Test
    public void getAll() {
        User user = new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER);
        userDao.create(user);
        Meal customMeals[] = {
                new Meal("banana", 15, new Date(), user),
                new Meal("apple", 20, new Date(), user),
                new Meal("orange", 30, new Date(), user)
        };
        for (Meal m : customMeals) mealDao.create(m);
        List<Meal> meals = mealDao.getAll(user.getId());
        assertEquals(customMeals.length, meals.size());
    }

    @Test
    public void getAllNonPersistedUser() {
        User persistedUser = new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER);
        userDao.create(persistedUser);

        User user = new User();
        user.setId(persistedUser.getId());
        Meal customMeals[] = {
                new Meal("banana", 15, new Date(), user),
                new Meal("apple", 20, new Date(), user),
                new Meal("orange", 30, new Date(), user)
        };
        for (Meal m : customMeals) mealDao.create(m);
        List<Meal> meals = mealDao.getAll(user.getId());
        assertEquals(customMeals.length, meals.size());
    }

    @Test
    public void filterByDateAndTime() throws JsonProcessingException {
        User user = new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER);
        userDao.create(user);
        Calendar calendar = Calendar.getInstance();

        calendar.set(2017, 2, 30, 15, 0);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 1, 15, 0);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 10, 15, 05);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 10, 16, 40);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 11, 15, 30);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 1, 15, 0);
        Date fromDate = calendar.getTime();

        calendar.set(2017, 3, 10, 16, 30);
        Date toDate = calendar.getTime();

        List<Meal> meals = mealDao.filterByDateAndTime(user.getId(), fromDate, toDate);
        assertEquals(2, meals.size());

    }

    @Test
    public void getSumByDateAndTime() throws JsonProcessingException, ParseException {
        User user = new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER);
        userDao.create(user);
        Calendar calendar = Calendar.getInstance();

        calendar.set(2017, 2, 30, 15, 0);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 1, 15, 0);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));
        calendar.set(2017, 3, 1, 15, 20);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));
        calendar.set(2017, 3, 1, 15, 40);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 9, 15, 05);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 10, 16, 40);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));
        calendar.set(2017, 3, 10, 15, 40);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));
        calendar.set(2017, 3, 10, 16, 50);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 11, 15, 30);
        mealDao.create(new Meal("banana", 500, calendar.getTime(), user));

        calendar.set(2017, 3, 1, 15, 0);
        Date fromDate = calendar.getTime();

        calendar.set(2017, 3, 10, 16, 30);
        Date toDate = calendar.getTime();

        List<DateCaloriesDto> dateCaloriesDtos = mealDao.getSumByDateAndTime(user.getId(), fromDate, toDate);
        assertEquals(3, dateCaloriesDtos.size());

        DateCaloriesDto dateCaloriesDto = dateCaloriesDtos.get(0);
        assertEquals(Long.valueOf(1500), dateCaloriesDto.getCalories());
    }

}

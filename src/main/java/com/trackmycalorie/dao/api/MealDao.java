package com.trackmycalorie.dao.api;

import com.trackmycalorie.dao.api.crud.Dao;
import com.trackmycalorie.dao.entity.Meal;

import java.util.List;

public interface MealDao extends Dao<Meal, Long> {
    List<Meal> getAll(Long userId);
}

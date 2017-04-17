package com.trackmycalorie.dao.impl;

import com.trackmycalorie.dao.api.MealDao;
import com.trackmycalorie.dao.entity.Meal;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MealDaoImpl extends BaseDaoImpl<Meal, Long> implements MealDao {

    public MealDaoImpl() {
        super(Meal.class);
    }

    @Override
    public List<Meal> getAll(Long userId) {
        Query query = getSession().getNamedQuery("User.getAllByUserId");
        query.setParameter("userId", userId);
        return query.list();
    }
}

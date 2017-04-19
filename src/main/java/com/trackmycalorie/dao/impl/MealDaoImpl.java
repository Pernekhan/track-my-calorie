package com.trackmycalorie.dao.impl;

import com.trackmycalorie.dao.api.MealDao;
import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.services.dto.DateCaloriesDto;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MealDaoImpl extends BaseDaoImpl<Meal, Long> implements MealDao {

    public MealDaoImpl() {
        super(Meal.class);
    }

    @Override
    public List<Meal> getAll(Long userId) {
        Query query = getSession().getNamedQuery("Meal.getAllByUserId");
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public List<Meal> filterByDateAndTime(Long userId, Date fromDate, Date toDate) {
        Query query = getSession().getNamedQuery("Meal.filterByDateAndTime");
        query.setParameter("userId", userId);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("fromTime", String.format("%02d:%02d", fromDate.getHours(), fromDate.getMinutes()));
        query.setParameter("toTime", String.format("%02d:%02d", toDate.getHours(), toDate.getMinutes()));
        return query.list();
    }

    @Override
    public List<DateCaloriesDto> getSumByDateAndTime(Long userId, Date fromDate, Date toDate) throws ParseException {
        Query query = getSession().getNamedQuery("Meal.getSumByDateAndTime");
        query.setParameter("userId", userId);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("fromTime", String.format("%02d:%02d", fromDate.getHours(), fromDate.getMinutes()));
        query.setParameter("toTime", String.format("%02d:%02d", toDate.getHours(), toDate.getMinutes()));
        List<DateCaloriesDto> result = new ArrayList<>();
        List<Object> iter = query.list();
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        for (Object obj: iter){
            Object entry [] = (Object[]) obj;
            Date date = format.parse((String)entry[0]);
            Long calories = (Long) entry[1];
            result.add(new DateCaloriesDto(date, calories));
        }
        return result;
    }


}

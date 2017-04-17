package com.trackmycalorie.dao.api;

import com.trackmycalorie.dao.api.crud.Dao;
import com.trackmycalorie.dao.entity.User;

import java.util.List;

public interface UserDao extends Dao<User, Long> {
    List<User> getAll();

    User findByUsername(String username);
}

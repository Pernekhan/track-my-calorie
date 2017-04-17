package com.trackmycalorie.dao.impl;

import com.trackmycalorie.dao.api.UserDao;
import com.trackmycalorie.dao.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> getAll() {
        Query query = getSession().getNamedQuery("User.getAll");
        return query.list();
    }

    @Override
    public User findByUsername(String username) {
        Query query = getSession().getNamedQuery("User.getByUsername");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }
}

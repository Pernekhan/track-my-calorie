package com.trackmycalorie.dao.api;

import com.trackmycalorie.dao.config.DaoConfigTest;
import com.trackmycalorie.dao.entity.Role;
import com.trackmycalorie.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DaoConfigTest.class)
@Transactional
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void getAll() {
        userDao.create(new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER));
        userDao.create(new User("Ipsum", "ipsum@gmail.com", "ipsumipsum", Role.ROLE_USER));
        assertEquals(userDao.getAll().size(), 2);
    }

    @Test
    public void findByUsername() {
        User user = new User("Lorem", "lorem@gmail.com", "loremlorem", Role.ROLE_USER);
        userDao.create(user);
        assertEquals(user.getId(), userDao.findByUsername("Lorem").getId());
    }

}

package com.trackmycalorie.services.impl;

import com.sun.tools.javac.util.List;
import com.trackmycalorie.dao.api.UserDao;
import com.trackmycalorie.dao.entity.Role;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.api.UserService;
import com.trackmycalorie.services.dto.RegisterUserDto;
import com.trackmycalorie.services.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void createUser(RegisterUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setFullName(userDto.getFullName());
        user.setCaloriesDaily(userDto.getCaloriesDaily());
        userDao.create(user);
    }

    @Override
    public User findUser(Long id) {
        return userDao.find(id);
    }

    public void updateUser(Long id, UserDto userDto) {
        User user = userDao.find(id);
        if (userDto.getFullName() != null) {
            user.setFullName(userDto.getFullName());
        }
        if (userDto.getCaloriesDaily() != null) {
            user.setCaloriesDaily(userDto.getCaloriesDaily());
        }
        userDao.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.find(id);
        userDao.remove(user);
    }

    @Override
    public void createManager(RegisterUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.ROLE_MANAGER);
        user.setFullName(userDto.getFullName());
        userDao.create(user);
    }
}

package com.trackmycalorie.services.api;

import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.dto.RegisterUserDto;
import com.trackmycalorie.services.dto.UserDto;

public interface UserService {
    java.util.List<User> getAllUsers();

    void createUser(RegisterUserDto userDto);

    User findUser(Long id);

    void updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    void createManager(RegisterUserDto userDto);

    void createAdmin(User user);
}

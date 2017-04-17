package com.trackmycalorie.web.controllers;

import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.api.UserService;
import com.trackmycalorie.services.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ManagerController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "users/", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('MANAGER')")
    public List<User> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('MANAGER')")
    public User getUser(@PathVariable("userId") Long userId) throws Exception {
        return userService.findUser(userId);
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.PATCH)
    @ResponseBody
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity updateUser(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) throws Exception {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "users/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity deleteUser(@PathVariable("userId") Long userId) throws Exception {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}

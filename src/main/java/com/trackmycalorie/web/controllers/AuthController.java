package com.trackmycalorie.web.controllers;

import com.trackmycalorie.services.api.UserService;
import com.trackmycalorie.services.dto.RegisterUserDto;
import com.trackmycalorie.web.security.jwt.AccountCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity registerUser(@RequestBody RegisterUserDto userDto) throws Exception {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody AccountCredentials credentials) throws Exception {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "manager", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity registerManager(@RequestBody RegisterUserDto userDto) throws Exception {
        userService.createManager(userDto);
        return ResponseEntity.ok().build();
    }


}

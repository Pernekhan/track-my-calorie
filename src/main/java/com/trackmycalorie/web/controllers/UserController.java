package com.trackmycalorie.web.controllers;

import com.trackmycalorie.dao.entity.Role;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.dao.impl.UserDaoImpl;
import com.trackmycalorie.services.api.UserService;
import com.trackmycalorie.services.dto.UserDto;
import com.trackmycalorie.web.security.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String testApi() throws Exception {
        return "test api works";
    }

    @RequestMapping(value = "users/self", method = RequestMethod.GET)
    @ResponseBody
    public User getTokenOwner(HttpServletRequest request) throws Exception {
        String token = request.getHeader(JwtTokenUtils.HEADER_STRING);
        User user = JwtTokenUtils.parseToken(token);
        return userService.findUser(user.getId());
    }

    @RequestMapping(value = "users/self", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateUser(HttpServletRequest request, @RequestBody UserDto userDto) throws Exception {
        String token = request.getHeader(JwtTokenUtils.HEADER_STRING);
        User user = JwtTokenUtils.parseToken(token);
        userService.updateUser(user.getId(), userDto);
        return ResponseEntity.ok().build();
    }

}

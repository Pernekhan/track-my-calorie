package com.trackmycalorie.web.controllers;

import com.trackmycalorie.dao.entity.Meal;
import com.trackmycalorie.dao.entity.User;
import com.trackmycalorie.services.api.MealService;
import com.trackmycalorie.services.api.UserService;
import com.trackmycalorie.services.dto.DateCaloriesDto;
import com.trackmycalorie.services.dto.MealDto;
import com.trackmycalorie.web.security.jwt.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MealController {

    @Autowired
    UserService userService;

    @Autowired
    MealService mealService;

    @RequestMapping(value = "users/self/meals", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public List<Meal> getAllMeals(HttpServletRequest request) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        return mealService.getAll(user.getId());
    }

    @RequestMapping(value = "filter/users/self/meals", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public List<Meal> filterByDateAndTime(
            HttpServletRequest request, @RequestParam("fromDate") Long fromDate,
            @RequestParam("toDate") Long toDate) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        return mealService.filterByDateAndTime(user.getId(), new Date(fromDate), new Date(toDate));
    }

    @RequestMapping(value = "group/users/self/meals", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public List<DateCaloriesDto> getSumByDateAndTime(
            HttpServletRequest request, @RequestParam("fromDate") Long fromDate,
            @RequestParam("toDate") Long toDate) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        return mealService.getSumByDateAndTime(user.getId(), new Date(fromDate), new Date(toDate));
    }


    @RequestMapping(value = "users/self/meals/{mealId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public Meal getMeal(HttpServletRequest request, @PathVariable("mealId") Long mealId) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        Meal meal = mealService.findMeal(mealId);
        if (!user.getId().equals(meal.getUser().getId())) {
            throw new AccessDeniedException("User is not the owner of the resource");
        }
        return meal;
    }

    @RequestMapping(value = "users/self/meals", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity createMeal(HttpServletRequest request, @RequestBody MealDto mealDto) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        mealService.createMeal(mealDto, user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "users/self/meals/{mealId}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteMeal(HttpServletRequest request, @PathVariable("mealId") Long mealId) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        Meal meal = mealService.findMeal(mealId);
        if (!user.getId().equals(meal.getUser().getId())) {
            throw new AccessDeniedException("User is not the owner of the resource");
        }
        mealService.deleteMeal(meal);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "users/self/meals/{mealId}", method = RequestMethod.PATCH)
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateMeal(
            HttpServletRequest request, @PathVariable("mealId") Long mealId,
            @RequestBody MealDto mealDto) throws Exception {
        User user = JwtTokenUtils.parseToken(request);
        Meal meal = mealService.findMeal(mealId);
        if (!user.getId().equals(meal.getUser().getId())) {
            throw new AccessDeniedException("User is not the owner of the resource");
        }
        mealService.updateMeal(mealId, mealDto);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "users/{userId}/meals", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public List<Meal> getAllMeals(@PathVariable("userId") Long userId) throws Exception {
        return mealService.getAll(userId);
    }

    @RequestMapping(value = "meals/{mealId}", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public Meal getMeal(@PathVariable("mealId") Long mealId) throws Exception {
        return mealService.findMeal(mealId);
    }

    @RequestMapping(value = "users/{userId}/meals", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createMeal(@PathVariable("userId") Long userId, @RequestBody MealDto mealDto) throws Exception {
        User user = new User();
        user.setId(userId);
        mealService.createMeal(mealDto, user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "meals/{mealId}", method = RequestMethod.DELETE)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteMeal(@PathVariable("mealId") Long mealId) throws Exception {
        Meal meal = mealService.findMeal(mealId);
        mealService.deleteMeal(meal);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "meals/{mealId}", method = RequestMethod.PATCH)
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteMeal(@PathVariable("mealId") Long mealId, @RequestBody MealDto mealDto) throws Exception {
        mealService.updateMeal(mealId, mealDto);
        return ResponseEntity.ok().build();
    }
}

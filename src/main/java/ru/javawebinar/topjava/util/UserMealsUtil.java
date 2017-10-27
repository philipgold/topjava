package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    // Home work HW0 https://github.com/JavaOPs/topjava#-Домашнее-задание-hw0
    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //Get total count of calories for the day
        int totalCalories = 0;
        for (UserMeal meal : mealList){
            boolean isExceeded = TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime);
            if (isExceeded)
                totalCalories =+ meal.getCalories();
        }

        //Create an exceeded list
//        List<UserMealWithExceed> list = new ArrayList<>();
//        for (UserMeal meal : mealList){
//            boolean isExceeded = TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime);
//            if (isExceeded)
//                list.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), totalCalories > caloriesPerDay));
//
//        }

        int finalTotalCalories = totalCalories;
        List<UserMealWithExceed> list = mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), finalTotalCalories > caloriesPerDay))
                .collect(Collectors.toList());

        return list;
    }
}

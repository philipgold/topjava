package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface MealDAO {
    int insertMeal(LocalDateTime dateTime, String description, int calories);
    boolean deleteMeal(int mealId);
    boolean updateMeal(int mealId, LocalDateTime dateTime, String description, int calories);
    Collection getMealsWithExceeded();
    Meal getMealId(int mealId);
}
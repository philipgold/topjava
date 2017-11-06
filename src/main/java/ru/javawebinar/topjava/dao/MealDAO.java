package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealDAO {
    int insert(Meal meal);
    void delete(int mealId);
    void update(Meal meal);
    Collection getList();
    Meal getById(int mealId);
}
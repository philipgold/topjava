package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealDAO implements MealDAO {
    private ConcurrentHashMap<Integer, Meal> meals;

    public InMemoryMealDAO() {
        meals = new ConcurrentHashMap(initializeMeals());
    }

    @Override
    public boolean deleteMeal(int mealId) {
       meals.remove(mealId);
       return true;
    }

    @Override
    public int insertMeal(LocalDateTime dateTime, String description, int calories) {
        int id = getNextId();
        meals.put(id, new Meal(id, dateTime, description, calories));
        return id;
    }

    @Override
    public boolean updateMeal(int mealId, LocalDateTime dateTime, String description, int calories) {
        if (meals.containsKey(mealId)){
            meals.put(mealId, new Meal(mealId, dateTime, description, calories));
            return true;
        }else
            return false;
    }

    @Override
    public Meal getMealId(int mealId) {
        return meals.get(mealId);
    }

    @Override
    public Collection getMealsWithExceeded() {
        List<Meal> list = new ArrayList<>(meals.values());
        return MealsUtil.getFilteredWithExceeded(list, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

private ConcurrentHashMap<Integer,Meal> initializeMeals(){
    ConcurrentHashMap<Integer, Meal> map = new ConcurrentHashMap<>();
    int id;
    map.put(id = getNextId(), new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
    map.put(id = getNextId(), new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
    map.put(id = getNextId(), new Meal(id, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
    map.put(id = getNextId(), new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
    map.put(id = getNextId(), new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
    map.put(id = getNextId(), new Meal(id, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    return  map;
}

    //Maintaining an ID sequence is a separate responsibility from the rest of what Meal object does, and doesn't belong to any one instance of the Meal class, so it belongs in a different object.
    private static AtomicInteger currentId = new AtomicInteger(1);
    public static int getNextId() {
        return currentId.getAndIncrement();
    }
}

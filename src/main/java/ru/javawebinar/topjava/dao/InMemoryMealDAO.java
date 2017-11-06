package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
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
    public void delete(int mealId) {
       meals.remove(mealId);
    }

    @Override
    public int insert(Meal meal) {
        int id = getNextId();
        meals.put(id, new Meal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        return id;
    }

    @Override
    public void update(Meal meal) {
        if (meals.containsKey(meal.getId())){
            meals.put(meal.getId(), new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
        }
    }

    @Override
    public Meal getById(int mealId) {
        return meals.get(mealId);
    }

    @Override
    public Collection getList() {
        return new ArrayList<>(meals.values());
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

package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {
    private final int mealid;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(int mealid, LocalDateTime dateTime, String description, int calories) {
        this.mealid = mealid;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getMealid() {
        return mealid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}

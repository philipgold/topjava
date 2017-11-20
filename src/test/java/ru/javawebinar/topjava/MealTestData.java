package ru.javawebinar.topjava;

import org.assertj.core.api.SoftAssertions;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int ADMIN_MEAL_LUNCH_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL_DINNER_ID = START_SEQ + 9;

    public static final Meal ADMIN_MEAL_LUNCH = new Meal(ADMIN_MEAL_LUNCH_ID, DateTimeUtil.parseLocalDateTime("2015-06-01 14:00:00"), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL_DINNER = new Meal(ADMIN_MEAL_DINNER_ID, DateTimeUtil.parseLocalDateTime("2015-06-01 21:00:00"), "Админ ужин", 1500);

    public static void assertMatch(Meal actual, Meal expected) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actual.getId()).isEqualTo(expected.getId());
        softly.assertThat(actual.getCalories()).isEqualTo(expected.getCalories());
        softly.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
        softly.assertThat(actual.getDateTime()).isEqualTo(expected.getDateTime());
        softly.assertAll();
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).containsOnly(expected);
    }
}

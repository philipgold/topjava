package ru.javawebinar.topjava.web.json;

import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import ru.javawebinar.topjava.View;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static ru.javawebinar.topjava.MealTestData.*;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ADMIN_MEAL1);
        System.out.println(json);
        Meal meal = JsonUtil.readValue(json, Meal.class);
        assertMatch(meal, ADMIN_MEAL1);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(MEALS);
        System.out.println(json);
        List<Meal> meals = JsonUtil.readValues(json, Meal.class);
        assertMatch(meals, MEALS);
    }

    @Test
    public void testWriteWithView() throws Exception {
        ObjectWriter uiWriter = JacksonObjectMapper.getMapper().writerWithView(View.JsonUI.class);
        String json = JsonUtil.writeValue(ADMIN_MEAL1, uiWriter);
        System.out.println(json);
        assertThat(json, containsString("dateTimeUI"));
    }
}
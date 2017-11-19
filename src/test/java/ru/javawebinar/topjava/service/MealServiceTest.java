package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, DateTimeUtil.parseLocalDateTime("2015-06-01 10:00:00"), "Админ завтрак", 500);
        Meal created = service.create(newMeal, UserTestData.ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(UserTestData.ADMIN_ID), newMeal, ADMIN_MEAL_LUNCH, ADMIN_MEAL_DINNER);
    }

    @Test(expected = DuplicateKeyException.class)
    public void duplicateDateMealCreate() throws Exception {
        service.create(new Meal(null, DateTimeUtil.parseLocalDateTime("2015-06-01 10:00:00"), "Админ завтрак", 500), UserTestData.ADMIN_ID);
        service.create(new Meal(null, DateTimeUtil.parseLocalDateTime("2015-06-01 10:00:00"), "Админ завтрак", 500), UserTestData.ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(ADMIN_MEAL_DINNER_ID, UserTestData.ADMIN_ID);
        assertMatch(service.getAll(UserTestData.ADMIN_ID),ADMIN_MEAL_LUNCH);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(ADMIN_MEAL_DINNER_ID, UserTestData.USER_ID);
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(ADMIN_MEAL_LUNCH_ID, UserTestData.ADMIN_ID);
        assertMatch(meal, ADMIN_MEAL_LUNCH);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGet() throws Exception{
        service.get(ADMIN_MEAL_LUNCH_ID, UserTestData.USER_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> meals  = service.getBetweenDates(DateTimeUtil.parseLocalDate("2015-06-01"), DateTimeUtil.parseLocalDate("2015-06-01"), UserTestData.ADMIN_ID);
        assertMatch(meals, ADMIN_MEAL_LUNCH, ADMIN_MEAL_DINNER);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> meals = service.getBetweenDateTimes(DateTimeUtil.parseLocalDateTime("2015-06-01 20:00:00"), DateTimeUtil.parseLocalDateTime("2015-06-01 22:00:00"), UserTestData.ADMIN_ID);
        assertMatch(meals, ADMIN_MEAL_DINNER);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(ADMIN_MEAL_DINNER);
        updated.setDateTime(DateTimeUtil.parseLocalDateTime("2015-06-01 20:00:00"));
        updated.setDescription("Админ ужин 2");
        updated.setCalories(500);
        service.update(updated, UserTestData.ADMIN_ID);
        assertMatch(service.get(ADMIN_MEAL_DINNER_ID, UserTestData.ADMIN_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() throws Exception {
        service.update(ADMIN_MEAL_DINNER, UserTestData.USER_ID);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(UserTestData.ADMIN_ID);
        assertMatch(all, ADMIN_MEAL_LUNCH, ADMIN_MEAL_DINNER);
    }
}
package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles({Profiles.POSTGRES_DB, Profiles.JPA})
public class ServiceJpaTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void getUserWithMeals() throws Exception {
//        User user = userService.get(USER_ID);
//        assertMatch(user.getMeals(), MealTestData.MEALS);
//    }

//    @Test
//    public void getUserWithEmptyMeals() throws Exception {
//        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, Collections.singleton(Role.ROLE_USER));
//        User created = userService.create(newUser);
//        newUser.setId(created.getId());
//        User expectedUser = userService.get(newUser.getId());
//        assertThat(expectedUser.getMeals()).isEmpty();
//    }
}

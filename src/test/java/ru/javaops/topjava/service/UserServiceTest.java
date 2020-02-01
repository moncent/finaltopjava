package ru.javaops.topjava.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.javaops.topjava.model.User;
import ru.javaops.topjava.util.UserUtil;
import ru.javaops.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.javaops.topjava.UserTestData.*;

@SpringJUnitConfig(locations = {
        "/spring/spring-app.xml",
        "/spring/spring-db.xml"
})
@Sql(scripts = "/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void create() {
        User newUser = getNew();
        User created = userService.create(new User(newUser));
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHERS.assertMatch(created, newUser);
        USER_MATCHERS.assertMatch(userService.get(newId), newUser);
    }

    @Test
    void delete() {
        userService.delete(USER2_ID);
        assertThrows(NotFoundException.class, () -> userService.delete(USER2_ID));
    }

    @Test
    void get() {
        User user = userService.get(USER1_ID);
        USER_MATCHERS.assertMatch(user, USER1);
    }

    @Test
    void getByEmail() {
        User user = userService.getByEmail(USER1.getEmail());
        USER_MATCHERS.assertMatch(user, USER1);
    }

    @Test
    void getAll() {
        List<User> all = userService.getAll();
        USER_MATCHERS.assertMatch(all, ADMIN, USER1, USER2);
    }

    @Test
    void update() {
        User updated = getUpdated();
        userService.update(UserUtil.asToWithRoles(new User(updated)));
        USER_MATCHERS.assertMatch(userService.get(USER1_ID), updated);
    }

    @Test
    void enable() {
        userService.enable(USER1_ID, false);
        assertFalse(userService.get(USER1_ID).isEnabled());
        userService.enable(USER1_ID, true);
        assertTrue(userService.get(USER1_ID).isEnabled());
    }
}
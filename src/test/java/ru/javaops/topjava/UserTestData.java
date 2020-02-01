package ru.javaops.topjava;

import ru.javaops.topjava.model.Role;
import ru.javaops.topjava.model.User;

import java.util.Collections;
import java.util.Date;

import static ru.javaops.topjava.model.BaseEntity.START_SEQ;

public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = ADMIN_ID + 1;
    public static final int USER2_ID = ADMIN_ID + 2;


    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@site.ru", "admin", Role.ROLE_ADMIN, Role.ROLE_REGULAR_USER);
    public static final User USER1 = new User(USER1_ID, "user1", "user1@google.com", "password1", Role.ROLE_REGULAR_USER);
    public static final User USER2 = new User(USER2_ID, "user2", "user2@google.com", "password2", Role.ROLE_REGULAR_USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", true, new Date(),Collections.singleton(Role.ROLE_REGULAR_USER));
    }

    public static User getUpdated() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setEmail("updated@mail.com");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        return updated;
    }

    public static TestMatchers<User> USER_MATCHERS = TestMatchers.useFieldsComparator(User.class, "registered", "votes", "password");
}
package org.tolinety.springrest.testdata;

import org.tolinety.springrest.ModelMatcher;
import org.tolinety.springrest.model.Role;
import org.tolinety.springrest.model.User;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;

/**
 * Created by tolin on 20.06.2017.
 */
public class UserTestData {
    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class);

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final User ADMIN = new User(ADMIN_ID, "admin@gmail.com", "admin", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER = new User(USER_ID, "user@yandex.ru", "user", Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(ADMIN, USER);

    public static final User CREATED = new User("new@mail.ru", "newPassword");
    public static final User UPDATED = new User(ADMIN_ID + 1, "updatedUser@yandex.ru", "updatedUser", Role.ROLE_USER);
}

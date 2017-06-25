package org.tolinety.springrest.testdata;

import lombok.extern.slf4j.Slf4j;
import org.tolinety.springrest.ModelMatcher;
import org.tolinety.springrest.model.Role;
import org.tolinety.springrest.model.User;
import org.tolinety.springrest.to.UserTo;
import org.tolinety.springrest.util.PasswordUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;

/**
 * Created by tolin on 20.06.2017.
 */
@Slf4j
public class UserTestData {
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;

    public static final User ADMIN = new User(ADMIN_ID, "admin@gmail.com", "admin", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User USER = new User(USER_ID, "user@yandex.ru", "user", Role.ROLE_USER);

    public static final List<User> USERS = Arrays.asList(ADMIN, USER);

    public static final UserTo CREATED = new UserTo("new@mail.ru", "newPassword");
    public static final UserTo UPDATED = new UserTo("updatedUser@yandex.ru", "updatedUser");

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (comparePassword(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isDeleted(), actual.isDeleted())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            log.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }
}

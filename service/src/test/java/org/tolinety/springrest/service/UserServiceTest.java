package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tolinety.springrest.model.User;
import org.tolinety.springrest.util.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.testdata.UserTestData.*;

/**
 * Created by tolin on 20.06.2017.
 */
@Slf4j
public class UserServiceTest extends AbstractServiceTest {
    @Autowired
    UserService service;

    @Test
    public void testCreate() throws Exception {
        User user = service.create(CREATED);
        log.info("From DB: " + user);

        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, user, USER), service.getAll());
    }

    @Test
    public void testCreateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(null);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = service.update(UPDATED, USER_ID);
        log.info("From DB: " + user);
        MATCHER.assertEquals(user, service.get(USER_ID));
    }

    @Test
    public void testUpdateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.update(null, USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER), service.getAll());
    }

    @Test
    public void testDeleteBadId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(ADMIN_ID + 5);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(ADMIN_ID);
        log.info("From DB: " + user);
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test
    public void testGetBadID() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(ADMIN_ID + 5);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail(USER.getEmail());
        log.info("From DB: " + user);
        MATCHER.assertEquals(USER, user);
    }

    @Test
    public void testGetByBadEmail() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getByEmail("bad@mail.ru");
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = service.getAll();
        log.info("From DB: " + users);
        MATCHER.assertCollectionEquals(USERS, users);
    }
}
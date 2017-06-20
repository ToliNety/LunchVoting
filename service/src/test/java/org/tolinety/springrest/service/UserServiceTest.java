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
        CREATED.setId(user.getId());
        MATCHER.assertEquals(CREATED, user);
    }

    @Test
    public void testCreateNotNew() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(UPDATED);
    }

    @Test
    public void testCreateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(null);
    }

    @Test
    public void testUpdate() throws Exception {
        User user = service.update(UPDATED);
        log.info("From DB: " + user);
        MATCHER.assertEquals(UPDATED, user);
    }

    @Test
    public void testUpdateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.update(null);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(USER), service.getAll());
    }

    @Test
    public void testDeleteBadId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(USER_ID + 5);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(USER_ID);
        log.info("From DB: " + user);
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test
    public void testGetBadID() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(USER_ID + 5);
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
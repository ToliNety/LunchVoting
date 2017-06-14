package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.util.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.RestaurantTestData.*;

/**
 * Created by tolin on 13.06.2017.
 */
@Slf4j
@Transactional
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void getAll() throws Exception {
        List<Restaurant> restaurants = service.getAll();
        log.info("From DB: " + restaurants.toString());
        MATCHER.assertCollectionEquals(RESTAURANTS, restaurants);
    }

    @Test
    public void getByID() throws Exception {
        Restaurant current = service.getByID(RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(current));
        MATCHER.assertEquals(RESTAURANT1, current);
    }

    @Test(expected = NotFoundException.class)
    public void getByIDNotFound() throws Exception {
        service.getByID(RESTAURANT1_ID + 5);
    }

    @Test
    public void create() throws Exception {
        Restaurant created = service.create(CREATED);
        log.info("From DB: " + String.valueOf(created));
        CREATED.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT1, RESTAURANT2, CREATED), service.getAll());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBadID() throws Exception {
        CREATED.setId(RESTAURANT1_ID + 5);
        service.create(CREATED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNull() throws Exception {
        service.create(null);
    }

    @Test
    public void update() throws Exception {
        Restaurant update = service.update(UPDATED);
        log.info("From DB: " + String.valueOf(update));
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT2, UPDATED), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(UPDATED_BAD_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNull() throws Exception {
        service.update(null);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT2), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(RESTAURANT1_ID + 5);
    }
}
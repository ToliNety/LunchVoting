package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.util.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.testdata.RestaurantTestData.*;

/**
 * Created by tolin on 13.06.2017.
 */
@Slf4j
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Test
    public void testGetAll() throws Exception {
        List<Restaurant> restaurants = service.getAll();
        log.info("From DB: " + restaurants.toString());
        MATCHER.assertCollectionEquals(RESTAURANTS, restaurants);
    }

    @Test
    public void testGet() throws Exception {
        Restaurant current = service.get(RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(current));
        MATCHER.assertEquals(RESTAURANT1, current);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(RESTAURANT1_ID + 5);
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant created = service.create(CREATED);
        log.info("From DB: " + String.valueOf(created));
        CREATED.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT1, RESTAURANT2, CREATED), service.getAll());
    }

    @Test
    public void testCreateBadID() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(new Restaurant(RESTAURANT1_ID, "test"));
    }

    @Test
    public void testCreateWithNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(null);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant update = service.update(UPDATED);
        log.info("From DB: " + String.valueOf(update));
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT2, UPDATED), service.getAll());
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(UPDATED_BAD_ID);
    }

    @Test
    public void testUpdateWithNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.update(null);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT2), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(RESTAURANT1_ID + 5);
    }
}
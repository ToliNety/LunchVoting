package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.util.NotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.testdata.DishTestData.*;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1_ID;

/**
 * Created by tolin on 18.06.2017.
 */
@Slf4j
public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    DishService service;

    @Test
    public void testGetAllByRestaurant() throws Exception {
        List<Dish> dishes = service.getAllByRestaurant(RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(dishes));
        MATCHER.assertCollectionEquals(DISHES, dishes);
    }

    @Test
    public void testCreate() throws Exception {
        Dish newDish = service.create(CREATED, RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(newDish));
        MATCHER.assertCollectionEquals(Arrays.asList(DISH1, DISH2, newDish), service.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testCreateBadRestaurantId() throws Exception {
        thrown.expect(DataIntegrityViolationException.class);
        service.create(CREATED, RESTAURANT1_ID + 5);
    }

    @Test
    public void testCreateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(null, RESTAURANT1_ID);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updatedDish = service.update(UPDATED, DISH_ID, RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(updatedDish));

        MATCHER.assertEquals(DISH1_UPDATED, service.get(DISH_ID));
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2, updatedDish), service.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testUpdateWithBadRestaurant() throws Exception {
        thrown.expect(NotFoundException.class);
        service.update(UPDATED, DISH_ID, RESTAURANT1_ID + 5);
    }

    @Test
    public void testGet() throws Exception {
        Dish dish = service.get(DISH_ID);
        log.info("From DB: " + String.valueOf(dish));
        MATCHER.assertEquals(DISH1, dish);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DISH_ID + 5);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(DISH_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2), service.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DISH_ID + 5);
    }

}
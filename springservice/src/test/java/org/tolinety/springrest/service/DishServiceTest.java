package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tolinety.springrest.model.Dish;

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
        CREATED.setId(newDish.getId());
        MATCHER.assertEquals(CREATED, newDish);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updatedDish = service.update(UPDATED, RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(updatedDish));

        MATCHER.assertEquals(DISH1_UPDATED, service.get(DISH_ID));

        UPDATED.setId(updatedDish.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2, UPDATED), service.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testGet() throws Exception {
        Dish dish = service.get(DISH_ID);
        log.info("From DB: " + String.valueOf(dish));
        MATCHER.assertEquals(DISH1, dish);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(DISH_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH2), service.getAllByRestaurant(RESTAURANT1_ID));
    }

}
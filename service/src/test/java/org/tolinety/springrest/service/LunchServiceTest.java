package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.util.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.tolinety.springrest.testdata.LunchTestData.*;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1_ID;

/**
 * Created by tolin on 14.06.2017.
 */
@Slf4j
public class LunchServiceTest extends AbstractServiceTest {
    @Autowired
    LunchService service;

    @Test
    public void testGetByDate() throws Exception {
        List<LunchMenu> menus = service.getAllByDate(LocalDate.of(2017, 06, 13));
        logoutMenuWithRestaurantAndDishes(menus);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU1, MENU2), menus);

        menus = service.getAllByDate(LocalDate.of(2017, 06, 12));
        logoutMenuWithRestaurantAndDishes(menus);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU3), menus);
    }

    @Test
    public void testGetByRestaurant() throws Exception {
        List<LunchMenu> menus = service.getAllByRestaurant(RESTAURANT1_ID);
        logoutMenuWithRestaurantAndDishes(menus);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU1, MENU3), menus);
    }

    @Test
    public void testGetWithNullDate() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.getAllByDate(null);
    }

    @Test
    public void testGet() throws Exception {
        LunchMenu menu = service.get(MENU_ID);
        log.info("From DB: " + String.valueOf(menu));
        MATCHER.assertEquals(MENU1, menu);
    }

    @Test
    public void testGetByBadId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MENU_ID + 5);
    }

    @Test
    public void testCreate() throws Exception {
        LunchMenu created = service.create(CREATED, RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(created));

        MATCHER.assertCollectionEquals(Arrays.asList(created), service.getAllByDate(CREATED.getRegistered()));
    }

    @Test
    public void testCreateWithBadDishes() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(CREATED_BAD_DISHES, RESTAURANT1_ID);
    }

    @Test
    public void testCreateWithNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(null, RESTAURANT1_ID);
    }

    @Test
    public void testCreateByBadRestaurantId() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.create(CREATED, RESTAURANT1_ID + 5);
    }

    @Test
    public void testUpdate() throws Exception {
        thrown.expect(UnsupportedOperationException.class);
        service.update(MENU1, RESTAURANT1_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MENU_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU2), service.getAllByDate(LocalDate.of(2017, 06, 13)));
    }

    @Test
    public void testDeleteBadID() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MENU_ID + 5);
    }

    @Test
    public void testDeleteBefore() throws Exception {
        service.deleteBefore(LocalDate.of(2017, 06, 13));
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getAllByDate(LocalDate.of(2017, 06, 12)));
    }

    @Test
    public void testDeleteBeforeWithNullDate() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        service.deleteBefore(null);
    }

    void logoutMenuWithRestaurantAndDishes(List<LunchMenu> menus) {
        StringBuilder sb = new StringBuilder();
        sb.append("From DB:\n");
        menus.forEach(menu -> sb
                .append("Restaurant name: ")
                .append(menu.getRestaurant().getName())
                .append("\n")
                .append(menu)
                .append("\n")
                .append(menu.getDishes()));
        log.info(sb.toString());
    }
}
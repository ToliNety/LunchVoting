package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.util.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.tolinety.springrest.testdata.MenuTestData.*;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1_ID;

/**
 * Created by tolin on 14.06.2017.
 */
@Slf4j
public class MenuServiceTest extends AbstractServiceTest {
    @Autowired
    MenuService service;

    @Test
    public void getByDate() throws Exception {
        List<LunchMenu> menus = service.getWithDataByDate(LocalDate.of(2017, 06, 13));
        logoutMenuWithRestaurantAndDishes(menus);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU1, MENU2), menus);

        menus = service.getWithDataByDate(LocalDate.of(2017, 06, 12));
        logoutMenuWithRestaurantAndDishes(menus);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU3), menus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithNullDate() throws Exception {
        service.getWithDataByDate(null);
    }

    @Test
    public void get() throws Exception {
        LunchMenu menu = service.get(MENU_ID);
        log.info("From DB: " + String.valueOf(menu));
        MATCHER.assertEquals(MENU1, menu);
    }

    @Test(expected = NotFoundException.class)
    public void getByBadId() throws Exception {
        service.get(MENU_ID + 5);
    }

    @Test
    public void create() throws Exception {
        LunchMenu created = service.create(CREATED, RESTAURANT1_ID);
        log.info("From DB: " + String.valueOf(created));
        CREATED.setId(created.getId());
        MATCHER.assertEquals(CREATED, service.get(created.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNull() throws Exception {
        service.create(null, RESTAURANT1_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNotNewMenu() throws Exception {
        service.create(MENU1, RESTAURANT1_ID);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createByBadRestaurantId() throws Exception {
        service.create(CREATED, RESTAURANT1_ID + 5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void update() throws Exception {
        service.update(MENU1, RESTAURANT1_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MENU_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU2), service.getWithDataByDate(LocalDate.of(2017, 06, 13)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteBadID() throws Exception {
        service.delete(MENU_ID + 5);
    }

    @Test
    public void deleteBefore() throws Exception {
        service.deleteBefore(LocalDate.of(2017, 06, 13));
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getWithDataByDate(LocalDate.of(2017, 06, 12)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteBeforeWithNullDate() throws Exception {
        service.deleteBefore(null);
    }

    void logoutMenuWithRestaurantAndDishes(List<LunchMenu> menus) {
        StringBuilder sb = new StringBuilder();
        menus.forEach(menu -> sb.append("From DB:\n")
                .append("Restaurant name: ")
                .append(menu.getRestaurant().getName())
                .append("\n")
                .append(menu)
                .append("\n")
                .append(menu.getDishes()));
        log.info(sb.toString());
    }
}
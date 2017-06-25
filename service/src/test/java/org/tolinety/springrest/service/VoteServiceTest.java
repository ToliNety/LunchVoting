package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.tolinety.springrest.testdata.LunchTestData.MENU3;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1_ID;
import static org.tolinety.springrest.testdata.UserTestData.ADMIN_ID;

/**
 * Created by tolin on 20.06.2017.
 */
@Slf4j
public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    VoteService service;

    @Test
    public void testNew() throws Exception {
        service.vote(ADMIN_ID, MENU3.getId(), LocalDate.of(2017, 06, 14));
    }

    @Test
    public void testUpdate() throws Exception {
        Assume.assumeTrue(LocalTime.now().isBefore(LocalTime.of(11, 00)));
        service.vote(ADMIN_ID, MENU3.getId(), LocalDate.of(2017, 06, 13));
    }

    @Test
    public void testCountByLunch() throws Exception {
        int count = service.countByLunch(MENU3.getId());
        log.info("Votes by lunch: " + count);
        Assert.assertEquals(count, 2);
    }

    @Test
    public void testCountByRestaurant() throws Exception {
        int count = service.countByRestaurant(RESTAURANT1_ID);
        log.info("Votes by restaurant: " + count);
        Assert.assertEquals(count, 3);
    }
}
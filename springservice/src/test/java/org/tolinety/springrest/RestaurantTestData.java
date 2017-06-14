package org.tolinety.springrest;

import org.tolinety.springrest.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;

/**
 * Created by tolin on 13.06.2017.
 */
public class RestaurantTestData {
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);

    public static final int RESTAURANT1_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Restaurant2");
    public static final Restaurant CREATED = new Restaurant(null, "RestaurantCreated");
    public static final Restaurant UPDATED = new Restaurant(RESTAURANT1_ID, "RestaurantUpdated");
    public static final Restaurant UPDATED_BAD_ID = new Restaurant(RESTAURANT1_ID + 5, "RestaurantUpdated");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2);
}

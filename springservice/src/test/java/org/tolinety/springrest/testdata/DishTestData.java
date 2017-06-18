package org.tolinety.springrest.testdata;

import org.tolinety.springrest.ModelMatcher;
import org.tolinety.springrest.model.BaseEntity;
import org.tolinety.springrest.model.Dish;

import java.util.Arrays;
import java.util.List;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT2;

/**
 * Created by tolin on 18.06.2017.
 */
public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class);

    public static final int DISH_ID = START_SEQ+4;

    public static final Dish DISH1 = new Dish(DISH_ID, "Dish1R1", 100, RESTAURANT1);
    public static final Dish DISH1_UPDATED = new Dish(DISH_ID, "Dish1R1", 100, true, RESTAURANT1);
    public static final Dish DISH2 = new Dish(DISH_ID+1, "Dish2R1", 200, RESTAURANT1);
    public static final Dish DISH3 = new Dish(DISH_ID+2, "Dish1R2", 150, RESTAURANT2);
    public static final Dish DISH4 = new Dish(DISH_ID+3, "Dish2R2", 10, RESTAURANT2);

    public static final List<Dish> DISHES = Arrays.asList(DISH1, DISH2);

    public static final Dish CREATED = new Dish(null, "Created", 180, null);
    public static final Dish UPDATED = new Dish(DISH_ID, "Updated", 120, RESTAURANT1);
}

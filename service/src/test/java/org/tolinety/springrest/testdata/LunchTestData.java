package org.tolinety.springrest.testdata;

import org.tolinety.springrest.ModelMatcher;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.to.LunchTo;

import java.time.LocalDate;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;
import static org.tolinety.springrest.testdata.DishTestData.DISH_ID;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT2;

/**
 * Created by tolin on 14.06.2017.
 */
public class LunchTestData {
    public static final ModelMatcher<LunchMenu> MATCHER = ModelMatcher.of(LunchMenu.class);

    public static final int MENU_ID = START_SEQ + 8;

    public static final LunchMenu MENU1 = new LunchMenu(MENU_ID, RESTAURANT1, LocalDate.of(2017, 06, 13));
    public static final LunchMenu MENU2 = new LunchMenu(MENU_ID + 1, RESTAURANT2, LocalDate.of(2017, 06, 13));
    public static final LunchMenu MENU3 = new LunchMenu(MENU_ID + 2, RESTAURANT1, LocalDate.of(2017, 06, 12));

    public static final LunchTo CREATED = new LunchTo(LocalDate.of(2017, 06, 14), new int[]{DISH_ID, DISH_ID + 1});
    public static final LunchTo CREATED_BAD_DISHES = new LunchTo(LocalDate.of(2017, 06, 14), new int[]{DISH_ID, DISH_ID + 2});
}

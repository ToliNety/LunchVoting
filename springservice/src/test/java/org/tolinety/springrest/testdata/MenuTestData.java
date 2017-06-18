package org.tolinety.springrest.testdata;

import org.tolinety.springrest.ModelMatcher;
import org.tolinety.springrest.model.LunchMenu;

import java.time.LocalDate;
import java.util.Collections;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT1;
import static org.tolinety.springrest.testdata.RestaurantTestData.RESTAURANT2;

/**
 * Created by tolin on 14.06.2017.
 */
public class MenuTestData {
    public static final ModelMatcher<LunchMenu> MATCHER = ModelMatcher.of(LunchMenu.class);

    public static final int MENU_ID = START_SEQ + 8;

    public static final LunchMenu MENU1 = new LunchMenu(MENU_ID, LocalDate.of(2017, 06, 13), RESTAURANT1);
    public static final LunchMenu MENU2 = new LunchMenu(MENU_ID + 1, LocalDate.of(2017, 06, 13), RESTAURANT2);
    public static final LunchMenu MENU3 = new LunchMenu(MENU_ID + 2, LocalDate.of(2017, 06, 12), RESTAURANT1);

    public static final LunchMenu CREATED = new LunchMenu(RESTAURANT1,
            LocalDate.of(2017, 06, 14), Collections.emptyList());
}

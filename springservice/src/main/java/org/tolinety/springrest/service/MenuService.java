package org.tolinety.springrest.service;

import org.tolinety.springrest.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
public interface MenuService {
    List<LunchMenu> getWithDataByDate(LocalDate date);

    LunchMenu get (int id);

    LunchMenu create (LunchMenu menu, int restaurantId);

    default LunchMenu update (LunchMenu menu, int restaurantId){
        throw new UnsupportedOperationException();
    }

    void delete (int id);

    void deleteBefore (LocalDate date);
}

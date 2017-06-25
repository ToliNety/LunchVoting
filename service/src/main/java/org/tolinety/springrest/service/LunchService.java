package org.tolinety.springrest.service;

import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.to.LunchTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
public interface LunchService {
    List<LunchMenu> getAllByDate(LocalDate date);

    List<LunchMenu> getAllByRestaurant(int restaurantId);

    LunchMenu get (int id);

    LunchMenu create (LunchTo menuTo, int restaurantId);

    default LunchMenu update (LunchMenu menu, int restaurantId){
        throw new UnsupportedOperationException();
    }

    void delete (int id);

    void deleteBefore (LocalDate date);
}

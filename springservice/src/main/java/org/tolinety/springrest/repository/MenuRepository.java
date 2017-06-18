package org.tolinety.springrest.repository;

import org.springframework.stereotype.Repository;
import org.tolinety.springrest.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 18.06.2017.
 */

public interface MenuRepository {
    LunchMenu save(LunchMenu menu, int restaurantId);

    int delete(int id);

    int deleteBefore(LocalDate date);

    LunchMenu get(int id);

    List<LunchMenu> getByDate(LocalDate date);

    default LunchMenu update (LunchMenu menu, int restaurantId){
        throw new UnsupportedOperationException();
    }
}

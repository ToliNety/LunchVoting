package org.tolinety.springrest.service;

import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.to.DishTo;

import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
public interface DishService {
    List<Dish> getAllByRestaurant(int restaurantId);

    Dish create(DishTo dishTo, int restaurantId);

    Dish update(DishTo dishTo, int dishId, int restaurantId);

    Dish get(int id);

    void delete(int id);
}

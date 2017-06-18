package org.tolinety.springrest.service;

import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.model.Restaurant;

import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
public interface DishService {
    List<Dish> getAllByRestaurant(int restaurantId);

    Dish create (Dish dish, int restaurantId);

    Dish update (Dish dish, int restaurantId);
}

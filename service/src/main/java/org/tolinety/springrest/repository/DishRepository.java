package org.tolinety.springrest.repository;

import org.tolinety.springrest.model.Dish;

import java.util.List;

/**
 * Created by tolin on 18.06.2017.
 */
public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    Dish get (int id);

    List<Dish> getByRestaurant (int restaurantId);

    boolean delete (int id);
}

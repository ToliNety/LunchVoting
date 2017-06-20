package org.tolinety.springrest.service;

import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
public interface RestaurantService {
    List<Restaurant> getAll();

    Restaurant get(int id);

    Restaurant create(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    void delete(int id);
}

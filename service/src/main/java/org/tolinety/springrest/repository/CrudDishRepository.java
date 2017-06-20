package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.model.Restaurant;

import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> getAllByRestaurantIdAndDeletedFalse(int restaurantId);
}

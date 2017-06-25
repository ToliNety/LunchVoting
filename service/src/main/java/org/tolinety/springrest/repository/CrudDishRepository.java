package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Dish;

import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    List<Dish> getDistinctByRestaurantIdAndDeletedFalse(int restaurantId);

    Dish getByIdAndAndDeletedFalse(int id);
}

package org.tolinety.springrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Dish;

import java.util.List;

/**
 * Created by tolin on 18.06.2017.
 */
@Repository
public class DishRepositoryImpl implements DishRepository {
    @Autowired
    CrudDishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        Dish newDish = null;
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        if (dish.isNew()) {
            newDish = dishRepository.save(dish);
        } else {
            Dish oldDish = get(dish.getId());
            if (oldDish.getRestaurant().getId() == restaurantId) {
                newDish = dishRepository.save(Dish.of(dish));
                oldDish.setDeleted(true);
                dishRepository.save(oldDish);
            }
        }
        return newDish;

    }

    @Override
    public Dish get(int id) {
        return dishRepository.findOne(id);
    }

    @Override
    public List<Dish> getByRestaurant(int restaurantId) {
        return dishRepository.getDistinctByRestaurantIdAndDeletedFalse(restaurantId);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Dish dish = get(id);
        if (dish == null) return false;
        dish.setDeleted(true);
        dishRepository.save(dish);
        return true;
    }
}

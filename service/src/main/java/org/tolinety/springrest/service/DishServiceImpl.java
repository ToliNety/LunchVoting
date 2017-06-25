package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.repository.DishRepository;
import org.tolinety.springrest.to.DishTo;

import java.util.List;

import static org.tolinety.springrest.to.DishTo.getFromTo;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFound;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by tolin on 18.06.2017.
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepository repository;

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return repository.getByRestaurant(restaurantId);
    }

    @Override
    public Dish create(DishTo dishTo, int restaurantId) {
        Assert.notNull(dishTo);
        return checkNotFoundWithId(repository.save(getFromTo(dishTo), restaurantId), restaurantId);
    }

    @Override
    public Dish update(DishTo dishTo, int dishId, int restaurantId) {
        Assert.notNull(dishTo);
        return checkNotFound(repository.save(getFromTo(dishTo, dishId), restaurantId), "restaurant id = " + restaurantId);
    }

    @Override
    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFound(repository.delete(id), "id = " + String.valueOf(id));

    }
}

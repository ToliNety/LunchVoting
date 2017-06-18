package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.repository.RestaurantRepository;

import java.util.List;

import static org.tolinety.springrest.util.ValidationUtil.checkNew;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFound;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAllByOrderByName();
    }

    @Override
    public Restaurant getByID(int id) {
        return checkNotFoundWithId(restaurantRepository.findOne(id), id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant mustn't be null");
        checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant mustn't be null");
        checkNotFoundWithId(getByID(restaurant.getId()), restaurant.getId());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        int result = restaurantRepository.deleteById(id);
        checkNotFound((result != 0), String.valueOf(id));
    }
}

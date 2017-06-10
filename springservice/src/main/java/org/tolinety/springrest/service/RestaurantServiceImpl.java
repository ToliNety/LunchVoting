package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.model.Vote;
import org.tolinety.springrest.repository.CrudRestaurantRepository;
import org.tolinety.springrest.repository.VotesRepository;
import org.tolinety.springrest.util.ValidationUtil;

import java.util.List;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    private VotesRepository votesRepository;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getByID(int id) {
        return restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        ValidationUtil.checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant, "Restaurant must not be null");
        return ValidationUtil.checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Override
    public int delete(int id) {
        //TODO delete data in repo
        return 0;
    }

    @Override
    public List<Vote> getVotes() {
        return votesRepository.findAll();
    }
}

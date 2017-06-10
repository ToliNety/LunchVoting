package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.model.Vote;
import org.tolinety.springrest.repository.CrudRestaurantRepository;
import org.tolinety.springrest.repository.VotesRepository;

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
        //TODO Add data from repo
        return null;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        //TODO add data to repo
        return null;
    }

    @Override
    public int update(Restaurant restaurant, int id) {
        //TODO add data update in repo
        return 0;
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

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
    public List<Vote> getVotes() {
        return votesRepository.findAll();
    }
}

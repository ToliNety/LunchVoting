package org.tolinety.springrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.model.Vote;
import org.tolinety.springrest.service.RestaurantService;

import java.util.List;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RESTController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/rests")
    public List<Restaurant> getRestaurants(){
        return restaurantService.getAll();
    }

    @GetMapping(value = "/votes")
    public List<Vote> getVotes(){
        return restaurantService.getVotes();
    }
}

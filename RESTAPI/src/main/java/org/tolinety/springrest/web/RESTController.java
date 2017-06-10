package org.tolinety.springrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.model.Vote;
import org.tolinety.springrest.service.RestaurantService;

import java.net.URI;
import java.util.List;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RESTController {
    private static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = REST_URL)
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping(value = REST_URL + "/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return restaurantService.getByID(id);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = REST_URL + "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        return restaurantService.update(restaurant, id);
    }

    @DeleteMapping(value = REST_URL + "{id}")
    public int deleteRestaurant(@PathVariable int id){
        return restaurantService.delete(id);
    }


    @GetMapping(value = "/votes")
    public List<Vote> getVotes() {
        return restaurantService.getVotes();
    }


}

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
@RequestMapping(value = RESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RESTController {
    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return restaurantService.getByID(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        restaurantService.update(restaurant);
    }

    @DeleteMapping(value = "{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.delete(id);
    }
}

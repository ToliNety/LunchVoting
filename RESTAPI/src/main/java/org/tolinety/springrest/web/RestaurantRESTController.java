package org.tolinety.springrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.model.Restaurant;
import org.tolinety.springrest.service.DishService;
import org.tolinety.springrest.service.LunchService;
import org.tolinety.springrest.service.RestaurantService;
import org.tolinety.springrest.to.DishTo;
import org.tolinety.springrest.to.LunchTo;

import java.net.URI;
import java.util.List;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
@RestController
@RequestMapping(value = RestaurantRESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRESTController {
    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private LunchService lunchService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return restaurantService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        restaurantService.update(restaurant);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRestaurant(@PathVariable int id) {
        restaurantService.delete(id);
    }

    @GetMapping(value = "/{id}/dishes")
    public List<Dish> getDishes(@PathVariable int id) {
        return dishService.getAllByRestaurant(id);
    }

    @GetMapping(value = "/{id}/dishes/{dishId}")
    public Dish getDishe(@PathVariable int dishId) {
        return dishService.get(dishId);
    }

    @PostMapping(value = "/{id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> addDish(@PathVariable int id, @RequestBody DishTo dishTo) {
        Dish created = dishService.create(dishTo, id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + id + "/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/dishes/{dishId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDish(@PathVariable("id") int restaurantId, @PathVariable("dishId") int dishId, @RequestBody DishTo dishTo) {
        dishService.update(dishTo, dishId, restaurantId);
    }

    @DeleteMapping(value = "/{id}/dishes/{dishId}")
    public void deleteDish(@PathVariable int dishId) {
        dishService.delete(dishId);
    }

    @GetMapping(value = "/{id}/lunches")
    public List<LunchMenu> getLunches(@PathVariable int id) {
        return lunchService.getAllByRestaurant(id);
    }

    @GetMapping(value = "/{id}/lunches/{luncheId}")
    public LunchMenu getLunch(@PathVariable int luncheId) {
        return lunchService.get(luncheId);
    }

    @PostMapping(value = "/{id}/lunches", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LunchMenu> addLunch(@PathVariable("id") int restaurantId, @RequestBody LunchTo lunchTo) {
        LunchMenu created = lunchService.create(lunchTo, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + restaurantId + "/lunches/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}/lunches/{luncheId}")
    public void deleteLunch(@PathVariable int luncheId) {
        lunchService.delete(luncheId);
    }
}

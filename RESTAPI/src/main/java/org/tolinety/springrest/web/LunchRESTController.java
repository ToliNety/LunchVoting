package org.tolinety.springrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tolinety.springrest.AuthorizedUser;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.service.LunchService;
import org.tolinety.springrest.service.VoteService;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 25.06.2017.
 */
@RestController
@RequestMapping(value = LunchRESTController.REST_URL)
public class LunchRESTController {
    public static final String REST_URL = "/lunches";

    @Autowired
    LunchService lunchService;

    @Autowired
    VoteService voteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LunchMenu> getTodayLunches() {
        return lunchService.getAllByDate(LocalDate.now());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LunchMenu getSelectedLunch(@PathVariable int id) {
        return lunchService.get(id);
    }

    @PutMapping(value = "/{id}")
    public void vote(@PathVariable("id") int lunchId) {
        int userId = AuthorizedUser.id();
        voteService.vote(userId, lunchId);
    }

    @GetMapping(value = "/ratingByRestaurant")
    public int getRaintingByRestaurant(@RequestParam("restaurant") int restaurantId) {
        return voteService.countByRestaurant(restaurantId);

    }

    @GetMapping(value = "/ratingByLunch")
    public int getRaintingByLunch(@RequestParam("lunch") int lunchId) {
        return voteService.countByLunch(lunchId);
    }
}

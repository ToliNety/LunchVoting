package org.tolinety.springrest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tolinety.springrest.model.User;
import org.tolinety.springrest.service.UserService;
import org.tolinety.springrest.to.UserTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by tolin on 25.06.2017.
 */
@RestController
@RequestMapping(value = AdminRESTController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRESTController {
    public static final String REST_URL = "/admin";

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody UserTo userTo) {
        User created = userService.create(userTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo, @PathVariable("id") int id) {
        userService.update(userTo, id);
    }

    @GetMapping(value = "/by")
    public User getByMail(@RequestParam("email") String email) {
        return userService.getByEmail(email);
    }

}

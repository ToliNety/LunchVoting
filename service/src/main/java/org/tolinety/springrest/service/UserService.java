package org.tolinety.springrest.service;

import org.tolinety.springrest.model.User;

import java.util.List;

/**
 * Created by tolin on 20.06.2017.
 */
public interface UserService {
    User create(User user);

    User update(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}

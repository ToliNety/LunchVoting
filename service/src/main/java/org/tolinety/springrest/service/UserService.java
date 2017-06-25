package org.tolinety.springrest.service;

import org.tolinety.springrest.model.User;
import org.tolinety.springrest.to.UserTo;

import java.util.List;

/**
 * Created by tolin on 20.06.2017.
 */
public interface UserService {
    User create(UserTo userTo);

    User update(UserTo userTo, int id);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();
}

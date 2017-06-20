package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.User;
import org.tolinety.springrest.repository.UserRepository;

import java.util.List;

import static org.tolinety.springrest.util.ValidationUtil.*;

/**
 * Created by tolin on 20.06.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public User create(User user) {
        Assert.notNull(user, "User mustn't be null");
        checkNew(user);
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "User mustn't be null");
        checkNotFoundWithId(get(user.getId()), user.getId());
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        checkNotFound(repository.delete(id) != 0, String.valueOf(id));
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email), "No user with email = " + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getDistinctByDeletedFalseOrderByEmail();
    }
}

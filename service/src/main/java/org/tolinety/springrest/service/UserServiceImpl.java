package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tolinety.springrest.AuthorizedUser;
import org.tolinety.springrest.model.User;
import org.tolinety.springrest.repository.UserRepository;
import org.tolinety.springrest.to.UserTo;

import java.util.List;

import static org.tolinety.springrest.to.UserTo.getFromTo;
import static org.tolinety.springrest.to.UserTo.prepareToSave;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFound;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by tolin on 20.06.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    public User create(UserTo userTo) {
        Assert.notNull(userTo, "User mustn't be null");
        return repository.save(getFromTo(prepareToSave(userTo)));
    }

    @Override
    public User update(UserTo userTo, int id) {
        Assert.notNull(userTo, "User mustn't be null");
        User user = checkNotFoundWithId(get(id), id);
        user.update(prepareToSave(userTo));
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}

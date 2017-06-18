package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.repository.CrudMenuRepository;
import org.tolinety.springrest.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

import static org.tolinety.springrest.util.ValidationUtil.checkNew;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFound;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by tolin on 14.06.2017.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuRepository repository;

    @Override
    public List<LunchMenu> getWithDataByDate(LocalDate date) {
        Assert.notNull(date, "Date mustn't be null");
        return repository.getByDate(date);
    }

    @Override
    public LunchMenu get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public LunchMenu create(LunchMenu menu, int restaurantId) {
        Assert.notNull(menu, "LunchMenu mustn't be null");
        checkNew(menu);
        return repository.save(menu, restaurantId);
    }

    @Override
    public void delete(int id) {
        int result = repository.delete(id);
        checkNotFound((result != 0), "id = " + String.valueOf(id));
    }

    @Override
    public void deleteBefore(LocalDate date) {
        Assert.notNull(date, "Date mustn't be null");
        int result = repository.deleteBefore(date);
        checkNotFound((result != 0), "date before = " + String.valueOf(date));
    }
}

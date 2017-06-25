package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.Dish;
import org.tolinety.springrest.model.LunchMenu;
import org.tolinety.springrest.repository.DishRepository;
import org.tolinety.springrest.repository.MenuRepository;
import org.tolinety.springrest.to.LunchTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.tolinety.springrest.to.LunchTo.getFromTo;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFound;
import static org.tolinety.springrest.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by tolin on 14.06.2017.
 */
@Service
public class LunchServiceImpl implements LunchService {
    @Autowired
    MenuRepository repository;

    @Autowired
    DishRepository dishRepository;

    @Override
    @Cacheable("lunches")
    public List<LunchMenu> getAllByDate(LocalDate date) {
        Assert.notNull(date, "Date mustn't be null");
        return repository.getByDate(date);
    }

    @Override
    public List<LunchMenu> getAllByRestaurant(int restaurantId) {
        return repository.getByRestaurant(restaurantId);
    }

    @Override
    public LunchMenu get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "lunches", allEntries = true)
    public LunchMenu create(LunchTo lunchTo, int restaurantId) {
        Assert.notNull(lunchTo, "LunchMenu mustn't be null");
        List<Dish> dishes = new ArrayList<>();

        IntStream.of(lunchTo.getDisheIds()).forEach(i -> {
            Dish dish = checkNotFound(dishRepository.get(i), "dish id=" + i);
            if (dish.getRestaurant().getId() != restaurantId) {
                throw new IllegalArgumentException("All dishes must be from restaurant with Id = " + restaurantId);
            }
            dishes.add(dish);
        });

        return repository.save(getFromTo(lunchTo, dishes), restaurantId);
    }

    @Override
    @CacheEvict(value = "lunches", allEntries = true)
    public void delete(int id) {
        int result = repository.delete(id);
        checkNotFound((result != 0), "id = " + String.valueOf(id));
    }

    @Override
    @CacheEvict(value = "lunches", allEntries = true)
    public void deleteBefore(LocalDate date) {
        Assert.notNull(date, "Date mustn't be null");
        int result = repository.deleteBefore(date);
        checkNotFound((result != 0), "date before = " + String.valueOf(date));
    }

    @Override
    @CacheEvict(value = "lunches", allEntries = true)
    public void evictCache() {

    }
}

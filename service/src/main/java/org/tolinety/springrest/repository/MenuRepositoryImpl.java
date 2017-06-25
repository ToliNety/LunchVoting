package org.tolinety.springrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 18.06.2017.
 */
@Repository
public class MenuRepositoryImpl implements MenuRepository {
    @Autowired
    CrudMenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public LunchMenu save(LunchMenu menu, int restaurantId) {
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

    @Override
    public int delete(int id) {
        return menuRepository.deleteById(id);
    }

    @Override
    public int deleteBefore(LocalDate date) {
        return menuRepository.deleteByRegisteredBefore(date);
    }

    @Override
    public LunchMenu get(int id) {
        return menuRepository.getById(id);
    }

    @Override
    public List<LunchMenu> getByDate(LocalDate date) {
        return menuRepository.getDistinctByRegistered(date);
    }

    @Override
    public List<LunchMenu> getByRestaurant(int restaurantId) {
        return menuRepository.getDistinctByRestaurantId(restaurantId);
    }
}

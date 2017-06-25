package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.LunchMenu;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by tolin on 14.06.2017.
 */
@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<LunchMenu, Integer> {
    @EntityGraph(value = LunchMenu.WITH_DATA)
    List<LunchMenu> getDistinctByRegistered(LocalDate date);

    @EntityGraph(value = LunchMenu.WITH_DATA)
    List<LunchMenu> getDistinctByRestaurantId(int restaurantId);

    @EntityGraph(value = LunchMenu.WITH_DATA)
    LunchMenu getById(int id);

    @Modifying
    @Transactional
    int deleteById(int id);

    @Modifying
    @Transactional
    int deleteByRegisteredBefore(LocalDate date);
}

package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by ToliNeTy on 04.03.2017.
 */
@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Transactional
    int deleteById(int id);

    List<Restaurant> findAllByOrderByName();
}

package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tolinety.springrest.model.Dish;

/**
 * Created by tolin on 14.06.2017.
 */
public interface DishRepository extends JpaRepository<Dish, Integer> {

}

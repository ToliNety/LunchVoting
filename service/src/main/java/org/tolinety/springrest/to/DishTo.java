package org.tolinety.springrest.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.tolinety.springrest.model.Dish;

import javax.validation.constraints.NotNull;

/**
 * Created by tolin on 24.06.2017.
 */
@Data
@AllArgsConstructor
public class DishTo {
    @NotBlank
    private String dishName;

    @NotNull
    @Range(min = 100)
    private Integer dishPrice;

    public static Dish getFromTo(DishTo dishTo) {
        return getFromTo(dishTo, null);
    }

    public static Dish getFromTo(DishTo dishTo, Integer id) {
        return new Dish(id, dishTo.getDishName(), dishTo.getDishPrice(), null);
    }
}

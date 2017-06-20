package org.tolinety.springrest.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by tolin on 10.06.2017.
 */
@Entity
@Table(name = "dishes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dish extends BaseEntity {
    @NotBlank
    @Column(name = "dish_name")
    private String dishName;

    @NotNull
    @Column(name = "dish_price")
    private int dishPrice;

    @Column(name = "deleted", nullable = false, columnDefinition = "bool default false")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Dish(Integer id, String dishName, int dishPrice, Restaurant restaurant) {
        this(id, dishName, dishPrice, false, restaurant);
    }

    public Dish(Integer id, String dishName, int dishPrice, boolean deleted, Restaurant restaurant) {
        this(dishName, dishPrice, deleted, restaurant);
        this.setId(id);
    }

    public static Dish of(Dish dish) {
        return new Dish(null, dish.getDishName(), dish.getDishPrice(), dish.getRestaurant());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("dishName", dishName)
                .add("dishPrice", dishPrice)
                .add("deleted", deleted)
                .add("restaurant", restaurant.getId())
                .toString();
    }
}

package org.tolinety.springrest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by tolin on 10.06.2017.
 */
@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dish extends BaseEntity {
    @NotBlank
    @Column(name = "dish_name")
    private String dishName;

    @NotNull
    @Column(name = "dish_price")
    private int dishPrice;

    @Column(name = "deleted")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}

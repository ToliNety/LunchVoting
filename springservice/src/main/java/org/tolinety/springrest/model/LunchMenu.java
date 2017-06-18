package org.tolinety.springrest.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by ToliNeTy on 05.03.2017.
 */
@Entity
@Table(name = "lunch_menus", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "registered"}, name = "lunch_restaurant_date_idx")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraph(name = LunchMenu.WITH_DATA, attributeNodes = {
        @NamedAttributeNode("restaurant"),
        @NamedAttributeNode("dishes")})
public class LunchMenu extends BaseEntity {
    public static final String WITH_DATA = "LunchMenu.withAllData";

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "registered")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate registered;

    @OneToMany
    @JoinTable(
            name = "menus",
            joinColumns = @JoinColumn(name = "lunch_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
    private List<Dish> dishes;

    public LunchMenu(Integer id, Restaurant restaurant, LocalDate registered) {
        this(restaurant, registered, Collections.emptyList());
        this.setId(id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("registered", registered)
                .add("restaurant", restaurant.getId())
                .toString();
    }
}

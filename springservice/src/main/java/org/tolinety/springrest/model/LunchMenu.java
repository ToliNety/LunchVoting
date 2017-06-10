package org.tolinety.springrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by ToliNeTy on 05.03.2017.
 */
@Entity
@Table(name = "lunch_menus", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "registered"}, name = "lunch_restaurant_date_idx")})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LunchMenu extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "registered")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registered;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menus",
            joinColumns = @JoinColumn(name = "lunch_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"))
    private List<Dish> dishes;
}

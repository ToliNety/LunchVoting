package org.tolinety.springrest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * ToliNeTy on 04.03.2017.
 */
@Entity
@Table(name = "restaurants", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "restaurant_name_idx")})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Restaurant extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    private List<LunchMenu> lunchMenus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Vote> votes;
}

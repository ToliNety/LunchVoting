package org.tolinety.springrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * ToliNeTy on 04.03.2017.
 */
@Entity
@Table(name = "restaurants", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "restaurant_name_idx")})
@Data
@AllArgsConstructor()
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Restaurant extends BaseEntity {
    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Restaurant(Integer id, String name) {
        this(name);
        this.setId(id);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", getId())
                .add("name", name)
                .toString();
    }
}

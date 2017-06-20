package org.tolinety.springrest.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ToliNeTy on 05.03.2017.
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraph(name = User.WITH_ROLES, attributeNodes = {@NamedAttributeNode("roles")})
public class User extends BaseEntity {
    public static final String WITH_ROLES = "User.withRoles";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Length(min = 5)
    private String password;

    @Column(name = "deleted", nullable = false, columnDefinition = "bool default false")
    private boolean deleted = false;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(String email, String password) {
        this(email, password, false, new Date(), EnumSet.of(Role.ROLE_USER));
    }

    public User(Integer id, String email, String password, Role role, Role... roles) {
        this(email, password, false, new Date(), EnumSet.of(role, roles));
        this.setId(id);
    }

    @Override
    public String toString() {
        ArrayList<Role> roles = new ArrayList<>(this.roles);
        roles.sort(Comparator.naturalOrder());
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("email", email)
                .add("password", password)
                .add("deleted", deleted)
                .add("roles", roles)
                .toString();
    }
}

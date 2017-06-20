package org.tolinety.springrest.model;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by ToliNeTy on 06.03.2017.
 */
@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "registered"}, name = "votes_idx")})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraph(name = Vote.WITH_DATA, attributeNodes = {
        @NamedAttributeNode("lunch"),
        @NamedAttributeNode("user")})
public class Vote extends BaseEntity {
    public static final String WITH_DATA = "Vote.withData";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lunch_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LunchMenu lunch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "registered")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registered;

    public Vote(LocalDate registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("lunch", lunch.getId())
                .add("user", user.getId())
                .add("registered", registered)
                .toString();
    }
}

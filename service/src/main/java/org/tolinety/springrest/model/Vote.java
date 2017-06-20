package org.tolinety.springrest.model;

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
public class Vote extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "lunch_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LunchMenu lunch;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "registered")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
}

package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.User;

import java.util.List;

/**
 * Created by tolin on 20.06.2017.
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.deleted = true WHERE u.id=?1")
    int delete(int id);

    @EntityGraph(User.WITH_ROLES)
    User getByEmail(String email);

    @EntityGraph(User.WITH_ROLES)
    List<User> getDistinctByDeletedFalseOrderByEmail();
}

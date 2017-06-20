package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.tolinety.springrest.model.Vote;

/**
 * Created by ToliNeTy on 06.03.2017.
 */
@Transactional(readOnly = true)
public interface VotesRepository extends JpaRepository<Vote, Integer> {
    Vote getByUserIdAndLunchId(int userId, int lunchId);
}

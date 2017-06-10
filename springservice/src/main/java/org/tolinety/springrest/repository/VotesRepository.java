package org.tolinety.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tolinety.springrest.model.Vote;

import java.util.List;

/**
 * Created by ToliNeTy on 06.03.2017.
 */
public interface VotesRepository extends JpaRepository<Vote, Integer> {
    @Override
    List<Vote> findAll();
}

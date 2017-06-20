package org.tolinety.springrest.repository;

import org.tolinety.springrest.model.Vote;

import java.time.LocalDate;

/**
 * Created by tolin on 20.06.2017.
 */
public interface VoteRepository {
    Vote get (int userId, LocalDate date);

    Vote save(Vote vote, int userId, int lunchId);
}

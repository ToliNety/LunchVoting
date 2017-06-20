package org.tolinety.springrest.service;

import java.time.LocalDate;

/**
 * Created by tolin on 20.06.2017.
 */
public interface VoteService {
    void vote(int userId, int lunchId, LocalDate date);

    void vote(int userId, int lunchId);
}

package org.tolinety.springrest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.tolinety.springrest.model.Vote;
import org.tolinety.springrest.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by tolin on 20.06.2017.
 */
@Service
@Slf4j
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepository repository;

    @Override
    public void vote(int userId, int lunchId) {
        vote(userId, lunchId, LocalDate.now());
    }

    @Override
    @Transactional
    public void vote(int userId, int lunchId, LocalDate date) {
        Assert.notNull(date, "Date musn't be null");
        Vote vote = repository.get(userId, date);
        if (vote == null) {
            vote = new Vote(date);
            repository.save(vote, userId, lunchId);
            log.info("New vote added: " + vote);
        } else {
            if (vote.getUser().getId() != userId) {
                throw new IllegalArgumentException("User can change only his(her)own vote");
            }
            if (LocalTime.now().isAfter(LocalTime.of(11, 00))) {
                throw new IllegalArgumentException("Vote can't be changed after 11:00");
            }
            repository.save(vote, userId, lunchId);
            log.info("Vote updated: " + vote);
        }

    }
}

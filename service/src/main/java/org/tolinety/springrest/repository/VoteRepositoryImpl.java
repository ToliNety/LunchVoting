package org.tolinety.springrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tolinety.springrest.model.Vote;

import java.time.LocalDate;

/**
 * Created by tolin on 20.06.2017.
 */
@Repository
public class VoteRepositoryImpl implements VoteRepository {
    @Autowired
    CrudVoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CrudMenuRepository menuRepository;

    @Override
    public Vote get(int userId, LocalDate date) {
        return voteRepository.getByUserIdAndRegistered(userId, date);
    }

    @Override
    public Vote save(Vote vote, int userId, int lunchId) {
        if (vote.isNew()) {
            vote.setUser(userRepository.getOne(userId));
        }
        vote.setLunch(menuRepository.getOne(lunchId));
        return voteRepository.save(vote);
    }
}

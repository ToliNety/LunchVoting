package org.tolinety.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.tolinety.springrest.model.Vote;
import org.tolinety.springrest.repository.VotesRepository;

/**
 * Created by tolin on 20.06.2017.
 */
public class VoteServiceImpl implements VoteService{
    @Autowired
    VotesRepository repository;

    @Override
    public void vote(int userId, int menuId) {
        Vote vote = repository.getByUserIdAndLunchId(userId, menuId);
    }
}

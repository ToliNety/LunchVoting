package org.tolinety.springrest.testdata;

import org.tolinety.springrest.ModelMatcher;
import org.tolinety.springrest.model.BaseEntity;
import org.tolinety.springrest.model.Vote;

import static org.tolinety.springrest.model.BaseEntity.START_SEQ;

/**
 * Created by tolin on 20.06.2017.
 */
public class VoteTestData {
    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);

    public static final int VOTE_ID = START_SEQ+11;

}

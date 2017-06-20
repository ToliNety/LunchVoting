package org.tolinety.springrest.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.tolinety.springrest.testdata.MenuTestData.MENU3;
import static org.tolinety.springrest.testdata.UserTestData.ADMIN_ID;

/**
 * Created by tolin on 20.06.2017.
 */
public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    VoteService service;

    @Test
    public void voteNew() throws Exception {
        service.vote(ADMIN_ID, MENU3.getId(), LocalDate.of(2017, 06, 14));
    }

    @Test
    public void voteUpdate() throws Exception {
        service.vote(ADMIN_ID, MENU3.getId(), LocalDate.of(2017, 06, 13));
    }

}
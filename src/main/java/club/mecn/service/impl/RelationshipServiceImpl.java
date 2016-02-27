package club.mecn.service.impl;

import club.mecn.dao.UserDao;
import club.mecn.module.Relationship;
import club.mecn.module.User;
import club.mecn.service.RelationshipService;
import club.mecn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/2.
 */
@Service
public class RelationshipServiceImpl implements RelationshipService{


    @Autowired
    private UserService userService;
    /**
     *
     * @param fromUsername
     * @param toUsername
     */

    @Transactional
    public void addFollowRelation(String fromUsername, String toUsername) {
        User fromUser = userService.getByName(fromUsername);
        User toUser = userService.getByName(toUsername);

        fromUser.followUser(toUser);
    }
}

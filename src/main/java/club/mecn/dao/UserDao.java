package club.mecn.dao;

import club.mecn.module.User;

import java.util.List;

/**
 * Created by Administrator on 2016/1/14.
 */
public interface UserDao extends BaseDao{
   
    User getByName(String name);

    User getByEmail(String email);
}

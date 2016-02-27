package club.mecn.service;

import club.mecn.module.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/14.
 */
public interface UserService {

    /**
     * 用户注册方法
     * @param user 保存的新用户对象
     */
    void register(User user);
    
    /**
     * 修改用户基本信息:
     * 用户名(更换用户名会增加曾用名，修改用户名时限为7天)
     * 密码
     * 邮箱(更换邮箱会重新发邮件)
     * @param newUser 更新的新用户对象
     */
    void updateUserBaseInfo(User newUser);
    
    Map<String,Object> login(String identifier,String password);
    
    boolean checkPassword(User user,String password);

    boolean checkEmailActivated(User user);

    void delete(int id);

    List<User> getAll();

    User getByName(String name);

    User getByEmail(String email);

    List<User> getFollowings(String username);

    List<User> getFolloweds(String username);

    int getFollowingsNum(String username);

    int getFollowedsNum(String username);

}

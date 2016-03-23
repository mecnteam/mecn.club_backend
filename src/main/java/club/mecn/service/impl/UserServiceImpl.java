package club.mecn.service.impl;

import club.mecn.dao.UserDao;
import club.mecn.module.Relationship;
import club.mecn.module.UsedName;
import club.mecn.module.User;
import club.mecn.service.UserService;
import club.mecn.util.EncryptionUtil;
import club.mecn.util.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/15.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    
    @Transactional
    public void register(User user) {
    	//密码加密
    	user.setPassword(EncryptionUtil.getHash(user.getPassword()));
        userDao.save(user);
    }

    
    @Transactional
	public void updateUserBaseInfo(User newUser) {
    	User oldUser = userDao.getById(User.class, newUser.getUserId());
    	//如果用户名不相等添加曾用名
    	if(!oldUser.getUsername().equals(newUser.getUsername()))
    	{
    		newUser.addUsedName(new UsedName(oldUser.getUsername(),new Date()));
    	}
    	userDao.update(newUser, newUser.getUserId());
	}
    
    public Map<String, Object> login(String identifier, String password) {
    	User user = null;
    	if((user = this.getByName(identifier)) != null || (user = this.getByEmail(identifier)) != null)
    	{
    		if(this.checkPassword(user, password))
    		{
    			if(this.checkEmailActivated(user))
    			{
    				return JsonUtil.returnJsonMap(JsonUtil.SUCCESS_STATUS,100,"登录成功",user);
    			}
    			else{
    				//not activited
    				return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS,-103,"邮箱未激活");
    			}
    			
    		}
    		else{
    			//fail password error
                return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS,-102,"密码错误");
    		}
    		
    	}
    	else{
    		//no user
            return JsonUtil.returnJsonMap(JsonUtil.FAIL_STATUS,-101,"没有此用户");
    		
    	}
    	
	}


    public boolean checkPassword(User user, String password) {
    	if(user.getPassword().equals(EncryptionUtil.getHash(password)))
        {
            return true;
        }else
            return false;
	}    


    public boolean checkEmailActivated(User user) {
    	return user.isActiveEmail();
    }  
    
    public List<User> getFollowings(String username) {
        User user = this.getByName(username);
        Set<Relationship> relationships = user.getFollowings();
        List<User> followings = new ArrayList<User>();

        for(Relationship r : relationships)
        {
            //此时查询的用户是to所在的视角
            followings.add(r.getFromUser());
        }

        return followings;
    }

    public List<User> getFolloweds(String username) {
        User user = this.getByName(username);
        Set<Relationship> relationships = user.getFolloweds();
        List<User> followeds = new ArrayList<User>();

        for (Relationship r : relationships)
        {
            //此时查询的用户是from所在的视角
            followeds.add(r.getToUser());
        }
        return followeds;
    }

    public int getFollowingsNum(String username) {
        List<User> followings = this.getFollowings(username);

        return followings.size();
    }

    public int getFollowedsNum(String username) {
        List<User> followeds = this.getFolloweds(username);

        return followeds.size();
    }


    public void delete(int id) {
        userDao.delete(User.class,id);
    }

    public List<User> getAll() {
        return userDao.getAll("User");
    }

    public User getByName(String name) {
        return userDao.getByName(name);
    }

    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

}

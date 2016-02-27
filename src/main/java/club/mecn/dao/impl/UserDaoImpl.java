package club.mecn.dao.impl;

import club.mecn.dao.UserDao;
import club.mecn.module.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by Administrator on 2016/1/14.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
    

    public User getByName(String name) {
        Query query = em.createQuery("select u from User u where u.username =:name");
        query.setParameter("name",name);
        User user = (User)query.getSingleResult();
        return user;

    }

    public User getByEmail(String email) {
        Query query = em.createQuery("select u from User u where u.email =:email");
        query.setParameter("email",email);
        User user = (User)query.getSingleResult();
        return user;
    }
}

package club.mecn.dao.impl;

import club.mecn.dao.BaseDao;
import club.mecn.module.Category;
import club.mecn.module.User;
import club.mecn.util.BeanValueExchangeUtil;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
@Repository
public class BaseDaoImpl implements BaseDao{
	
    @PersistenceContext
    protected EntityManager em;


    public void save(Object entity) {
        em.persist(entity);
    }

    public <T> void delete(Class<T> entityClass, Serializable entityId) {
        T t = this.getById(entityClass,entityId);
        em.remove(t);
    }

    public <T> T getById(Class<T> entityClass, Serializable entityId) {

        return em.find(entityClass,entityId);
    }

    public <T> List<T> getAll(String entityClassName) {
        List<T> results =  em.createQuery("select t from "+entityClassName+" t").getResultList();
        return results;
    }

	public void update(Object entity, Serializable entityId) {
		Object oldEntity = this.getById(Object.class, entityId);
		BeanValueExchangeUtil.exchangeBeanValue(oldEntity, entity);
		
	}

	
	
}

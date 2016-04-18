package club.mecn.dao.impl;

import club.mecn.dao.CategoryDao;
import club.mecn.module.Category;
import club.mecn.module.User;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/2/3.
 */

@Repository
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao{

	public Category getByName(String name) {
		try{
    		
    		Query query = em.createQuery("select c from Category c where c.categoryName =:name");
    		query.setParameter("name",name);
    		Category category = (Category)query.getSingleResult();
    		return category;
    	}catch(Exception e)
    	{
    		//没有就抛出异常
    		return null;
    	}
	}

}

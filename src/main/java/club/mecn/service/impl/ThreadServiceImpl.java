package club.mecn.service.impl;

import club.mecn.dao.CategoryDao;
import club.mecn.dao.ThreadDao;
import club.mecn.module.Category;
import club.mecn.module.Post;
import club.mecn.module.Thread;
import club.mecn.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/2/2.
 */
@Service
public class ThreadServiceImpl implements ThreadService {

    @Autowired
    private ThreadDao threadDao;

    @Autowired
    private CategoryDao categoryDao;


    @Transactional
    public void add(Thread thread,String categoryName) {
    	
    	Category category = categoryDao.getByName(categoryName);
    	
    	if(category == null)
    	{
    		category = new Category(categoryName);
    	}
    	   	
    	thread.addCategory(category);

        threadDao.save(thread);
    }

    @Transactional
    public void delete(int id) {
    	threadDao.delete(Thread.class, id);
    }

    public List<Post> getAllPosts(int threadId) {

        Thread thread = threadDao.getById(Thread.class,threadId);

        List<Post> posts = new ArrayList<Post>(thread.getPosts());

        return posts;

    }

	public Thread getById(int id) {
		
		return threadDao.getById(Thread.class, id);
	}

	@Transactional
	public void updateThreadInfo(Thread newThread) {
		
		threadDao.update(newThread, newThread.getThreadId());
	}

	/* (non-Javadoc)
	 * @see club.mecn.service.ThreadService#updateCategories(int, java.lang.String[])
	 */
	@Transactional
	public void updateCategories(int threadId ,String[] categorynames) {
		Thread thread = getById(threadId);
		HashSet<Category> newCategories = new HashSet<Category>();
		for(String categoryname : categorynames)
		{
			Category category = categoryDao.getByName(categoryname);
			if(category == null )
			{
				category = new Category(categoryname);
			}
			newCategories.add(category);
		}
		
		thread.setCategories(newCategories);
	}

	public List<Thread> getAll() {
		
		return threadDao.getAll("Thread");
	}
}

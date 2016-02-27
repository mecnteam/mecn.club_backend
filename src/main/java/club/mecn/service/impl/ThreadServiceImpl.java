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
import java.util.List;

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
    public void add(Thread thread,int categoryId) {
        Category c = categoryDao.getById(Category.class,categoryId);
        thread.setCategory(c);

        threadDao.save(thread);
    }

    public void delete(int id) {

    }

    public List<Post> getAllPosts(int threadId) {

        Thread thread = threadDao.getById(Thread.class,threadId);

        List<Post> posts = new ArrayList<Post>(thread.getPosts());

        return posts;

    }
}

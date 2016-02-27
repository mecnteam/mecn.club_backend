package club.mecn.service.impl;

import club.mecn.dao.CategoryDao;
import club.mecn.module.Category;
import club.mecn.module.Thread;
import club.mecn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/3.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public void add(Category category) {
        categoryDao.save(category);
    }

    @Transactional
    public void delete(int id) {
        categoryDao.delete(Category.class,id);
    }

    public List<Category> getAll() {
        return categoryDao.getAll("Category");
    }

    public Category getById(int id) {
        return categoryDao.getById(Category.class,id);
    }


    public List<Thread> getAllThreads(int id) {
        Category c =categoryDao.getById(Category.class,id);

        List<Thread> threads = new ArrayList<Thread>(c.getThreads());

        return threads;
    }
}

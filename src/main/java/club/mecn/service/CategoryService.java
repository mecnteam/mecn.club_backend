package club.mecn.service;

import club.mecn.module.Category;
import club.mecn.module.Thread;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3.
 */
public interface CategoryService {

    void add(Category category);

    void delete(int id);

    List<Category> getAll();

    Category getById(int id);
    
    /**
     * 根据id获取所有的值
     * @param id
     * @return
     */
    List<Thread> getAllThreads(int id);
}

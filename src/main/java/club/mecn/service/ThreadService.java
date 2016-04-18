package club.mecn.service;

import club.mecn.module.Category;
import club.mecn.module.Post;
import club.mecn.module.Thread;
import club.mecn.module.User;

import java.util.List;

/**
 * Created by Administrator on 2016/2/2.
 */
public interface ThreadService {

    /**
     * 添加主题帖
     * @param thread
     * @param categoryId
     */
    void add(Thread thread,String categoryName);

    void delete(int id);
    
    Thread getById(int id);
    
    /**
     *
     * @param threadId
     * @param categories 新的全部categories
     */
    void updateCategories(int threadId,String[] categories);
    
    void updateThreadInfo(Thread thread);
    
    List<Thread> getAll();

    List<Post> getAllPosts(int threadId);
}

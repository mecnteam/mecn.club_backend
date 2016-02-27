package club.mecn.service;

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
    void add(Thread thread,int categoryId);

    void delete(int id);

    List<Post> getAllPosts(int threadId);
}

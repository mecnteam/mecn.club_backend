package club.mecn.service.impl;

import club.mecn.dao.PostDao;
import club.mecn.dao.ThreadDao;
import club.mecn.module.Comment;
import club.mecn.module.Post;
import club.mecn.module.Thread;
import club.mecn.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/3.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private ThreadDao threadDao;

    @Transactional
    public void add(Post post, int threadId) {

        post.setThread(threadDao.getById(Thread.class,threadId));

        postDao.save(post);
    }

    @Transactional
    public void delete(int id) {

    }

    /**
     * 返回一条跟帖的所有评论
     * @param postId
     * @return
     */
    public List<Comment> getAllComments(int postId) {

        Post post = threadDao.getById(Post.class,postId);

        List<Comment> comments = new ArrayList<Comment>(post.getComments());

        return comments;
    }


}

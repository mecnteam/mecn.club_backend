package club.mecn.service.impl;

import club.mecn.dao.CommentDao;
import club.mecn.dao.PostDao;
import club.mecn.module.Comment;
import club.mecn.module.Post;
import club.mecn.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/2/3.
 */

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @Transactional
    public void add(Comment comment, int postId) {

        comment.setPost(postDao.getById(Post.class,postId));
        commentDao.save(comment);
    }

    @Transactional
    public void delete(int id) {

    }
}

package club.mecn.service;

import club.mecn.module.Comment;

/**
 * Created by Administrator on 2016/2/3.
 */
public interface CommentService {

    void add(Comment comment, int postId);

    void delete(int id);
}

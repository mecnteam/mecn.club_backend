package club.mecn.service;

import club.mecn.module.Comment;
import club.mecn.module.Post;
import club.mecn.module.Thread;

import java.util.List;

/**
 * Created by Administrator on 2016/2/3.
 */
public interface PostService {

    void add(Post post,int threadId);

    void delete(int id);

    List<Comment> getAllComments(int postId);
}

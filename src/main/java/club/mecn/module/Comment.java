package club.mecn.module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/29.
 * 主回复下面的评论
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_id")
    private Integer commentId;

    @Lob
    @Column(name="c_content")
    private String commentContent;

    @Column(name="c_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentTime;


    /**
     * 回复用户
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="c_user_id")
    private User user;


    /**
     * 回复所属的post
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="c_post_id")
    private Post post;

    public Comment(String commentContent, Date commentTime) {
        this.commentContent = commentContent;
        this.commentTime = commentTime;
    }

    
    public Comment(String commentContent) {
		
		this.commentContent = commentContent;
	}


	public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

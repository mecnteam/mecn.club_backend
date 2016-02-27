package club.mecn.module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/29.
 * 一个主题帖的主回复贴
*/
@Entity
@Table(name= "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id")
    private Integer postId;

    @Column(name="p_content")
    private String postContent;

    @Column(name="p_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postTime;

    /**
     * 跟帖的回复
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY ,mappedBy = "post")
    private Set<Comment> comments = new HashSet<Comment>();
    /**
     * 跟帖用户
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="p_user_id")
    private User user;

    /**
     * 跟帖的主题帖
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="p_thread_id")
    private Thread thread;

    public Post(String postContent, Date postTime) {
        this.postContent = postContent;
        this.postTime = postTime;
    }

    public Post() {

    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}

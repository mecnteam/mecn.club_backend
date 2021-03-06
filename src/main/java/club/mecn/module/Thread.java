package club.mecn.module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/29.
 * 用户发帖(帖子1楼)
 *
 */
@Entity
@Table(name = "thread")
public class Thread implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="t_id")
    private Integer threadId;

    /**
     * 用户发帖标题
     */
    @Column(name = "t_title")
    private String threadTitle;

    //大文本
    @Lob
    @Column(name="t_content")
    private String threadContent;

    @Column(name="t_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date threadTime;


//    /**
//     * 帖子的标签
//     */
//    @Column(name = "t_tags")
//    private String tags;
    /**
     * 单向一对一
     * 最后的跟帖的用户
     */
    @OneToOne
    @JoinColumn(name = "last_post_user_id")
    private User lastPostUser;


    /**
     * 单向一对一
     * 最后评论的用户
     */
    @OneToOne
    @JoinColumn(name = "last_com_user_id")
    private User lastCommentUser;
    /**
     * 主题帖对应多个跟帖
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY ,mappedBy = "thread")
    private Set<Post> posts = new HashSet<Post>();

    /**
     * 发主题帖用户
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="t_user_id")
    private User user;

    /**
     * 主题帖子所属类别(多对多)
     */
//    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinColumn(name="t_category_id")
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "category_thread",
    joinColumns = {@JoinColumn(name = "t_id", referencedColumnName = "t_id")},
    inverseJoinColumns = {@JoinColumn(name = "cg_id", referencedColumnName ="cg_id")})
    private Set<Category> categories = new HashSet<Category>();


    public Thread(String threadTitle, String threadContent, Date threadTime ) {
        this.threadTitle = threadTitle;
        this.threadContent = threadContent;
        this.threadTime = threadTime;
    }

    
    public Thread(String threadTitle) {
		
		this.threadTitle = threadTitle;
	}


	public Thread() {
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public String getThreadTitle() {
        return threadTitle;
    }

    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    public Date getThreadTime() {
        return threadTime;
    }

    public void setThreadTime(Date threadTime) {
        this.threadTime = threadTime;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public User getLastCommentUser() {
        return lastCommentUser;
    }

    public void setLastCommentUser(User lastCommentUser) {
        this.lastCommentUser = lastCommentUser;
    }

    public User getLastPostUser() {
        return lastPostUser;
    }

    public void setLastPostUser(User lastPostUser) {
        this.lastPostUser = lastPostUser;
    }

    public void addCategory(Category category)
    {
    	this.categories.add(category);
    }
//    public String getTags() {
//        return tags;
//    }
//
//    public void setTags(String tags) {
//        this.tags = tags;
//    }
}

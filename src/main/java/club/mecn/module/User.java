package club.mecn.module;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/14.
 *
 * 注册策略，先填邮件和密码，然后发一封邮件，验证，选一个用户名
 */
@JsonIgnoreProperties({"password"})
@Entity
@Table(name="user")
public class User  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Integer userId;



    @Column(name = "u_name")
    private String username;
    @Column(name = "u_password")
    private String password;
    @Column(name = "u_email")
    private String email;

    /**
     * 注册日期
     */
    @Column(name = "u_registerdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    /**
     * 用户头像的路径
     */
    @Column(name = "u_avatarurl")
    private String avatarUrl;

    @Column(name = "u_bgimgurl")
    private String bgImgUrl;

    @Column(name = "u_active_email")
    private boolean activeEmail;
    /**
     *  一对多映射的用户端关系
     *  mappedBy = user 指向另一张表的字段
     *  用户表是关系的维护端
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
    ,fetch = FetchType.LAZY ,mappedBy = "user")
    private Set<UsedName> usedNames = new HashSet<UsedName>();


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Thread> threads = new HashSet<Thread>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY ,mappedBy = "user")
    private Set<Post> posts = new HashSet<Post>();

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY ,mappedBy = "user")
    private Set<Comment> comments = new HashSet<Comment>();

    /**
     * 关注的用户  from this user
     * fromUser
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY,mappedBy = "fromUser")
    private Set<Relationship> followeds = new HashSet<Relationship>();

    /**
     * 粉丝
     */
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY,mappedBy = "toUser")
    private Set<Relationship> followings = new HashSet<Relationship>();


    public User() {

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email, Date registerDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }

    public Set<UsedName> getUsedNames() {
        return usedNames;
    }

    public String getBgImgUrl() {
        return bgImgUrl;
    }

    public void setBgImgUrl(String bgImgUrl) {
        this.bgImgUrl = bgImgUrl;
    }

    public void setUsedNames(Set<UsedName> usedNames) {
        this.usedNames = usedNames;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActiveEmail() {
        return activeEmail;
    }

    public void setActiveEmail(boolean activeEmail) {
        this.activeEmail = activeEmail;
    }

    public Set<Relationship> getFolloweds() {
        return followeds;
    }

    public void setFolloweds(Set<Relationship> followeds) {
        this.followeds = followeds;
    }

    public Set<Relationship> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Relationship> followings) {
        this.followings = followings;
    }

        public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", usedNames=" + usedNames +
                '}';
    }

    /**
     * 添加曾用名的方法，（同时添加外键的值）
     * @param uname 要添加的曾用名对象
     */
    public void addUsedName(UsedName uname)
    {
        uname.setUser(this);
        this.usedNames.add(uname);
    }


    public void followUser(User toUser)
    {

        //两个用户的关系,默认为接受此关系
        Relationship r = new Relationship(this,toUser);
        //this ---> toUser
        r.setFromUser(this);
        r.setToUser(toUser);
        this.followeds.add(r);

        //fans add
        toUser.followings.add(r);


    }
}
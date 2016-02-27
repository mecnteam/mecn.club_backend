package club.mecn.module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/14.
 * 这个表记录用户的曾用名
 */
@Entity
@Table(name = "usedname")
public class UsedName implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="un_id")
    private Integer usedNameId;

    /**
     * 用过的用户名
     */
    @Column(name="un_name")
    private String usedUsername;

    /**
     * 用户创建这个用户名的时间
     */
    @Column(name="un_createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    /**
     * 外键，cascadeType.merge：修改了用户曾用名则这个用户的属性也会改变
     * cascadeType.refresh : 在得到usedname表的最新数据时也需要user表的最新数据
     * 延迟加载
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="un_user_id")
    private User user;

    public UsedName() {}

    public UsedName(String usedUsername) {
        this.usedUsername = usedUsername;
    }

    public UsedName(String usedUsername, Date createDate) {
        this.usedUsername = usedUsername;
        this.createDate = createDate;
    }

    public Integer getUsedNameId() {
        return usedNameId;
    }

    public void setUsedNameId(Integer usedNameId) {
        this.usedNameId = usedNameId;
    }

    public String getUsedUsername() {
        return usedUsername;
    }

    public void setUsedUsername(String usedUsername) {
        this.usedUsername = usedUsername;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

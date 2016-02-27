package club.mecn.module;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/1/29
 * 用户关系表from user1 to user2定义一个关系
 * 关系的内容由主表（1）决定
 * Relationship表示了一个关系定义，用户1 向 用户2 的单向关系
 * 关系具体内容根据User 表定义
 *
 */
@Entity
@Table(name = "relationship")
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer relationId;


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="r_fromuser_id")
    private User fromUser;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="r_touser_id")
    private User toUser;

    @Column(name = "r_accepted")
    private boolean accepted;


    public Relationship(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.accepted = true;
    }

    public Relationship() {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}

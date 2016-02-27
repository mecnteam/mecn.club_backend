package club.mecn.module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/29.
 * 分类表
 */
@Entity
@Table(name = "category")
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cg_id")
    private Integer categoryId;

    @Column(name = "cg_name")
    private String categoryName;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE,CascadeType.REFRESH}
            ,fetch = FetchType.LAZY ,mappedBy = "category")
    private Set<Thread> threads = new HashSet<Thread>();

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }
}

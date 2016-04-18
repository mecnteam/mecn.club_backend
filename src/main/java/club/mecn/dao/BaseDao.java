package club.mecn.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/1/29.
 */
public interface BaseDao {

    /**
     * 保存
     * @param entity
     */
    void save(Object entity);

    /**
     * 更新实体类(传入新的对象)
     * @param entity
     */
    void update(Object newEntity,Serializable entityId);
    /**
     * 
     * @param entityClass
     * @param entityId
     * @param <T>
     */
    <T> void delete(Class<T> entityClass,Serializable entityId);

    /**
     * 按id查找
     * @param entityClass
     * @param entityId
     * @return
     */
    <T> T getById(Class<T> entityClass,Serializable entityId);

    /**
     * 获取全部
     * @param entityClassName
     * @param <T>
     * @return
     */
    <T> List<T> getAll(String entityClassName);
    
    
//
//
//    /**
//     * 分页
//     * @param entityClassName
//     * @param pageNo
//     * @param pageSize
//     * @param <T>
//     * @return
//     */
//    <T> List<T> getAll(String entityClassName,int pageNo,int pageSize);

}

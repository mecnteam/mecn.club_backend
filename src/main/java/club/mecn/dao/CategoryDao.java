package club.mecn.dao;

import club.mecn.module.Category;
import club.mecn.module.User;

public interface CategoryDao extends BaseDao{

	Category getByName(String name);
}

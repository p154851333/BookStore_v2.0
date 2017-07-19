package com.pei.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.pei.po.Category;

/**
 * <p>类名称	CategoryDao </p>
* <p>类描述	分类模块的持久层</p>
* @author	裴健
* @date		2017年3月26日 下午10:09:54
 */
public class CategoryDaoImpl extends HibernateDaoSupport implements  CategoryDao{
	
	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有分类</p>
	* 参数 @return 参数说明
	* 返回类型 List<Category> 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:34:58
	 */
	@SuppressWarnings("all")
	public List<Category> findAll() {
		List<Category> categoryList = (List<Category>) this.getHibernateTemplate().find(
				"from Category");
		if (categoryList != null && categoryList.size() != 0) {
			return categoryList;
		}
		return null;
	}

	public void addCategory(Category category) {
		this.getHibernateTemplate().save(category);
	}

	/**
	 * <p>方法名	delete </p>
	* <p>方法描述	根据cid删除分类</p>
	* 参数 @param cid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午8:12:46
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	@SuppressWarnings("all")
	public Category findCateforyBcid(String cid) {
		List<Category> categoryList = (List<Category>) this.getHibernateTemplate().find(
				"from Category where cid=?",cid);
		if (categoryList != null && categoryList.size() != 0) {
			return categoryList.get(0);
		}
		return null;
	}

	public void updateCategory(Category category) {
		this.getHibernateTemplate().update(category);
	}
	

}

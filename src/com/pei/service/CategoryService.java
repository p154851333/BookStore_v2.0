package com.pei.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pei.dao.CategoryDao;
import com.pei.po.Category;

/**
 * <p>类名称	CategoryService </p>
* <p>类描述	分类的业务层</p>
* @author	裴健
* @date		2017年3月26日 下午10:13:05
 */
@Transactional
public class CategoryService {
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private CategoryDao categoryDao ;
	

	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有分类</p>
	* 参数 @return 参数说明
	* 返回类型 List<Category> 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:35:16
	 */
	public List<Category> findAll() {
		return 	categoryDao.findAll();
	}

	/**
	 * <p>方法名	addCategory </p>
	* <p>方法描述	添加分类</p>
	* 参数 @param category 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午8:12:33
	 */
	public void addCategory(Category category) {
		categoryDao.addCategory(category);		
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
		categoryDao.delete(category);
	}

	public Category findCateforyBcid(String cid) {
		Category category = categoryDao.findCateforyBcid(cid);
		return category;
	}

	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);	
	}
	
}

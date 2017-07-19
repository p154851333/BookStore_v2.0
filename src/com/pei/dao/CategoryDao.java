package com.pei.dao;

import java.util.List;
import com.pei.po.Category;

public interface CategoryDao {
	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有分类</p>
	* 参数 @return 参数说明
	* 返回类型 List<Category> 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:34:58
	 */
	public List<Category> findAll() ;

	//添加分类
	public void addCategory(Category category) ;

	/**
	 * <p>方法名	delete </p>
	* <p>方法描述	根据cid删除分类</p>
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午8:12:46
	 */
	public void delete(Category category) ;

	//根据id查询分类
	public Category findCateforyBcid(String cid) ;

	//更新分类
	public void updateCategory(Category category) ;
}

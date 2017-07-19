package com.pei.dao;

import java.util.List;

import com.pei.po.Book;
import com.pei.po.Category;

public interface BookDao {

	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有图书</p>
	* 参数 @return 参数说明
	* 返回类型 List<Book> 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:53:53
	 */
	public List<Book> findAll();

	/**
	 * <p>方法名	findBookByCategory </p>
	* <p>方法描述	根据分类cid，查询此分类的所有图书</p>
	* 参数 @param cid
	* 参数 @return 参数说明
	* 返回类型 List<Book> 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午9:22:46
	 */
	public List<Book> findBookByCategory(String cid) ;

	/**
	 * <p>方法名	findBookById </p>
	* <p>方法描述	根据图书bid，查询图书</p>
	* 参数 @param bid
	* 参数 @return 参数说明
	* 返回类型 Book 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午9:23:32
	 */
	public Book findBookById(String bid) ;

	/**
	 * <p>方法名	addBook </p>
	* <p>方法描述	添加图书</p>
	* 参数 @param book 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午6:31:43
	 */
	public void addBook(Book book) ;

	/**
	 * <p>方法名	delete </p>
	* <p>方法描述	根据bid修改图书的显示方式（删除）</p>
	* 参数 @param bid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午8:28:31
	 */
	public void delete(String bid) ;
	
	
	public void updateBook(Book book) ;

	public int countBook();

	public List<Book> pageFindAll(int begin, int pageSize);

	public List<Book> pageFindBookByCategory(String cid, int begin, int pageSize);

	public int countBookByCategory(String cid);
}

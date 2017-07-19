package com.pei.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pei.dao.BookDao;
import com.pei.po.Book;
import com.pei.po.PageBean;


/**
 * <p>类名称	BookService </p>
* <p>类描述	图书类的业务成</p>
* @author	裴健
* @date		2017年3月26日 下午10:54:45
 */
@Transactional
public class BookService {
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	private BookDao bookDao ;
	
	
	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有图书</p>
	* 参数 @return 参数说明
	* 返回类型 List<Book> 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:55:58
	 */
	public List<Book> findAll(){
		return bookDao.findAll();
	}

	public List<Book> findBookByCategory(String cid) {
		return bookDao.findBookByCategory(cid);
	}

	public Book findBookById(String bid) {
		return bookDao.findBookById(bid);
	}

	/**
	 * <p>方法名	addBook </p>
	* <p>方法描述	添加图书</p>
	* 参数 @param book 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午6:26:17
	 */
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	/**
	 * <p>方法名	delete </p>
	* <p>方法描述	修改指定bid的显示状态（删除）</p>
	* 参数 @param bid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午8:27:39
	 */
	public void delete(String bid) {
		bookDao.delete(bid);
	}

	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}
	
	/**
	 * <p>方法名	countBook </p>
	* <p>方法描述	返回图书的总数</p>
	* 参数 @return 参数说明
	* 返回类型 int 返回类型
	* @author	裴健
	* @date		2017年5月10日 下午7:01:27
	 */
	public int countBook(){
		return bookDao.countBook();
	}
	/**
	 * <p>方法名	countBook </p>
	 * <p>方法描述	根据分类返回图书的总数</p>
	 * 参数 @return 参数说明
	 * 返回类型 int 返回类型
	 * @author	裴健
	 * @date		2017年5月10日 下午7:01:27
	 */
	public int countBookByCategory(String cid){
		return bookDao.countBookByCategory(cid);
	}

	/**
	 * <p>方法名	pageFindAll </p>
	* <p>方法描述	分页查询</p>
	* 参数 @param currentPage 当前页
	* 参数 @return 参数说明
	* 返回类型 PageBean<Book> 返回类型
	* @author	裴健
	* @date		2017年5月10日 下午3:52:42
	 */
	public PageBean<Book> pageFindAll(int currentPage,String url) {
		PageBean<Book> pageBean = new PageBean<Book>();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//设置总记录数
		pageBean.setTotalCount(this.countBook());
		//设置url
		pageBean.setUrl(url);
		//每页显示记录数
		int pageSize = 8;
		pageBean.setPageSize(pageSize);
		
		//开始查询位置
		int begin = (currentPage-1)*pageSize;
		//查询出来的list集合
		pageBean.setBeanList(bookDao.pageFindAll(begin,pageSize));
		return pageBean;
	}

	public PageBean<Book> pageFindBookByCategory(String cid, int currentPage,
			String url) {
		PageBean<Book> pageBean = new PageBean<Book>();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//设置总记录数
		pageBean.setTotalCount(this.countBookByCategory(cid));
		//设置url
		pageBean.setUrl(url);
		//每页显示记录数
		int pageSize = 8;
		pageBean.setPageSize(pageSize);
		
		//开始查询位置
		int begin = (currentPage-1)*pageSize;
		//查询出来的list集合
		pageBean.setBeanList(bookDao.pageFindBookByCategory(cid,begin,pageSize));
		return pageBean;
	}
	
}

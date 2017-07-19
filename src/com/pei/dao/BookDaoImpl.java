package com.pei.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.pei.po.Book;
import com.pei.po.Category;

import cn.itcast.jdbc.TxQueryRunner;

/**
 * <p>类名称	BookDao </p>
* <p>类描述	图书的持久层</p>
* @author	裴健
* @date		2017年3月26日 下午10:47:25
 */
public class BookDaoImpl extends HibernateDaoSupport implements BookDao{

	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有图书</p>
	* 参数 @return 参数说明
	* 返回类型 List<Book> 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:53:53
	 */
	@SuppressWarnings("all")
	public List<Book> findAll(){
		List<Book> bookList = (List<Book>) this.getHibernateTemplate().find(
				"from Book");
		if (bookList != null && bookList.size() != 0) {
			return bookList;
		}
		return null;
	}

	/**
	 * <p>方法名	findBookByCategory </p>
	* <p>方法描述	根据分类cid，查询此分类的所有图书</p>
	* 参数 @param cid
	* 参数 @return 参数说明
	* 返回类型 List<Book> 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午9:22:46
	 */
	@SuppressWarnings("all")
	public List<Book> findBookByCategory(String cid) {
		Category category = this.getHibernateTemplate().get(Category.class, cid);
		List<Book> bookList = (List<Book>) this.getHibernateTemplate().find(
				"from Book where category = ?",category);
		if (bookList != null && bookList.size() != 0) {
			return bookList;
		}
		return null;
		
	}

	/**
	 * <p>方法名	findBookById </p>
	* <p>方法描述	根据图书bid，查询图书</p>
	* 参数 @param bid
	* 参数 @return 参数说明
	* 返回类型 Book 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午9:23:32
	 */
	public Book findBookById(String bid) {
		return this.getHibernateTemplate().get(Book.class, bid);
	}

	/**
	 * <p>方法名	loaderBook </p>
	* <p>方法描述	加载图书的所属分类</p>
	* 参数 @param book
	* 参数 @param bid
	* 参数 @return 参数说明
	* 返回类型 Book 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午9:24:53
	 */
	/*private Book loaderBook(Book book,String bid) {
		try {
			String sql = "SELECT cid FROM book where bid =?";
			String cid = (String) qr.query(sql, new ScalarHandler() ,bid);
			CategoryDao categoryDao = new CategoryDaoImpl();
			Category category = categoryDao.findCateforyBcid(cid);
			book.setCategory(category);
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}*/

	/**
	 * <p>方法名	addBook </p>
	* <p>方法描述	添加图书</p>
	* 参数 @param book 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午6:31:43
	 */
	public void addBook(Book book) {
		this.getHibernateTemplate().save(book);
	}

	/**
	 * <p>方法名	delete </p>
	* <p>方法描述	根据bid修改图书的显示方式（删除）</p>
	* 参数 @param bid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午8:28:31
	 */
	public void delete(String bid) {
		Book book = this.getHibernateTemplate().get(Book.class, bid);
		book.setDel(true);
		this.getHibernateTemplate().update(book);
	}
	
	public void updateBook(Book book) {
		 this.getHibernateTemplate().update(book);
	}

	@SuppressWarnings("all")
	@Override
	public int countBook() {
		//调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Book");
		//从list中把值得到
		if(list != null && list.size()!=0) {
			Object obj = list.get(0);
			//变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}
	@SuppressWarnings("all")
	@Override
	public int countBookByCategory(String cid) {
		//调用hibernateTemplate里面的find方法实现
		List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Book where  cid = '"+cid+"'");
		//从list中把值得到
		if(list != null && list.size()!=0) {
			Object obj = list.get(0);
			//变成int类型
			Long lobj = (Long) obj;
			int count = lobj.intValue();
			return count;
		}
		return 0;
	}
	

	@SuppressWarnings("all")
	@Override
	public List<Book> pageFindAll(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Book.class);
		List<Book> bookList = (List<Book>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		if(bookList != null && bookList.size()!=0) {
			return bookList;
		}
		return null;
	}

	@SuppressWarnings("all")
	@Override
	public List<Book> pageFindBookByCategory(String cid, int begin, int pageSize) {
		Category category = this.getHibernateTemplate().get(Category.class, cid);
		//第一种 使用hibernate底层代码实现（了解）
		//得到sessionFactory
		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
		//得到session对象
		Session session = sessionFactory.getCurrentSession();
		//设置分页信息
		Query query = session.createQuery("from Book where category = '"+category.getCid()+"'");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Book> list = query.list();
		if(list != null && list.size()!=0) {
			return list;
		}
		return null;
	}
}

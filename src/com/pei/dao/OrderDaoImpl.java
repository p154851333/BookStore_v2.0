package com.pei.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.pei.po.Book;
import com.pei.po.Category;
import com.pei.po.User;
import com.pei.po.order.Order;
import com.pei.po.order.OrderItem;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
/**
 * <p>类名称	OrderDao </p>
* <p>类描述	订单的持久层	</p>
* @author	裴健
* @date		2017年3月28日 下午2:14:43
 */
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	/**
	 * <p>方法名	addOrder </p>
	* <p>方法描述	向数据库中添加订单</p>
	* 参数 @param order 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午2:32:18
	 */
	public void addOrder(Order order){
		this.getHibernateTemplate().save(order);
	}
	
	/**
	 * <p>方法名	addOrderItem </p>
	* <p>方法描述	添加订单条目。
	* 				条目可以一次添加很多，所以使用批处理添加
	* </p>
	* 参数 @param orderItemList 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午2:48:14
	 */
	public void addOrderItem(List<OrderItem> orderItemList){
		for (OrderItem orderItem : orderItemList) {
			this.getHibernateTemplate().save(orderItem);
		}
	}

	/**
	 * <p>方法名	findOrderByUid </p>
	* <p>方法描述	查询用户订单</p>
	* 参数 @param uid
	* 参数 @return 参数说明
	* 返回类型 List<Order> 返回类型
	* @author	裴健
	 * @date		2017年3月28日 下午6:57:21
	 */
	@SuppressWarnings("all")
	public List<Order> findOrderByUid(String uid) {
		List<Order> orderList = (List<Order>) this.getHibernateTemplate().
				find("from Order where uid =?",uid);
		if (orderList != null && orderList.size() != 0) {
			return orderList;
		}
		return null;
	}
		
	/**
	 * <p>方法名	findOrderByOid </p>
	* <p>方法描述	根据订单oid查询订单</p>
	* 参数 @param oid
	* 参数 @return 参数说明
	* 返回类型 Order 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午10:58:43
	 */
	public  Order findOrderByOid(String oid) {
		Order order = this.getHibernateTemplate().get(Order.class, oid);
		if (order != null) {
			return order;
		}
		return null;
	}

	/**
	 * <p>方法名	deleteOrder </p>
	* <p>方法描述	根据oid删除订单</p>
	* 参数 @param oid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健		
	* @date		2017年3月28日 下午10:11:20
	 */
	public void deleteOrder(String oid) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(Order.class, oid));
	}
	

	/**
	 * <p>方法名	findOrderStateByOid </p>
	* <p>方法描述	根据oid查询订单的当前状态</p>
	* 参数 @param oid
	* 参数 @return 参数说明
	* 返回类型 int 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午2:37:24
	 */
	public int findOrderStateByOid(String oid) {
		Number state = (Number) this.getHibernateTemplate().find("SELECT state FROM orders WHERE oid = ?", oid);
		return state.intValue();
	}

	/**
	 * <p>方法名	updateOrderStateByOid </p>
	* <p>方法描述	</p>
	* 参数 @param oid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午2:38:03
	 */
	public void updateOrderStateByOid(String oid ,int state) {
		Order order = this.getHibernateTemplate().get(Order.class, oid);
		order.setState(state);
		this.getHibernateTemplate().update(order);
	}

	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有订单</p>
	* 参数 @return 参数说明
	* 返回类型 List<Order> 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午9:53:10
	 */
	@SuppressWarnings("all")
	public List<Order> findAll() {
		List<Order> orderList = (List<Order>) this.getHibernateTemplate().find("FROM Order");
		if (orderList != null && orderList.size() != 0) {
			return orderList;
		}
		return null;
	}

	/**
	 * <p>方法名	findOrderByState </p>
	* <p>方法描述	根据订单状态查询订单</p>
	* 参数 @param i
	* 参数 @return 参数说明
	* 返回类型 List<Order> 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午10:38:37
	 */
	@SuppressWarnings("all")
	public List<Order> findOrderByState(int i) {
		List<Order> orderList = (List<Order>) this.getHibernateTemplate().find("FROM Order WHERE state = '"+i+"'");
		if (orderList != null && orderList.size() != 0) {
			return orderList;
		}
		return null;
	}

	@Override
	public void deleteOrderItem(String oid) {
		// TODO Auto-generated method stub
		
	}

}

package com.pei.dao;

import java.util.List;

import com.pei.po.order.Order;
import com.pei.po.order.OrderItem;

public interface OrderDao {

	/**
	 * <p>方法名	addOrder </p>
	* <p>方法描述	向数据库中添加订单</p>
	* 参数 @param order 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午2:32:18
	 */
	public void addOrder(Order order);
	
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
	public void addOrderItem(List<OrderItem> orderItemList);

	/**
	 * <p>方法名	findOrderByUid </p>
	* <p>方法描述	查询用户订单</p>
	* 参数 @param uid
	* 参数 @return 参数说明
	* 返回类型 List<Order> 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午6:57:21
	 */
	public List<Order> findOrderByUid(String uid);

	/**
	 * <p>方法名	findOrderByOid </p>
	* <p>方法描述	根据订单oid查询订单</p>
	* 参数 @param oid
	* 参数 @return 参数说明
	* 返回类型 Order 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午10:58:43
	 */
	public  Order findOrderByOid(String oid);

	/**
	 * <p>方法名	deleteOrder </p>
	* <p>方法描述	根据oid删除订单</p>
	* 参数 @param oid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健		
	* @date		2017年3月28日 下午10:11:20
	 */
	public void deleteOrder(String oid);
	
	/**
	 * <p>方法名	deleteOrderItem </p>
	* <p>方法描述	根据oid删除订单明细</p>
	* 参数 @param oid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午10:19:55
	 */
	public void deleteOrderItem(String oid);

	/**
	 * <p>方法名	findOrderStateByOid </p>
	* <p>方法描述	根据oid查询订单的当前状态</p>
	* 参数 @param oid
	* 参数 @return 参数说明
	* 返回类型 int 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午2:37:24
	 */
	public int findOrderStateByOid(String oid);

	/**
	 * <p>方法名	updateOrderStateByOid </p>
	* <p>方法描述	</p>
	* 参数 @param oid 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午2:38:03
	 */
	public void updateOrderStateByOid(String oid ,int state);

	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有订单</p>
	* 参数 @return 参数说明
	* 返回类型 List<Order> 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午9:53:10
	 */
	public List<Order> findAll();

	/**
	 * <p>方法名	findOrderByState </p>
	* <p>方法描述	根据订单状态查询订单</p>
	* 参数 @param i
	* 参数 @return 参数说明
	* 返回类型 List<Order> 返回类型
	* @author	裴健
	* @date		2017年3月30日 下午10:38:37
	 */
	public List<Order> findOrderByState(int i);
}

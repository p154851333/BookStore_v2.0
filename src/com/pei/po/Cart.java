package com.pei.po;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>类名称	Cart </p>
* <p>类描述	购物车</p>
* @author	裴健
* @date		2017年3月27日 下午1:39:09
 */
public class Cart {

	//购物车内只有商品条目。条目用map封装
	private Map<String,CartItme> map = new LinkedHashMap<String,CartItme>();
	
	@Override
	public String toString() {
		return "Cart [map=" + map + "]";
	}
	/*
	 * 提供多种方法。
	 * 1、添加条目。（）
	 * 2、按照商品bid删除条目
	 * 3、清空所有条目。
	 * 4、计算所有条目的合计。
	 * 5、得到所有的条目
	 */
	
	//计算购物车合计
	public double getTotal(){
		/*
		 *购物车合计就是所有的条目的小计相加。
		 *这里继续使用大数据浮点数封装 
		 */
		//创建合计
		BigDecimal total = new BigDecimal("0");
		//得到所有的条目
		for (CartItme cartItme : map.values()) {
			//封装小计
			BigDecimal subtotal = new BigDecimal(cartItme.getSubtotal()+"");
			
			total = total.add(subtotal);
		}
		
		return total.doubleValue();
	}
	//添加条目
	public void add(CartItme cartItme){
		/*
		 * 1、分两种情况，当购物车中已经有该商品，则只要在条目上加上数量
		 * 2、当购物车没有该商品，则直接添加到购物车
		 */
		if (map.containsKey(cartItme.getBook().getBid())) {
			//当map中有该商品时
			//根据bid得到购物车中原有的商品条目
			CartItme _cartItme = map.get(cartItme.getBook().getBid());
			//原商品数量+新添加的商品数量，并设置回去
			_cartItme.setCount(_cartItme.getCount()+cartItme.getCount());
			//更新map中的数据
			map.put(cartItme.getBook().getBid(), _cartItme);
		} else {
			//当map中没有对应的商品，直接添加进去就行了
			map.put(cartItme.getBook().getBid(), cartItme);
		}
		
	}
	//清空购物车
	public void clean(){
		map.clear();
	}
	//删除指定条目
	public void delete(String bid){
		map.remove(bid);
	}
	//得到所有条目
	public Collection<CartItme> getCartItmes(){
		return map.values();
	}
}

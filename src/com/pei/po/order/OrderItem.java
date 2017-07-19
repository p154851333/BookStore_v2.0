package com.pei.po.order;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.pei.po.Book;


/**
 * <p>类名称	OrderItem </p>
* <p>类描述	订单明细的领域对象</p>
* @author	裴健
* @date		2017年3月28日 下午2:08:39
 */
@Entity
public class OrderItem {

	private String iid;//订单条目的id
	private int count ;//订单条目商品的数量
	private double subtotal;//订单条目的小计
	private Order order;//订单条目所属的订单
	private Book book;//订单条目的商品
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal="
				+ subtotal + ", order=" + order + ", book=" + book + "]";
	}
	@Id
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH})
	@JoinColumn(name="oid")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@ManyToOne(cascade={CascadeType.REFRESH},optional=true)
	@JoinColumn(name="bid")
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public OrderItem() {
		super();
	}
	public OrderItem(String iid, int count, double subtotal, Order order,
			Book book) {
		super();
		this.iid = iid;
		this.count = count;
		this.subtotal = subtotal;
		this.order = order;
		this.book = book;
	}
	
	
	
	
}

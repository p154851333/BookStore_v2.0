package com.pei.po;

import java.math.BigDecimal;

/**
 * <p>类名称	CartItme </p>
* <p>类描述	购物车内的条目</p>
* @author	裴健
* @date		2017年3月27日 下午1:30:35
 */
public class CartItme {

	
	private Book book;//商品
	private int count;//数量
	
	//因为一类商品有多个数量，小计是通过计算所得
	/*public double subtotal(){
		return book.getPrice()*count;
	}*/
	//因为二进制精度丢失问题，这里采用
	/**
	 * <p>方法名	getSubtotal </p>
	* <p>方法描述	计算商品条目小计</p>
	* 参数 @return 参数说明
	* 返回类型 double 返回类型
	* @author	裴健
	* @date		2017年3月27日 下午2:09:47
	 */
	public double getSubtotal(){
		//将图书的单价封装成大数据浮点数
		BigDecimal bookPrice = BigDecimal.valueOf(book.getPrice());
		//将图书数量封装成大数据浮点数
		BigDecimal _count = BigDecimal.valueOf(count);
		//将两个数相乘，并以double类型返回。
		return bookPrice.multiply(_count).doubleValue();
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartItme [book=" + book + ", count=" + count + "]";
	}

	public CartItme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItme(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	
}

package com.pei.po;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.engine.internal.Cascade;

/**
 * <p>类名称	Book </p>
* <p>类描述	图书的领域对象</p>
* @author	裴健
* @date		2017年3月26日 下午10:50:52
 */
@Entity
public class Book {

	private String bid;
	private String bname;
	private double price;
	private String author;
	private String image;
	private Category category;
	private boolean del;
	
	@Id
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)	//级联增删改查
	@JoinColumn(name="cid")					//外键列名	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price
				+ ", author=" + author + ", image=" + image + ", category="
				+ category + "]";
	}
	
	public Book() {
	}
	public Book(String bid, String bname, double price, String author,
			String image, Category category) {
		this.bid = bid;
		this.bname = bname;
		this.price = price;
		this.author = author;
		this.image = image;
		this.category = category;
	}
	
	
}

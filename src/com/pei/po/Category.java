package com.pei.po;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>类名称	Category </p>
* <p>类描述	分类的领域对象</p>
* @author	裴健
* @date		2017年3月26日 下午10:10:56
 */
@Entity
public class Category {
	
	private String cid;
	private String cname;
	
	@Id
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	

}

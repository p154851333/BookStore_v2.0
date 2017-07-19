package com.pei.po;

import java.util.List;

/**
 * <p>类名称	PageBean </p>
* <p>类描述	分页时，保存信息</p>
* @author	裴健
* @date		2017年3月14日 下午7:18:22
 */
public class PageBean<T>{

	private int currentPage ;//当前页码currentPage
//	private int totalPage ;//总页码数totalpage.总页数是可以通过计算得到。不需要设置
	private int totalCount ;//总记录数（sql查询出来的条数），totalcount
	private int pageSize ;//每一页的记录数。pageSize
	
	private List<T> beanList ;//查询出来的当前页的数据集合。
	private String  url ;//携带分页条件
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		// 通过总记录数和每页记录数来计算总页数
		int totalPage = totalCount / pageSize;
		return totalCount%pageSize==0 ? totalPage : totalPage+1;
	}


	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(int currentPage, int totalCount, int pageSize, List<T> beanList, String url) {
		super();
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.beanList = beanList;
		this.url = url;
	}
	@Override
	public String toString() {
		return "PageBean [pc=" + currentPage  + ", totalCount=" + totalCount + ", pageSize="
				+ pageSize + ", beanList=" + beanList + ", url=" + url + "]";
	}

	
	

	
}

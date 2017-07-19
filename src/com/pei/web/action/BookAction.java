package com.pei.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pei.po.Book;
import com.pei.po.PageBean;
import com.pei.service.BookService;

public class BookAction extends ActionSupport {
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private BookService bookService ;
	

	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有图书</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午11:09:31
	 */
	public String findAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (bookService.findAll()==null||bookService.findAll().size()==0) {
			request.setAttribute("msg", "暂无图书！");
			return "f:/jsps/msg.jsp";
		}else {
			List<Book> bookList = bookService.findAll();
			request.setAttribute("bookList",bookList);
			return "f:/jsps/book/list.jsp";
		}
	}
	
	
	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述：	得到页面分页的当前页码，定义页面大小，去数据库查询，
	* 				返回一页的客户信息
	* </p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月14日 下午7:47:21
	 */
	public String pageFindAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//得到页面的分页的当前页码
		int currentPage = getCurrentPage(request);
		//得到url
		String url = getUrl(request);
		//调用service层的查询所有客户方法
		PageBean<Book> pageBean = bookService.pageFindAll(currentPage,url);
		if (pageBean.getBeanList()==null||pageBean.getBeanList().size()==0) {
			request.setAttribute("msg", "暂无图书！");
			return "f:/jsps/msg.jsp";
		}
		//将查询到的数据添加到Request域中
		request.setAttribute("pageBean", pageBean);
		return "f:/jsps/book/paginglist.jsp";
	}
	/**
	 * <p>方法名	getUrl </p>
	* <p>方法描述	返回请求的参数String对象</p>
	* 参数 @param request
	* 参数 @return 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月15日 下午9:26:07
	 */
	private  String  getUrl(HttpServletRequest request){
		//需要将url的参数部分获取之后，返回到分页信息对象中。
		String contextPath = request.getContextPath();//获取项目名
		String servletPath = request.getServletPath();//获取servletPath，即/CustomerServlet
		String queryString = request.getQueryString();//获取问号之后的参数部份
		if (queryString==null) {
			return contextPath+servletPath+"?";
		}else {
			if (queryString.contains("&currentPage=")) {
				//得到&pc的位置。
				int indexOf = queryString.indexOf("&currentPage=");
				//需要前部分。截取
				queryString=queryString.substring(0,indexOf);
			}
			return contextPath+servletPath+"?"+queryString;
		}
	}
	/**
	 * <p>方法名	getCurrentPage </p>
	* <p>方法描述	得到页面传来的CurrentPage(当前页码)</p>
	* 参数 @param request
	* 参数 @return 参数说明
	* 返回类型 int 返回类型
	* @author	裴健
	* @date		2017年3月14日 下午7:37:42
	 */
	private	int getCurrentPage(HttpServletRequest request){
		String currentPage = request.getParameter("currentPage");
		if (currentPage==null||currentPage.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(currentPage);
	}
	
	
	/**
	 * <p>方法名	findBookByCategory </p>
	* <p>方法描述	按照分类cid查询图书</p>
	* 参数 @return 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午11:09:46
	 */
	public String findBookByCategory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cid = request.getParameter("cid");
		if (bookService.findBookByCategory(cid)==null||bookService.findBookByCategory(cid).size()==0) {
			request.setAttribute("msg", "暂无图书！");
			return "f:/jsps/msg.jsp";
		}else {
			List<Book> bookList = bookService.findBookByCategory(cid);
			request.setAttribute("bookList", bookList);
			return "f:/jsps/book/list.jsp";
		}
	}
	/**
	 * <p>方法名	pageFindBookByCategory </p>
	* <p>方法描述	按照图书分类分页查询图书</p>
	* 参数 @return 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年5月10日 下午6:28:21
	 */
	public String pageFindBookByCategory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//得到页面的分页的当前页码
		int currentPage = getCurrentPage(request);
		//得到url
		String url = getUrl(request);
		String cid = request.getParameter("cid");
		PageBean<Book> pageBean = bookService.pageFindBookByCategory(cid,currentPage,url);
		if (pageBean.getBeanList()==null||pageBean.getBeanList().size()==0) {
			request.setAttribute("msg", "暂无图书！");
			return "f:/jsps/msg.jsp";
		}
		//将查询到的数据添加到Request域中
		request.setAttribute("pageBean", pageBean);
		return "f:/jsps/book/paginglist.jsp";
	}
	
	/**
	 * <p>方法名	findBookById </p>
	* <p>方法描述	通过图书bid查询图书</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午11:21:50
	 */
	public String findBookById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String bid = request.getParameter("bid");
		if (bookService.findBookById(bid)==null) {
			request.setAttribute("msg", "信息有误！");
			return "f:/jsps/msg.jsp";
		}else {
			Book book = bookService.findBookById(bid);
			request.setAttribute("book", book);
			return "f:/jsps/book/desc.jsp";
		}
		
	}

}

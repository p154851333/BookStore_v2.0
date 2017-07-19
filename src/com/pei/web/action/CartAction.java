package com.pei.web.action;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pei.po.Book;
import com.pei.po.Cart;
import com.pei.po.CartItme;
import com.pei.service.BookService;

public class CartAction extends ActionSupport {
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private static final long serialVersionUID = 1L;
	private BookService bookService;
	
	
	/**
	 * 添加购物车条目
	 */
	public String addCartItme(){
		System.out.println("addCartItme");
		HttpServletRequest request = ServletActionContext.getRequest();
		//页面点击添加到购物车传来的数据(商品bid和购买数量)。
		BigInteger count =new BigInteger(request.getParameter("count"));
//		int count = Integer.parseInt(request.getParameter("count"));
		String bid = request.getParameter("bid");
		//通过bid查询得到对应的book对象
		Book book = bookService.findBookById(bid);
		//创建条目(并给其商品和数量)
		CartItme cartItme = new CartItme(book, count.intValue());
		//从session中得到购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//给购物车添加条目
		cart.add(cartItme);
		//再将购物车保存到session中
		request.getSession().setAttribute("cart", cart);
		//转发到购物车页面
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * <p>方法名	cleanCartItme </p>
	* <p>方法描述	清空购物车内容</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月27日 下午7:16:19
	 */
	public String cleanCartItme() {
		HttpServletRequest request = ServletActionContext.getRequest();
		/*
		 * 1.从session中得到购物车
		 * 2.清空条目
		 * 3.保存到session中
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clean();
		request.getSession().setAttribute("cart", cart);
		//转发到购物车页面
		return "f:/jsps/cart/list.jsp";
	}
		
	/**
	 * <p>方法名	deleteCartItmeCartItme </p>
	* <p>方法描述	删除单个条目</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月27日 下午7:16:34
	 */
	public String deleteCartItme(){
		HttpServletRequest request = ServletActionContext.getRequest();
		/*
		 * 1.从session中得到购物车
		 * 2.得到页面传来的单个条目的bid
		 * 3.删除条目
		 * 4.保存到session中
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		request.getSession().setAttribute("cart", cart);
		//转发到购物车页面
		return "f:/jsps/cart/list.jsp";
	}
	
}

package com.pei.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.pei.po.User;
/**
 * 未登陆游客限制访问购物车和订单
 */
@WebFilter(
		urlPatterns = { 
				"/jsps/cart/*", 
				"/jsps/order/*"
		}, 
		servletNames = { 
				"com.monkey.bookstore.web.action.CartAction", 
				"com.monkey.bookstore.web.action.OrderAction"
		})
public class LoginFilter implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 从session中获取用户信息
		 * 	获取不到，则说明未登录，保存错误信息到登录页面。
		 * 	获取到，放行
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		if (user!=null) {
			chain.doFilter(request, response);
		}else {
			req.setAttribute("msg", "*您还未登录，登录后方可享受会员待遇！");
			req.getRequestDispatcher("jsps/user/login.jsp").forward(req, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

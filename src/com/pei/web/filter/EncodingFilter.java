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

/**
 * 全站乱码问题
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//增强requset的getParameter方法
		EncodingRequest erq = new EncodingRequest((HttpServletRequest) request);
		//响应乱码问题
		response.setContentType("text/html;charset=utf-8");
		//判断请求方式
		if (erq.getMethod().equals("GET")) {//get请求方式
			//get请求乱码问题
			chain.doFilter(erq, response);
		}else if (erq.getMethod().equals("POST")) {
			//post请求乱码问题
			request.setCharacterEncoding("utf-8");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

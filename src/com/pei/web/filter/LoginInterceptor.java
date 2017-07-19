package com.pei.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.pei.po.User;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		if (user!=null) {
			return invocation.invoke();
		}else {
			req.setAttribute("msg", "*您还未登录，登录后方可享受会员待遇！");
			return "loginerr";
		}
	}

}

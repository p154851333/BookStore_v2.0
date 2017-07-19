package com.pei.web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>类名称	EncodingRequest </p>
* <p>类描述	装饰者模式：
* 			增强request的getParameter方法，使其编码格式为utf-8
* </p>
* @author	裴健
* @date		2017年3月22日 下午1:53:06
 */
public class EncodingRequest extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	@Override
	public String getParameter(String name) {
		String value = request.getParameter(name);
		try {
			value=new String(value.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return value;
	}
	
}

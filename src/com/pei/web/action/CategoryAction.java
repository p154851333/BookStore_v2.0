package com.pei.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pei.po.Category;
import com.pei.service.CategoryService;


public class CategoryAction extends ActionSupport{
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private CategoryService categoryService ;
	
	
	/**
	 * <p>方法名	findAll </p>
	* <p>方法描述	查询所有的分类</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午10:15:20
	 */
	public  String findAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Category> categoryList = null;
		if (categoryService.findAll() == null || categoryService.findAll().size() == 0) {
			categoryList = new ArrayList<Category>();
			Category category = new Category("0", "暂无分类，敬请期待！");
			categoryList.add(category);
			request.setAttribute("categoryList", categoryList);
			return "f_jsps_left_jsp";
		}else {
			categoryList = categoryService.findAll();
			request.setAttribute("categoryList", categoryList);
			return "f_jsps_left_jsp";
		}
	}

}

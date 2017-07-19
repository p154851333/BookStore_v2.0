package com.pei.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.vcode.utils.VerifyCode;

import com.opensymphony.xwork2.ActionSupport;
import com.pei.po.Cart;
import com.pei.po.User;
import com.pei.service.UserException;
import com.pei.service.UserService;

public class UserAction extends ActionSupport {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * <p>方法名	regist </p>
	* <p>方法描述	验证登录功能
	* 				从页面得到数据，输入验证通过后，调用业务层方法
	* </p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	 * @throws IOException 
	* @date		2017年3月25日 下午9:38:43
	 */
	public String regist() throws IOException{
		/*
		 * 1、封装表单传来的数据
		 * 2、补全表单中没有输入的数据（uid,code）
		 * 3、对表单的输入验证。
		 * 		验证不通过，保存错误信息和表单信息到Request中，用来回显。
		 * 4、调用业务层的regist方法。
		 * 		有异常，保存异常信息和表单信息到Request中，用来回显。
		 * 5、注册完成之后，需要发送邮件用来让客户激活账户。
		 * 6、保存成功信息到Request中。
		 * 7、转发到msg.jsp
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		//封装表单数据得到form
		User formUser = CommonUtils.toBean(request.getParameterMap(), User.class);
		//补全信息
		formUser.setUid(CommonUtils.uuid());
		formUser.setCode(CommonUtils.uuid()+CommonUtils.uuid());
		/*
		 * 3、输入验证
		 * 		1.创建map,存放错误信息
		 * 		2.从formUser中得到表单数据进行验证
		 */
		//1.创建map,存放错误信息
		Map<String,String> errors = new HashMap<String,String>();
		//2.从formUser中得到表单数据进行验证
		//验证用户名
		String username = formUser.getUsername();
		if (username==null||username.trim().isEmpty()) {//空字符验证
			//当用户名为空，或者空字符时,创建错误信息
			errors.put("username", "*用户名不能为空！");
		}else if ((username.length()<3)||(username.length()>10)) {//用户名长度验证
			errors.put("username", "*用户名长度必须在3~10之间！");
		}
		//验证密码(正则："(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}")
		String password = formUser.getPassword();
		if (password==null||password.trim().isEmpty()) {//空字符验证
			//当密码为空，或者空字符，创建错误信息
			errors.put("password","*密码不能为空！");
		}else if (!password.matches("(?=.*[0-9])(?=.*[a-zA-Z]).{5,17}")) {
			//密码复杂度
			errors.put("password", "*密码中需要包含字母和数字！");
		}else if ((password.length()<6)||(password.length()>16)) {
			//密码长度
			errors.put("password", "*密码长度必须在6~16之间！");
		}
		//验证邮箱
		String email=formUser.getEmail();
		if (email==null||email.trim().isEmpty()) {
			errors.put("email", "*邮箱不能为空！");
		}else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "*请输入正确的Email地址！");
		}
		//验证码
		String verifycode=request.getParameter("verifycode");
		if (verifycode==null||verifycode.trim().isEmpty()) {
			errors.put("verifycode", "*验证码不能为空！");
		}else if (!verifycode.equalsIgnoreCase((String) request.getSession().getAttribute("session_verifycode"))) {
			errors.put("verifycode", "*验证码错误！");
		}
		//判断输入验证是否有值
		if (errors.size()>0) {
			//表单信息有错(验证不通过，保存错误信息和表单信息到Request中，用来回显。)
			request.setAttribute("errors", errors);
			request.setAttribute("formUser", formUser);
			//转发都jsp
			return "registerr";
		}
		//调用业务成的regist方法。
		try {
			//注册成功
			userService.regist(formUser);
		} catch (UserException e) {
			//注册验证（有异常，保存异常信息和表单信息到Request中，用来回显。）
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("formUser", formUser);
			//转发都jsp
			return "registerr";
		}
		//注册成功之后
		/*
		 * 发送激活邮件
		 */
		//得到邮件的配置文件
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host = props.getProperty("host");//得到服务器主机
		String uname = props.getProperty("uname");//得到用户名
		String pwd = props.getProperty("pwd");//得到密码
		String from = props.getProperty("from");//得到发件人
		String subject = props.getProperty("subject");//得到主题
		String content = props.getProperty("content");//得到邮件内容
		content= MessageFormat.format(content, formUser.getCode());//给占位符添加激活码
		Session mailSession = MailUtils.createSession(host, uname, pwd);
		Mail mail = new Mail(from, email, subject, content);
		try {
			MailUtils.send(mailSession, mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//保存成功信息转发到成功页面
		request.setAttribute("msg", "恭喜您，成功注册DarlingMonkey,您可以登录邮箱激活账户！");
		return "registok";
		
		
	}
	
	/**
	 * <p>方法名	active </p>
	* <p>方法描述	激活码激活账户功能</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午3:52:19
	 */
	public String active(){
		//System.out.println("激活！");
		//得到页面传递来的激活码
		HttpServletRequest request = ServletActionContext.getRequest();
		String code = request.getParameter("code");
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜你，账户激活成功！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		
		return "active";
	}
	
	/**
	 * <p>方法名	login </p>
	* <p>方法描述	登录方法
	* </p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午7:17:01
	 */
	public String login(){
		/*
		 * 登录功能
		 * 1、得到页面创来的数据，一键封装
		 * 2、对数据进行输入验证
		 * 		验证不通过，保存错误信息和表单信息到Request中，用来回显。
		 * 3、验证通过，调用Service中的登录方法。
		 * 		登录异常，保存错误信息和表单信息到request中没来回显
		 * 4、登录成功，保存用户信息到session中，并跳转到index.jsp
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		User formUser = CommonUtils.toBean(request.getParameterMap(), User.class);
		//1.创建map,存放错误信息
		Map<String,String> errors = new HashMap<String,String>();
		//2.从formUser中得到表单数据进行验证
		//验证用户名
		String username = formUser.getUsername();
		if (username==null||username.trim().isEmpty()) {//空字符验证
			//当用户名为空，或者空字符时,创建错误信息
			errors.put("username", "*用户名不能为空！");
		}
		//验证密码(正则："(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}")
		String password = formUser.getPassword();
		if (password==null||password.trim().isEmpty()) {//空字符验证
			//当密码为空，或者空字符，创建错误信息
			errors.put("password","*密码不能为空！");
		}
		//验证码
		String verifycode=request.getParameter("verifycode");
		if (verifycode==null||verifycode.trim().isEmpty()) {
			errors.put("verifycode", "*验证码不能为空！");
		}else if (!verifycode.equalsIgnoreCase((String) request.getSession().getAttribute("session_verifycode"))) {
			errors.put("verifycode", "*验证码错误！");
		}
		//判断输入验证是否有值
		if (errors.size()>0) {
			//表单信息有错(验证不通过，保存错误信息和表单信息到Request中，用来回显。)
			request.setAttribute("errors", errors);
			request.setAttribute("formUser", formUser);
			//转发都jsp
			return "loginerr";
		}
		//调用登录方法
		try {
			//登录验证，登陆成功，返回user对象。
			User user = userService.login(formUser);
			//登录成功，保存用户信息到session中。
			request.getSession().setAttribute("user", user);
			//登录成功，给用户创建一个购物车存放在session中。
			request.getSession().setAttribute("cart",new Cart());
			//并跳转到主页。
			return "loginok";
		} catch (UserException e) {
			//登录失败。保存错误信息，转发到login.jsp
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("formUser", formUser);
			//转发都jsp
			return "loginerr";
		}
		
	}
	
	/**
	 * <p>方法名	exit </p>
	* <p>方法描述	退出登录</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午7:14:26
	 */
	public String exit(){
		//使session中的数据，失效。
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().invalidate();
		return "r_index_jsp";
	}
	
	public void VerifyCodeChange(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//得到VerifyCode工具类
		VerifyCode code = new VerifyCode();
		//得到验证码图片
		BufferedImage image = code.getImage();
		//保存验证码文字到session域中，用以和界面输入的验证码做对比。
		request.getSession().setAttribute("session_verifycode", code.getText());
		//将图片流输出到response输出流中用以显示在页面上
		try {
			VerifyCode.output(image, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

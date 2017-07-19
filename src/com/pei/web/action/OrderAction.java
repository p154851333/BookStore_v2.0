package com.pei.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.pei.po.Cart;
import com.pei.po.CartItme;
import com.pei.po.User;
import com.pei.po.order.Order;
import com.pei.po.order.OrderItem;
import com.pei.service.OrderException;
import com.pei.service.OrderService;

import cn.itcast.commons.CommonUtils;

public class OrderAction extends ActionSupport {
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	private OrderService orderService ;
	
	/**
	 * 添加订单的方法。
	 */
	public  String addOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/*
		 * 订单的参数都是从购物车中获取
		 * 1、创建订单。
		 * 2、从session中得到购物车。
		 * 3、给订单赋值
		 */
		//创建订单
		Order order = new Order();
		//得到购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart.getCartItmes()==null) {
			System.out.println("cart空");
			return this.myOrders(request, response);
		}
		//得到当前用户信息
		User user = (User) request.getSession().getAttribute("user");
		order.setOid(CommonUtils.uuid());//给订单赋值订单id
		order.setOrdertime(new Date());//给订单赋值系统时间
		order.setTotal(cart.getTotal());//给订单赋值购物车中的合计
		order.setState(1);//订单生成时，状态为1（未付款）
		order.setUser(user);//给订单赋值所属客户
		order.setAddress("三十三重天，兜率宫。");
		/*
		 * 给订单赋值订单条目。
		 * 条目是从购物车中获取购物车条目。
		 * 循环遍历购物车中的条目
		 */
		//创建订单条目集合
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//遍历
		for (CartItme cartItme : cart.getCartItmes()) {
			//创建订单条目
			OrderItem orderItem = new OrderItem();
			//给订单赋值
			orderItem.setIid(CommonUtils.uuid());//给订单条目赋值id
			orderItem.setCount(cartItme.getCount());//给订单条目赋值数量
			orderItem.setSubtotal(cartItme.getSubtotal());//给订单条目赋值小计
			orderItem.setOrder(order);//给订单条目确定订单
			orderItem.setBook(cartItme.getBook());//给订单条目确定商品
			//将订单条目加入到集合中
			orderItemList.add(orderItem);
		}
		//将订单条目集合添加到订单中
		order.setOrderItem(orderItemList);
		
		//订单属性赋值结束
		//执行添加订单订单业务
		orderService.addOrder(order);
		//页面需要显示订单详情。所以保存订单信息
		request.setAttribute("order", order);
		//提交订单之后，清空购物车信息
		cart.clean();
		return "f:/jsps/order/desc.jsp";
	}
	
	public  String myOneOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//得到页面上传来的oid
		String oid = request.getParameter("oid");
		Order order = orderService.findOrderByOid(oid);
		if (order==null) {
			request.setAttribute("msg","您暂无此订单！");
			return "f:/jsps/msg.jsp";
		}else {
			//页面需要显示订单详情。所以保存订单信息
			request.setAttribute("order", order);
			return "f:/jsps/order/desc.jsp";
		}
	}
	
	/**
	 * <p>方法名	JspToOrders </p>
	* <p>方法描述	页面直接到订单页面</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年5月11日 下午8:43:39
	 */
	public  String jspToOrders() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//从session中获取当前用户信息。
		User user = (User) request.getSession().getAttribute("user");
		//使用uid查询当前用户的订单
		List<Order> orderList= orderService.findOrderByUid(user.getUid());
		if (orderList==null||orderList.size()==0) {
			request.setAttribute("msg","您暂无订单！");
			return "f:/jsps/msg.jsp";
		}else {
			request.setAttribute("orderList",orderList);
			return "f:/jsps/order/list.jsp";
		}
	}
	
	/**
	 * <p>方法名	myOrders </p>
	* <p>方法描述	操作订单返回订单页面的方法</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年5月11日 下午8:42:44
	 */
	private  String myOrders(HttpServletRequest request,HttpServletResponse response) {
		//从session中获取当前用户信息。
		User user = (User) request.getSession().getAttribute("user");
		//使用uid查询当前用户的订单
		List<Order> orderList= orderService.findOrderByUid(user.getUid());
		if (orderList==null||orderList.size()==0) {
			request.setAttribute("msg","您暂无订单！");
			return "f:/jsps/msg.jsp";
		}else {
			request.setAttribute("orderList", orderList);
			return "f:/jsps/order/list.jsp";
		}
	}

	/**
	 * <p>方法名	deleteOrders </p>
	* <p>方法描述	根据oid删除单个订单</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午10:08:08
	 */
	public  String deleteOrder() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//得到页面上传来的oid
		String oid = request.getParameter("oid");
		//调动删除订单的业务
		try {
			orderService.deleteOrder(oid);
			return this.myOrders(request, response);
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}
	}
	
	/**
	 * <p>方法名	changeOrderStarte </p>
	* <p>方法描述	确认收货订单状态
	* 				将订单状态由3->4</p>
	* 参数 @param request
	* 参数 @param response
	* 参数 @return
	* 参数 @throws ServletException
	* 参数 @throws IOException 参数说明
	* 返回类型 String 返回类型
	* @author	裴健
	* @date		2017年3月29日 下午2:08:24
	 */
	public  String confirmGoods() {
		HttpServletRequest request = ServletActionContext.getRequest();
		/*
		 * 1.从页面得到订单的oid
		 * 2.提交给业务层修改状态码。
		 * 		3、有异常则提交失败，保存异常信息，返回到msg.jsp
		 * 		4、无异常则修改状态成功，返回到我的订单列表
		 */		
		String oid = request.getParameter("oid");
		try {
			orderService.confirmGoods(oid);
			System.out.println("成功");
			request.setAttribute("msg","感谢您对DarlingMonkey的支持！祝您生活愉快~");
			return "f:/jsps/order/list.jsp";
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/jsps/msg.jsp";
		}
	}
	
	public String zhiFu(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("支付方法");
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("merchantInfo.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		/*
		 * 准备13参数
		 */
		String p0_Cmd = "Buy";
		String p1_MerId = props.getProperty("p1_MerId");
		String p2_Order = request.getParameter("oid");
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = props.getProperty("p8_Url");
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = request.getParameter("pd_FrpId");
		String pr_NeedResponse = "1";

		/*
		 * 计算hmac
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);

		/*
		 * 连接易宝的网址和13+1个参数
		 */
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);

		System.out.println(url);

		/*
		 * 重定向到易宝
		 */
		try {
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return NONE;
	}

	/**
	 * 这个方法是易宝回调方法 我们必须要判断调用本方法的是不是易宝！
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String back(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		/*
		 * 1. 获取11 + 1
		 */
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");

		String hmac = request.getParameter("hmac");

		/*
		 * 2. 校验访问者是否为易宝！
		 */
		Properties props = new Properties();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("merchantInfo.properties");
		try {
			props.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String keyValue = props.getProperty("keyValue");

		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		
		if(!bool) {//如果校验失败
			request.setAttribute("msg", "您不是什么好东西！");
			return "f:/jsps/msg.jsp";
		}
		
		/*
		 * 3. 获取状态订单，确定是否要修改订单状态，以及添加积分等业务操作
		 */
		orderService.zhiFu(r6_Order);//有可能对数据库进行操作，也可能不操作！
		
		/*
		 * 4. 判断当前回调方式
		 *   如果为点对点，需要回馈以success开头的字符串
		 */
		if(r9_BType.equals("2")) {
			try {
				response.getWriter().print("success");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		/*
		 * 5. 保存成功信息，转发到msg.jsp
		 */
		request.setAttribute("msg", "支付成功！等待卖家发货！你慢慢等~");
		return "f:/jsps/msg.jsp";
	}

	
}

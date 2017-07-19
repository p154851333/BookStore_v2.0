package com.pei.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pei.po.Book;
import com.pei.po.Category;
import com.pei.po.User;
import com.pei.po.order.Order;
import com.pei.po.order.OrderItem;

public class OrderServiceTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		OrderService orderService = (OrderService) context.getBean("orderService");
		
		Order order = new Order("sdssssdsdgfdsdaaaaa", new Date(), 10.0, 1, "wo1jia"
				, new User("admin2211", "admin11", "admin11", "e1mail", "co1de", false)
				, null);
		orderService.addOrder(order);
	}
	ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
	OrderService orderService = (OrderService) context.getBean("orderService");
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetOrderDao() {
	}

	@Test
	public void testAddOrder() {
		Order order = new Order("sdssssdsdgssssfdsdaaaaa", new Date(), 10.0, 1, "wo1jia"
				, new User("admin1", "admin11", "admin11", "e1mail", "co1de", false)
				, null);
		orderService.addOrder(order);
	}

	@Test
	public void testFindOrderByUid() {
		List<Order> orderList = orderService.findOrderByUid("4028818c5be73fcc015be73fd0f10000");
		for (Order order : orderList) {
			List<OrderItem> orderItems = order.getOrderItem();
			for (OrderItem orderItem : orderItems) {
				System.out.println(orderItem);
			}
		}
	}

	@Test
	public void testDeleteOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrderByOid() {
		Order order = orderService.findOrderByOid("E6E8A6F461A543BC82977337A8E973BE");
		System.out.println(order);
	}

	@Test
	public void testConfirmGoods() {
		fail("Not yet implemented");
	}

	@Test
	public void testZhiFu() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindOrderByState() {
		fail("Not yet implemented");
	}

}

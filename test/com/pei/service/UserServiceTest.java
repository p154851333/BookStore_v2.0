package com.pei.service;

import static org.junit.Assert.fail;

import cn.itcast.commons.CommonUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pei.po.User;

public class UserServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRegist() throws UserException {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		UserService service = (UserService) context.getBean("userService");
		User user = new User();
		user.setUid(CommonUtils.uuid());
		user.setUsername("admin4");
		user.setPassword("admin4");
		user.setState(false);
		service.regist(user);
	}

	@Test
	public void testActive() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() throws UserException {
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		UserService service = (UserService) context.getBean("userService");
		User user = new User();
		user.setUsername("admin3");
		user.setPassword("admin3");
		User login = service.login(user);
		System.out.println(login);
	}

}

package com.pei.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pei.dao.BookDao;
import com.pei.dao.BookDaoImpl;
import com.pei.po.Book;
import com.pei.po.PageBean;

public class BookServiceTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
	BookService bookService = (BookService) context.getBean("bookService");
			
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBookByCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBookById() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBookByCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testPageFindBookByCategory() {
		PageBean<Book> page = bookService.pageFindBookByCategory("1", 1, "");
		System.out.println(page);
	}

}

package com.adel.userTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.adel.dao.IUserDAO;
import com.adel.model.User;
import com.opensymphony.xwork2.interceptor.annotations.Before;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class TestUserDAO extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static final Logger log = Logger.getLogger(TestUserDAO.class);
	
	@Autowired
	IUserDAO userdao;
	
	@Before
	public void before(){
		log.info("------------------------");
		log.info("Avant Tests");
		log.info("------------------------");
	}
	
	@Test
	public void testAddUser(){
		Assert.assertNotNull(userdao);
		User user1 = new User();
		user1.setLogin("aaaa@aaaa.com");
		user1.setPassword("aaaa");
		user1.setEnable(true);
		user1.setLastConnexion(new Date());
		userdao.create(user1);
		userdao.flush();
		List<User> users = userdao.getAll(null);
		// create user test
		log.info(user1.getIdUser());
		Assert.assertNotNull(user1.getIdUser());
		
		// find user
		User user2 = userdao.find(user1.getIdUser());
		Assert.assertNotNull(user2);
		Assert.assertEquals(user2.getLogin(), "aaaa@aaaa.com");	
		
		// count all
		Assert.assertEquals(users.size(), 1);
		
		// Update user test
		user2.setLogin("bbbb@bbbb.com");
		user2.setPassword("bbbb");
		user2.setEnable(false);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = "01/01/2017";
		Date date = null;
		try {
			date = sdf.parse(dateStr);
			log.info(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user2.setLastConnexion(date);
		
		// update user
		userdao.update(user2);
		
		// test update
		User user3 = userdao.find(user2.getIdUser());
		Assert.assertNotNull(user3);
		Assert.assertEquals(user2.getLogin(), "bbbb@bbbb.com");
		Assert.assertEquals(user2.getPassword(), "bbbb");
		Assert.assertEquals(user2.isEnable(), false);
		Assert.assertEquals(user2.getLastConnexion(), date);
		
	}
	
	@Test
	public void getQueryClausesTest(){
		User user1 = new User();
		user1.setLogin("aaaa@aaaa.com");
		user1.setPassword("aaaa");
		user1.setEnable(true);
		user1.setLastConnexion(new Date());
		userdao.create(user1);
		
		User user2 = new User();
		user2.setLogin("user2@aaaa.com");
		user2.setPassword("user2");
		user2.setEnable(true);
		user2.setLastConnexion(new Date());
		userdao.create(user2);
		
		User user3 = new User();
		user3.setLogin("user3@aaaa.com");
		user3.setPassword("user3");
		user3.setEnable(true);
		user3.setLastConnexion(new Date());
		userdao.create(user3);
		
		userdao.flush();
		
		List<User> users = userdao.getAll(null);
		
		Assert.assertEquals(users.size(), 3);
		
		// test where clause
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", "aaaa@aaaa.com");
		//params.put("password", "user2");
		//params.put("enable", true);
		
		String clause = userdao.getQueryClauses(params, null);
		System.out.println(clause);
		Assert.assertEquals(" where login = 'aaaa@aaaa.com'", clause);
		
		// test order by
		Map<String, Object> orderParams = new HashMap<String, Object>();
		orderParams.put("login", "");
		String orderClause = userdao.getQueryClauses(null, orderParams);
		Assert.assertEquals(" order by login ", orderClause);
	}
}

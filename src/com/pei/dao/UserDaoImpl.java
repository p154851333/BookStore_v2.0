package com.pei.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.pei.po.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@SuppressWarnings("all")
	@Override
	public User findUserByName(String username) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find(
				"from User where username=?",username);
		if (userList != null && userList.size() != 0) {
			User u = userList.get(0);
			return u;
		}
		return null;
	}

	@Override
	public void addUser(User form) {
		this.getHibernateTemplate().save(form);
	}

	@SuppressWarnings("all")
	@Override
	public User findUseByEmail(String email) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find(
				"from User where email=?",email);
		if (userList != null && userList.size() != 0) {
			User u = userList.get(0);
			return u;
		}
		return null;
	}

	@SuppressWarnings("all")
	@Override
	public User findUseByCode(String code) {
		List<User> userList = (List<User>) this.getHibernateTemplate().find(
				"from User where code=?",code);
		if (userList != null && userList.size() != 0) {
			User u = userList.get(0);
			return u;
		}
		return null;
	}

	@Override
	public void updateState(String uid, boolean state) {
		User user = this.findUserById(uid);
		user.setState(state);
//		this.getHibernateTemplate().update(user);
		
	}

	@Override
	public User findUserById(String uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}


}

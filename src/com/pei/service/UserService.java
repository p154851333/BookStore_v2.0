package com.pei.service;

import org.springframework.transaction.annotation.Transactional;

import com.pei.dao.UserDao;
import com.pei.po.User;

@Transactional
public class UserService {
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private UserDao userDao;
	
	
	/**
	 * <p>方法名	regist </p>
	* <p>方法描述	注册验证
	* 				如果查到form，则说明有人注册过，则不允许注册。
	* </p>
	* 参数 @param form 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月25日 下午9:26:14
	 */
	public void regist(User form) throws UserException{
		//查询数据库是否有相同User（用户名或者邮箱验证）
		User user = userDao.findUserByName(form.getUsername());
		if (user!=null) {
			throw new UserException("*此用户名已被注册！");
		}
		user = userDao.findUseByEmail(form.getEmail());
		if (user!=null) {
			throw new UserException("*此邮箱已被注册！");
		}
		//查询不到user则说明没有重复uesr，将form添加到数据库
		userDao.addUser(form);
	}


	/**
	 * <p>方法名	active </p>
	* <p>方法描述	激活账户</p>
	* 参数 @param code 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午4:41:58
	 */
	public void active(String code) throws UserException{
		//使用code去dao中查询是否有code的user
		User user = userDao.findUseByCode(code);
		if (user==null) {
			//数据库中没有对应的激活码账户
			throw new UserException("*激活码有误！");
		}
		//有账户的时候得到账户的uid
		String uid = user.getUid();
		boolean state = user.isState();
		//修改该账户的状态码。
		//判断账户的激活码。true时，说明账户已激活
		if (state) {
			throw new UserException("*账户已激活，无需再次激活！");
		}else {
			//账户未激活，激活
			userDao.updateState(uid, true);
		}
	}


	public User login(User formUser) throws UserException{
		//通过用户名查询dao
		User user = userDao.findUserByName(formUser.getUsername());
		if (user==null) {
			//查询为空。说明么有用户
			throw new UserException("*用户名输入错误！");
		}else if (!(user.getPassword().equals(formUser.getPassword()))) {
			//密码验证失败
			throw new UserException("*密码输入错误！");
		}else if (!user.isState()) {
			throw new UserException("*账户还未激活，登录"+user.getEmail()+"邮箱，激活后方可登录！");
		}  
		return user;
	}
}

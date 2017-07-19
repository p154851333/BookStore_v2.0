package com.pei.dao;

import com.pei.po.User;

public interface UserDao {
	
	/**
	 * <p>方法名	findUserByName </p>
	* <p>方法描述	通过username得到User</p>
	* 参数 @param username
	* 参数 @return 参数说明
	* 返回类型 User 返回类型
	* @author	裴健
	* @date		2017年3月25日 下午9:07:05
	 */
	public User findUserByName(String username);
	
	public void addUser(User form);
	
	/**
	 * <p>方法名	findUseByEmail </p>
	* <p>方法描述	通过email得到user</p>
	* 参数 @param email
	* 参数 @return 参数说明
	* 返回类型 User 返回类型
	* @author	裴健
	* @date		2017年3月25日 下午9:09:23
	 */
	public User findUseByEmail(String email);
	
	
	/**
	 * <p>方法名	findUseByCode </p>
	* <p>方法描述	根据code查询是否有user</p>
	* 参数 @param code
	* 参数 @return 参数说明
	* 返回类型 User 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午4:47:56
	 */
	public User findUseByCode(String code) ;
	
	/**
	 * <p>方法名	updateState </p>
	* <p>方法描述	更改数据state(账户激活状态)</p>
	* 参数 @param uid
	* 参数 @param state 参数说明
	* 返回类型 void 返回类型
	* @author	裴健
	* @date		2017年3月26日 下午4:49:23
	 */
	public void updateState(String uid ,boolean state);
	
	/**
	 * <p>方法名	findUserById </p>
	* <p>方法描述	通过uid得到user对象</p>
	* 参数 @param uid
	* 参数 @return 参数说明
	* 返回类型 User 返回类型
	* @author	裴健
	* @date		2017年3月28日 下午8:42:29
	 */
	public User findUserById(String uid) ;
}

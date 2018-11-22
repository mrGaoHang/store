package com.hang.store.service.impl;

import java.sql.SQLException;

import com.hang.store.dao.UserDao;
import com.hang.store.dao.impl.UserDaoImpl;
import com.hang.store.domain.User;
import com.hang.store.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void userRegist(User user) throws SQLException {
		//实现注册功能
		UserDao dao =new UserDaoImpl();
		dao.userRegist(user);
	}

	@Override
	public boolean userActive(String code) throws SQLException {
		//实现激活功能
		UserDao dao =new UserDaoImpl();
		User user=dao.userActive(code);
		if(user!=null) {
			//	根据激活码能查询到用户
			//	修改用户的状态为1，并清楚激活码
			user.setState(1);
			user.setCode(null);
			dao.updateUser(user);
			return true;
		}else{
			return false;
		}
	}

}

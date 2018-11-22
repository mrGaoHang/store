package com.hang.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hang.store.dao.UserDao;
import com.hang.store.domain.User;
import com.hang.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void userRegist(User user) throws SQLException {
		String sql ="INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
						   user.getName(),user.getEmail(),user.getTelephone(),
						   user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	}

	@Override
	public void updateUser(User user) throws SQLException {
		//update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?
		String sql ="update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		runner.update(sql,  user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid());
		
	}

	@Override
	public User userActive(String code) throws SQLException {
		String sql="select * from user where code=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		User user = runner.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}

}

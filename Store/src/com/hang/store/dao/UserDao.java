package com.hang.store.dao;

import java.sql.SQLException;

import com.hang.store.domain.User;

public interface UserDao {

	void userRegist(User user) throws SQLException;

	void updateUser(User user) throws SQLException;

	User userActive(String code) throws SQLException;

}

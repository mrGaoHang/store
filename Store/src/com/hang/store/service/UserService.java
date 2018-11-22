package com.hang.store.service;

import java.sql.SQLException;

import com.hang.store.domain.User;

public interface UserService {

	void userRegist(User user) throws SQLException;

	boolean userActive(String code) throws SQLException;

	

}

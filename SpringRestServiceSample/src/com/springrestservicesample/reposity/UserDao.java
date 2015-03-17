package com.springrestservicesample.reposity;

import java.sql.Connection;
import java.util.List;

import com.springrestservicesample.domain.User;
import com.springrestservicesample.utility.DBUtility;

public interface UserDao {
	Connection connection = DBUtility.getConnection();
	List<User> getAllUsers();
	User getUserById(int userId);
}

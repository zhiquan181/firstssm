package com.javen.dao;

import java.util.List;
import com.javen.model.User;

public interface IUserDao {

	List<User> getUserList();

	User getUserDetailById(int userid);

	void UserInsert(User user);

	void UserUpdate(User user);

	void UserDelete(int userid);
}
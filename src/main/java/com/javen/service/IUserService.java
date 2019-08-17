package com.javen.service;  

import java.util.List;
import com.javen.model.User;
  
public interface IUserService {

	public List<User> getUserList();
	
	public User getUserDetailById(int userid);

	public int UserInsert(User user);

	public int UserUpdate(User user);

	public int UserDelete(int userid);
} 
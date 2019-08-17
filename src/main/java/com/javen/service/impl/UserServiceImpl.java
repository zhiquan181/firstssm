package com.javen.service.impl;

import java.util.List;
import javax.annotation.Resource;  
import org.springframework.stereotype.Service;  
import com.javen.dao.IUserDao;
import com.javen.model.User;
import com.javen.service.IUserService;  
  
@Service("userService")  
public class UserServiceImpl implements IUserService {  
    @Resource  
    private IUserDao userDao;  

	public List<User> getUserList() {
		return this.userDao.getUserList();
	}
	
	public User getUserDetailById(int userid) {
		return this.userDao.getUserDetailById(userid);
	}

	public int UserInsert(User user) {
		try {
			this.userDao.UserInsert(user);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int UserUpdate(User user) {
		try {
			this.userDao.UserUpdate(user);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public int UserDelete(int userid) {
		try {
			this.userDao.UserDelete(userid);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}  
  
}  

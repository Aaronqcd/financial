package com.kljx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kljx.dao.UserDao;
import com.kljx.pojo.User;
import com.kljx.service.UserManageService;

@Service("userManageService")
public class UserManageServiceImpl implements UserManageService {

	@Autowired
	private UserDao userDao;

	public List<User> queryUserList() throws RuntimeException {
		return this.userDao.queryUserList();
	}
	
	@Transactional(rollbackFor=RuntimeException.class)
	public void insertUser() throws RuntimeException {
		User user1 = new User();
		user1.setUsername("马宇1");
		user1.setPassword("87b750fdfeb4468f58c3247b303704ab");
		user1.setCellphone("13980573875");
		user1.setEmail("mayulll@163.com");
		user1.setRealName("管理员1");
		user1.setStatus(1);
		user1.setRoleid(36);
		user1.setIsproduct3head(0);
		user1.setIsemail(1);
		userDao.insertUser(user1);

		User user2 = new User();
		user2.setUsername("马宇2");
		user2.setPassword("87b750fdfeb4468f58c3247b303704ab");
		user2.setCellphone("13980573875");
		user2.setEmail("mayulll@163.com");
		user2.setRealName("管理员1");
		user2.setStatus(1);
		user2.setRoleid(36);
		user2.setIsproduct3head(0);
		user2.setIsemail(1);
		userDao.insertUser(user2);
		
		throw new RuntimeException();
	}

}
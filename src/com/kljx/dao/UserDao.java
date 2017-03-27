package com.kljx.dao;


import java.util.List;
import com.kljx.pojo.User;


public interface UserDao {
	
	public List<User> queryUserList();
	
	public void insertUser(User user);

}

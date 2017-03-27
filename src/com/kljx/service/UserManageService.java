package com.kljx.service;

import java.util.List;
import com.kljx.pojo.User;

public abstract interface UserManageService {

	public abstract List<User> queryUserList() throws RuntimeException;
	
	public abstract void insertUser() throws RuntimeException;

}
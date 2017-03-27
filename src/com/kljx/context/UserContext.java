package com.kljx.context;

import com.kljx.pojo.User;

public class UserContext {
	private String username;
	private String ip;
	private Integer role;
	private Long lastoptime;
	private User user;
	private String option;
	private Long timeout = Long.valueOf(60L);
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Long getLastoptime() {
		return lastoptime;
	}
	public void setLastoptime(Long lastoptime) {
		this.lastoptime = lastoptime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Long getTimeout() {
		return timeout;
	}
	public void setTimeout(Long timeout) {
		this.timeout = timeout;
	}

}
package com.kljx.workflow;

import java.util.List;

public class CheckUserInfo
{
	private String account;
	private String name;
	private List<Integer> officeDept;
	private List<Integer> exptendDept;
	private List<String> firstProductDept;

	public String getAccount()
	{
		return this.account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getOfficeDept() {
		return this.officeDept;
	}
	public void setOfficeDept(List<Integer> officeDept) {
		this.officeDept = officeDept;
	}
	public List<Integer> getExptendDept() {
		return this.exptendDept;
	}
	public void setExptendDept(List<Integer> exptendDept) {
		this.exptendDept = exptendDept;
	}
	public List<String> getFirstProductDept() {
		return this.firstProductDept;
	}
	public void setFirstProductDept(List<String> firstProductDept) {
		this.firstProductDept = firstProductDept;
	}
}
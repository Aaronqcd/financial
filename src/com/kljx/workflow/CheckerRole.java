package com.kljx.workflow;

public class CheckerRole
{
	public static final String VARIABLE_KEYS_BH = "BH";
	public static final String VARIABLE_KEYS_JH = "JH";
	private String roleName;
	private String mapKey;
	private String refModule;
	private CheckUserInfo[] refCheckUserInfo;

	public String getRoleName()
	{
		return this.roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getMapKey() {
		return this.mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public String getRefModule() {
		return this.refModule;
	}
	public void setRefModule(String refModule) {
		this.refModule = refModule;
	}
	public CheckUserInfo[] getRefCheckUserInfo() {
		return this.refCheckUserInfo;
	}
	public void setRefCheckUserInfo(CheckUserInfo[] refCheckUserInfo) {
		this.refCheckUserInfo = refCheckUserInfo;
	}

	public String toString()
	{
		return " mapKey = " + this.mapKey + ", roleName = " + this.roleName + " , refModule = " + this.refModule + " , refCheckUserInfo = " + this.refCheckUserInfo;
	}
}
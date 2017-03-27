package com.kljx.action;


import java.util.HashMap;
import java.util.Map;

import com.kljx.context.UserContext;

public abstract class MemberBaseAction extends BaseAction {
	public UserContext getUserContext()
	{
		return (UserContext)this.session.get("userContext");
	}

	public static Map<String, String> getStatusList() {
		Map result = new HashMap();
		result.put("0", "失效");
		result.put("1", "生效");
		return result;
	}
}
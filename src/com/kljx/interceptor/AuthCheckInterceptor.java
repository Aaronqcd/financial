package com.kljx.interceptor;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kljx.action.MemberBaseAction;
import com.kljx.context.SpringContext;
import com.kljx.context.UserContext;
import com.kljx.menu.ActionPermissions;
import com.kljx.pojo.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthCheckInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = (HttpServletRequest)ai.getInvocationContext().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
		HttpSession session = request.getSession();
		UserContext user = (UserContext)session.getAttribute("userContext");
		if ((ai.getAction() instanceof MemberBaseAction)) {
			ActionPermissions perm = (ActionPermissions)
			SpringContext.getBean("ActionPermission");
			String actionName = ai.getInvocationContext().getName();
			Integer acctionRequired = (Integer)perm.getPermissionMap().get(actionName);
			Map parameters = ai.getInvocationContext().getParameters();
			System.out.println("Action Name: " + actionName);
			Iterator localIterator = parameters.keySet().iterator();
			while (localIterator.hasNext()) {
				Object paString = localIterator.next();
				System.out.println(paString + "--:--" + parameters.get(paString));
			}
			if ((acctionRequired != null) && (!checkUserPermissions(user.getUser(), acctionRequired))) {
				return "login-index";
			}
		}
		return ai.invoke();
	}

	private boolean checkUserPermissions(User user, Integer acctionRequired) {
		if (user == null) {
			return false;
		}
		if (user.getRoleid() != 4) {
			if (user.getRoleid() == acctionRequired.intValue())
				return true;
		} else {
			if (acctionRequired.intValue() != 1) {
				return true;
			}
			return false;
		}

		return false;
	}

}
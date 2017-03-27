package com.kljx.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.kljx.componet.MailBeanComponent;
import com.kljx.pojo.User;
import com.kljx.service.UserManageService;

public class UserManageAction {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManageService userManageService;
	
	@Autowired
	private MailBeanComponent mailBeanComponent;

	public void getUserList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			pw = response.getWriter();
			List<User> userlist = this.userManageService.queryUserList();

			map.put("userlist", userlist);
		} catch (Exception e) {
            System.out.println(e);
		} finally {
			try { if (pw != null) { pw.println(JSON.toJSONString(map));pw.flush();pw.close(); } } catch (Exception e) { }
		}
	}
	
	/** test 事务回滚 */
	public void insertUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		this.userManageService.insertUser();
		
	}
	
	/** test 发送邮件 */
	public void sendMail() {
		this.mailBeanComponent.sendMailToUserForBusinessBeforeApply("a111111", "xiaoma", "hujia", "mayulll@163.com");
		System.out.println("发送成功！");
		
	}
	
	

}
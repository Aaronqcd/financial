package com.kljx.componet;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MailBeanComponent {

	@Value("${mail.host}")
	private String mailHost;

	@Value("${mail.port}")
	private Integer mailPort;

	@Value("${mail.username}")
	private String mailName;

	@Value("${mail.password}")
	private String mailPassword;

	@Value("${mail.protocol}")
	private String protocol;

	@Value("${mail.auth}")
	private String auth;

	@Value("${mail.encoding}")
	private String encoding;

	@Value("${web.webSite}")
	private String webSite;

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	private JavaMailSenderImpl javaMailSender;

	@PostConstruct
	private void createMailSendInfo() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(this.mailHost);
		javaMailSender.setPort(this.mailPort.intValue());
		javaMailSender.setProtocol(this.protocol);
		javaMailSender.setUsername(this.mailName);
		javaMailSender.setPassword(this.mailPassword);
		javaMailSender.getJavaMailProperties().setProperty("mail.smtp.auth", this.auth);
		javaMailSender.setDefaultEncoding(this.encoding);
		this.javaMailSender = javaMailSender;
	}

	public boolean sendMimeEmail(MessageInfo messageInfo) {
		try
		{
			this.threadPoolTaskExecutor.execute(new TaskRun(messageInfo));
			return true; 
		} catch (Exception ex) {
		}
		return false;
	}

	public boolean sendMailToUserForBusinessBeforeApply(String codeId, String sendAccount, String userName, String emailAddress) {
		MessageInfo info = new MessageInfo();
		info.setReceiver(emailAddress);

		info.setSubject("财务报销系统《差旅报销单》需要您审批");
		info.setHtmlContext("尊敬的用户 " + userName + " 您好：<br>" + 
				"账号为 " + sendAccount + " 的用户提交了差旅报销申请，申请单编号为" + codeId + "，请您及时登录财务报销系统进行审批，<br/>" + 
				"财务报销系统链接为：<a href='" + this.webSite + "'>" + this.webSite + "</a>直接点击或复制粘贴到浏览器的URl处。该邮件为系统自动发出，请勿回复！");
		return sendMimeEmail(info);
	}

	public boolean sendMailNotifyToUser(String emailAddress, String title, String content) {
		MessageInfo info = new MessageInfo();
		info.setReceiver(emailAddress);
		info.setSubject(title);
		info.setHtmlContext(content);
		return sendMimeEmail(info);
	}

	public String getWebSite() {
		return this.webSite;
	}

	public class MessageInfo {
		private String receiver;
		private String subject;
		private String htmlContext;

		public MessageInfo() {

		}

		public String getReceiver()
		{
			return this.receiver;
		}
		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}
		public String getSubject() {
			return this.subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getHtmlContext() {
			return this.htmlContext;
		}
		public void setHtmlContext(String htmlContext) {
			this.htmlContext = htmlContext;
		}
	}

	class TaskRun implements Runnable {
		private MailBeanComponent.MessageInfo info;

		public TaskRun(MailBeanComponent.MessageInfo info) {
			this.info = info;
		}

		public void run() {
			MimeMessage msg = MailBeanComponent.this.javaMailSender.createMimeMessage();

			MimeMessageHelper msgHelper = new MimeMessageHelper(msg);
			try {
				msgHelper.setFrom(MailBeanComponent.this.mailName);
				msgHelper.setTo(this.info.getReceiver());
				msgHelper.setSubject(this.info.getSubject());
				msgHelper.setText(this.info.getHtmlContext(), true);

				MailBeanComponent.this.javaMailSender.send(msg);
				System.out.println("sendMimeEmail ...");
			}
			catch (MessagingException mex) {
				throw new InternalError("发送出现错误!");
			}
		}
	}
}
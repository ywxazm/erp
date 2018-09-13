package com.ywx.erp.common;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件工具类
 */
public class MailUtil {

	private JavaMailSender javaMailSender;
	private String from;
	public void setFrom(String from) {
		this.from = from;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(String to, String subject, String text) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();	//1. 创建邮件信息
		MimeMessageHelper helper = new MimeMessageHelper(message);	//2. 使用spring邮件工具类
		helper.setTo(to);			//3.收件人
		helper.setFrom(from);		//4.发件人
		helper.setSubject(subject);	//5.设置邮件的标题
		helper.setText(text);		//6.邮件的内容
		javaMailSender.send(message);//7.发送邮件
	}
}

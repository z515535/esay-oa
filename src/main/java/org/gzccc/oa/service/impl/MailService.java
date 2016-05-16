package org.gzccc.oa.service.impl;

import javax.inject.Inject;

import org.gzccc.oa.mail.Mail;
import org.gzccc.oa.mail.MailUtil;
import org.springframework.stereotype.Service;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月15日 下午6:46:04<p> 
* 类说明 发送邮件服务
*/
@Service("mailService")
public class MailService {
	@Inject
	private MailUtil mailUtil;
	@Inject
	private Mail mail;
	
	/**
	 * 发送邮件
	 * @param receiver 收件人邮件
	 * @param subject 主题
	 * @param message 内容
	 * @return
	 */
	public boolean send(String receiver,String subject,String message){
		mail.setReceiver(receiver);
		mail.setSubject(subject);
		mail.setMessage(message);
		return mailUtil.send(mail);
	}
}
 
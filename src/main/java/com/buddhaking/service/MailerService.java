package com.buddhaking.service;

import com.buddhaking.entity.MailModel;

import jakarta.mail.internet.MimeMessage;

public interface MailerService {

	public void push(String to, String subject, String body);
	
}	

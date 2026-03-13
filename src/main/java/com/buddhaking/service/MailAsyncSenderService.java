package com.buddhaking.service;

import jakarta.mail.internet.MimeMessage;

public interface MailAsyncSenderService {
	
	public void send(MimeMessage message);
	
}

package com.buddhaking.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.buddhaking.service.MailAsyncSenderService;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailAsyncSenderServiceImp implements MailAsyncSenderService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	@Async("mailExecutor")
	public void send(MimeMessage message) {
		System.out.println("THREAD: " + Thread.currentThread().getName());
		
		sender.send(message);
		
	}
	
	
}

package com.buddhaking.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.buddhaking.entity.MailModel;
import com.buddhaking.service.MailAsyncSenderService;
import com.buddhaking.service.MailerService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailerServiceImp implements MailerService {

	@Autowired
    private JavaMailSender sender;
	
	@Autowired
	private MailAsyncSenderService AsyncSender;

    public void push(String to, String subject, String body) {
        try {
            MimeMessage message = sender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "utf-8");

            helper.setFrom("thaomochuongnhang@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            AsyncSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

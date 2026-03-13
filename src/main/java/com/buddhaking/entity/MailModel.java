package com.buddhaking.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MailModel {
	
	private String from = "Thảo Mộc Hương Nhang <thaomochuongnhang@gmail.com>";
	
	private String to;
	
	private String subject;
	
	private String body;
	
	private List<String> cc = new ArrayList<>();
	
	private List<String> bcc = new ArrayList<>();
	
	private List<File> files = new ArrayList<>();
	
	public MailModel(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	
}

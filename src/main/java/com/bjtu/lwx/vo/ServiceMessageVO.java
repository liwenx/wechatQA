package com.bjtu.lwx.vo;

import org.springframework.stereotype.Component;

@Component
public class ServiceMessageVO {
	
	private String touser;
	private String msgtype;
	private String content;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}

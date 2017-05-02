package com.bjtu.lwx.service;


public interface WechatAppService {
	
	public String getUserInfo(String code);
	
	public String getAppUserInfo(String openid);
}

package com.bjtu.lwx.service;

import java.util.List;

import com.bjtu.lwx.po.AppUserInfoPO;

public interface WechatAppService {
	
	public String getUserInfo(String code);
	
	public AppUserInfoPO getAppUserInfo(String openid);
}

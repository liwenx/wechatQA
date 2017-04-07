package com.bjtu.lwx.po;

import org.springframework.stereotype.Component;

/**
 * weixin_accesstoken 表
 * 获取access_token
 * @author liwenxing
 *
 */
@Component
public class AccessTokenPO {
	
	private String token;
	private int expiresIn;
	private String creatTime;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	

	

}

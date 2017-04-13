package com.bjtu.lwx.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AppAccessTokenPO;



@MapperScan
public interface AppAccessTokenDao {
	
	public AppAccessTokenPO getAppAccessTokenByOpenid(String openid);
	public int insertAppAccessToken(AppAccessTokenPO aatpo);
	public void deleteAppAccessTokenByOpenid(String openid);
}

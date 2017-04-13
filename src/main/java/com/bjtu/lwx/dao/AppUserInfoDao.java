package com.bjtu.lwx.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AppUserInfoPO;

@MapperScan
public interface AppUserInfoDao {
	
	public int insertUserInfo(AppUserInfoPO auipo);
	public AppUserInfoPO getAppUserInfoByOpenid(String openid);
	public void deleteAppUserInfoByOpenid(String openid);
}

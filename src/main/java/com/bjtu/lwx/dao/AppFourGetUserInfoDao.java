package com.bjtu.lwx.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AppUserInfoPO;

@MapperScan
public interface AppFourGetUserInfoDao {
	
	public String getUserInfoByOpenid (String openid);

}

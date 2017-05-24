package com.bjtu.lwx.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AppUserInfoPO;

@MapperScan
public interface AppFourGetUserInfoDao {
	
	public List<AppUserInfoPO> getUserInfoByOpenid (String openid);

}

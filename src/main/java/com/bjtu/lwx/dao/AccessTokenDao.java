package com.bjtu.lwx.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AccessTokenPO;


@MapperScan
public interface AccessTokenDao {
	
	public List<AccessTokenPO> getToken();
	public int insertByAccessToken (AccessTokenPO atpo);
	public void deleteAccessToken();
}

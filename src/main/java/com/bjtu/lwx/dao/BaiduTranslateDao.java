package com.bjtu.lwx.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.BaiduTranslatePO;

@MapperScan
public interface BaiduTranslateDao {
	
	public List<BaiduTranslatePO> getAbbByName (String languageName);
}
 
package com.bjtu.lwx.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.QuestionPO;

@MapperScan
public interface QuestionDao {
	
	public void insertByQuestion(QuestionPO qupo);

}

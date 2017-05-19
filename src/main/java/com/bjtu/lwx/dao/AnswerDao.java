package com.bjtu.lwx.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AnswerPO;

@MapperScan
public interface AnswerDao {
	
	public void insertAnswer(AnswerPO aspo);
	
	public List<AnswerPO> getAnswerInfoByAnswerid(int answerid);
	
	public void addPageviewsByAnswer(AnswerPO aspo);

}

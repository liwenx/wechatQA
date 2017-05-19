package com.bjtu.lwx.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.bjtu.lwx.po.AnswerPO;
import com.bjtu.lwx.po.QuestionListPO;
import com.bjtu.lwx.po.QuestionPO;

@MapperScan
public interface QuestionDao {
	
	//发布问题
	public int insertByQuestion(QuestionPO qupo);
	
	//查询热门问题列表
	public List<QuestionListPO> getHotQuestion(String createTime);
	
	//查询推荐问题
	public List<QuestionListPO> getReQuestion(QuestionPO qupo);
	
	//查询操作数量
	public int getActionNum(QuestionPO qupo);
	
	//根据问题id查询问题信息
	public List<QuestionPO> getQuestionInfoByQuestionid(int questionid);
	
	//查询回答列表
	public List<AnswerPO> getAnswerListByQuestionid(int questionid);
	
	//问题浏览量+1
	public void addPageviewsByQuestionid(QuestionPO qupo);

}

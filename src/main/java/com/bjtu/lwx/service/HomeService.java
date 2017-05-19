package com.bjtu.lwx.service;

import java.util.List;
import java.util.Map;

import com.bjtu.lwx.po.AnswerPO;
import com.bjtu.lwx.po.QuestionListPO;
import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.po.UserActionPO;

public interface HomeService {
	
	public List<QuestionListPO> getHotQuestion();
	
	public List<QuestionListPO> getReQuestion(String openid);
	
	public QuestionPO getQuestionInfo(int questionid);
	
	public List<AnswerPO> getAnswerList(int questionid);
	
	public void addPageviewsFromQuestion(QuestionPO qupo);
	
	public void InsertAnswer(AnswerPO aspo);
	
	public AnswerPO getAnswerInfo(int answerid);
	
	public void addPageviewsFromAnswer(AnswerPO aspo);
	
	public void addUserAction(UserActionPO uapo);
	
}

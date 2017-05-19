package com.bjtu.lwx.service;

import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.po.UserActionPO;

public interface QuizService {
	
	public int submitQuestion(QuestionPO qupo);
	
	public void addUserAction(UserActionPO uapo);

}

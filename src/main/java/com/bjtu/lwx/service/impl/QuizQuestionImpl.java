package com.bjtu.lwx.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjtu.lwx.dao.QuestionDao;
import com.bjtu.lwx.dao.UserActionDao;
import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.po.UserActionPO;
import com.bjtu.lwx.service.QuizService;

@Service("quizService")
public class QuizQuestionImpl implements QuizService {
	
	@Resource
	private QuestionDao qudao;
	
	@Resource
	private UserActionDao uadao;
	
	@Override
	public int submitQuestion(QuestionPO qupo) {
		int id = 0;
		qupo.setCreateTime(String.valueOf(new Date().getTime()));
		qupo.setPageviews(0);
		qupo.setAnswerNum(0);
		int result = qudao.insertByQuestion(qupo);
		
		if(result == 1){
			id = qupo.getQuestionid();
		}
		return id;
	}
	@Override
	public void addUserAction(UserActionPO uapo) {
		uapo.setCreateTime(String.valueOf(new Date().getTime()));
		
		uadao.insertAction(uapo);
		
	}
	
}

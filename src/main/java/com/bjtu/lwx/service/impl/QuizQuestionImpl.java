package com.bjtu.lwx.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjtu.lwx.dao.QuestionDao;
import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.service.QuizService;

@Service("quizService")
public class QuizQuestionImpl implements QuizService {
	
	@Resource
	private QuestionDao qudao;
	@Override
	public String submitQuestion(QuestionPO qupo) {
		
		qupo.setCreateTime(String.valueOf(new Date().getTime()));
		qupo.setPageviews(0);
		qupo.setAnswerNum(0);
		qudao.insertByQuestion(qupo);
		
		return "success";
	}

}

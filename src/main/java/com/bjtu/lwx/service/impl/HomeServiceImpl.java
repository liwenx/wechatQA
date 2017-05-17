package com.bjtu.lwx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjtu.lwx.dao.QuestionDao;
import com.bjtu.lwx.po.AnswerPO;
import com.bjtu.lwx.po.QuestionListPO;
import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Resource
	QuestionDao qudao;
	
	@Resource
	QuestionPO qupo;
	
	public List<QuestionListPO> getHotQuestion() {
		
		List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
		lst = qudao.getHotQuestion();
		return lst;
	}
	@Override
	public QuestionPO getQuestionInfo(int questionid) {
		
		List<QuestionPO> lst = new ArrayList<QuestionPO>();
		lst = qudao.getQuestionInfoByQuestionid(questionid);
		qupo = lst.get(0);
		return qupo;
	}
	@Override
	public List<AnswerPO> getAnswerList(int questionid) {
		List<AnswerPO> lst = new ArrayList<AnswerPO>();
		
		lst = qudao.getAnswerListByQuestionid(questionid);
		return lst;
	}
	@Override
	public void addPageviewsFromQuestion(QuestionPO qupo) {
		
		qudao.addPageviewsByQuestionid(qupo);
		
		
	}

}

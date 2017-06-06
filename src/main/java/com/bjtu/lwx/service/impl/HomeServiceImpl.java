package com.bjtu.lwx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjtu.lwx.dao.AnswerDao;
import com.bjtu.lwx.dao.QuestionDao;
import com.bjtu.lwx.dao.UserActionDao;
import com.bjtu.lwx.po.AnswerPO;
import com.bjtu.lwx.po.QuestionListPO;
import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.po.UserActionPO;
import com.bjtu.lwx.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Resource
	QuestionDao qudao;
	
	@Resource
	AnswerDao asdao;
	
	@Resource
	UserActionDao uadao;
	
	@Resource
	QuestionPO qupo;
	
	@Resource
	AnswerPO aspo;
	
	public List<QuestionListPO> getHotQuestion() {
		
		List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
		String createTime = String.valueOf(new Date().getTime());
		lst = qudao.getHotQuestion(createTime);
		return lst;
	}
	
	@Override
	public List<QuestionListPO> getReQuestion(String openid) {
		int num = 0;
		int type = 1;
		int se = 0;
		qupo.setOpenid(openid);
		qupo.setCreateTime(String.valueOf(new Date().getTime()));
		for(int i = 0; i < 7; i ++ ){
			
			qupo.setQuestionType(i+1);
			se = qudao.getActionNum(qupo);
			if( se>= num){
				num = se;
				type = i+1;
			}
			
		}
		
		qupo.setQuestionType(type);
		
		List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
		lst = qudao.getReQuestion(qupo);
		
		
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
	@Override
	public void InsertAnswer(AnswerPO aspo) {
		
		aspo.setPageviews(0);
		aspo.setCreateTime(String.valueOf(new Date().getTime()));
		
		asdao.insertAnswer(aspo);
	}
	@Override
	public AnswerPO getAnswerInfo(int answerid) {
		
		aspo = asdao.getAnswerInfoByAnswerid(answerid).get(0);
		
		return aspo;
	}
	@Override
	public void addPageviewsFromAnswer(AnswerPO aspo) {
		
		asdao.addPageviewsByAnswer(aspo);
		
	}
	@Override
	public void addUserAction(UserActionPO uapo) {
		
		uapo.setCreateTime(String.valueOf(new Date().getTime()));
		
		uadao.insertAction(uapo);
		
		
	}

	
	@Override
	public List<QuestionListPO> searchQuestionList(String searchContent) {
		
		List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
		lst = qudao.getQuestionListByContent(searchContent);
		
		return lst;
	}


}

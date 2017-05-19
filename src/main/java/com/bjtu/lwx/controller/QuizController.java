package com.bjtu.lwx.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.po.UserActionPO;
import com.bjtu.lwx.service.QuizService;

@Controller
@RequestMapping("/wechatapp/quiz")
public class QuizController {
	@Resource
	QuizService quizService;
	@Resource
	UserActionPO uapo;
	
	//提交问题
	@RequestMapping(value="/submitQuestion",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> submitQuestion (QuestionPO qupo,HttpSession httpSession){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
	   	String openid = httpSession.getAttribute("openid")==null?"":httpSession.getAttribute("openid").toString();
//	   	String openid = "oPI3e0Y095xDAwe3KO8AVsXIA_pg";
	   	try {
	   		qupo.setQuestionTitle(new String(qupo.getQuestionTitle().getBytes("iso-8859-1"),"utf-8"));
	   		qupo.setQuestionContent(new String(qupo.getQuestionContent().getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	   	qupo.setOpenid(openid);
	   	
	   	int result = quizService.submitQuestion(qupo);
	  //用户提问操作+1
	   	if (result !=0){
	   		uapo.setActionname("提问");
	   		uapo.setOpenid(openid);
	   		uapo.setQuestionid(result);
	   		quizService.addUserAction(uapo);
	   	}
	  	   	
    	if(result != 0 ){
    		rMap.put("retflag", "0");
    		rMap.put("msg", "问题发布成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "问题发布失败");
    	}
    	return rMap;
		
	}
}

package com.bjtu.lwx.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.lwx.po.AnswerPO;
import com.bjtu.lwx.po.QuestionListPO;
import com.bjtu.lwx.po.QuestionPO;
import com.bjtu.lwx.service.HomeService;

@Controller
@RequestMapping("/wechatapp/home")
public class HomeController {
	
	@Resource
	HomeService homeService;
	@Resource
	QuestionPO qupo;
	
	//热门问题 
	@RequestMapping(value="/getHotQuestion",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getHotQuestion (){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
	   
	   	List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
	   	lst = homeService.getHotQuestion();
	   	
    	if(lst != null){
    		rMap.put("data", lst);
    		rMap.put("retflag", "0");
    		rMap.put("msg", "获取热门问题成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "获取热门问题失败");
    	}
    	return rMap;
		
	}
	
	
	//返回问题详情URL
	@RequestMapping(value="/getQuestionUrl",method = RequestMethod.GET)
    @ResponseBody
	public void getQuestionUrl (@RequestParam(value = "questionid") int questionid,HttpServletRequest request,HttpServletResponse response){
		String result = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if (0 != questionid){
				request.getSession().setAttribute("questionid", questionid); 
				String scheme = request.getScheme();
				String serverName = request.getServerName();
				String path=serverName+request.getContextPath();
				result =scheme+"://"+path+"/wechatapp/page/home/questioninfo.html";
			}
			else{
				result = "wechatapp/page/error.html";
			}
//			request.getRequestDispatcher(result).forward(request, response);
			response.sendRedirect(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		return result;
		
	}
	
	//获取问题信息
	@RequestMapping(value="/getQuestionInfo",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getQuestionInfo (HttpSession httpSession){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
	   	
	   	String questionid1 = httpSession.getAttribute("questionid")==null?"":httpSession.getAttribute("questionid").toString();
	   	int questionid =  Integer.valueOf(questionid1);
	   	//获取问题信息
	   qupo = homeService.getQuestionInfo(questionid);	   	
	   //获取答案信息
		List<AnswerPO> lst = new ArrayList<AnswerPO>();
		lst = homeService.getAnswerList(questionid);
	   
		//浏览量+1
		
		homeService.addPageviewsFromQuestion(qupo);
		
    	if(qupo!= null){
    		rMap.put("questioninfo", qupo);
    		rMap.put("answer", lst);
    		rMap.put("retflag", "0");
    		rMap.put("msg", "获取问题信息成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "获取问题信息失败");
    	}
    	return rMap;
		
	}

}

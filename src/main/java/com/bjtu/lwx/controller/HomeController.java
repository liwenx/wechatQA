package com.bjtu.lwx.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.bjtu.lwx.po.UserActionPO;
import com.bjtu.lwx.service.HomeService;

@Controller
@RequestMapping("/wechatapp/home")
public class HomeController {
	
	@Resource
	HomeService homeService;
	@Resource
	QuestionPO qupo;
	@Resource
	AnswerPO aspo;
	
	@Resource
	UserActionPO uapo;
	
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
	
	//为你推荐
	@RequestMapping(value="/getReQuestion",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getReQuestion (HttpSession httpSession){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
		String openid = httpSession.getAttribute("openid")==null?"":httpSession.getAttribute("openid").toString();
	   	List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
	   	lst = homeService.getReQuestion(openid);
	   	
    	if(lst != null){
    		rMap.put("data", lst);
    		rMap.put("retflag", "0");
    		rMap.put("msg", "获取推荐问题成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "获取推荐问题失败");
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
	   	String openid = httpSession.getAttribute("openid")==null?"":httpSession.getAttribute("openid").toString();
	   	int questionid =  Integer.valueOf(questionid1);
	   	//获取问题信息
	   qupo = homeService.getQuestionInfo(questionid);	   	
	   //获取答案信息
		List<AnswerPO> lst = new ArrayList<AnswerPO>();
		lst = homeService.getAnswerList(questionid);
	   
		//浏览量+1
		
		homeService.addPageviewsFromQuestion(qupo);
		
		//用户浏览操作+1
		uapo.setOpenid(openid);
		uapo.setQuestionid(questionid);
		uapo.setActionname("浏览");
		homeService.addUserAction(uapo);
		
		
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
	
	//发布回答
	@RequestMapping(value="/submitAnswer",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> submitAnswer (@RequestParam(value = "answerContent") String answerContent,HttpSession httpSession,HttpServletRequest request,HttpServletResponse response){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
	   	try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String questionid1 = httpSession.getAttribute("questionid")==null?"":httpSession.getAttribute("questionid").toString();
			int questionid = 0;
			if(questionid1 !=""){
				questionid =  Integer.valueOf(questionid1);
			}
			String openid = httpSession.getAttribute("openid")==null?"":httpSession.getAttribute("openid").toString();
	   	
		   	if (questionid != 0 && openid != ""){
				aspo.setOpenid(openid);
				aspo.setQuestionid(questionid);	
				aspo.setAnswerContent(new String(answerContent.getBytes("iso-8859-1"),"utf-8"));
				homeService.InsertAnswer(aspo);
				
				//用户回答操作+1
				uapo.setOpenid(openid);
				uapo.setQuestionid(questionid);
				uapo.setActionname("回答");
				homeService.addUserAction(uapo);
				
				
				rMap.put("retflag", "0");
	    		rMap.put("msg", "发布回答成功");
		   	}
		   	else{
	    		rMap.put("retflag", "1");
	    		rMap.put("msg", "发布回答失败");
		   	}
    	  	
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	return rMap;
		
	}
	
	
	//返回答案详情URL
	@RequestMapping(value="/getAnswerUrl",method = RequestMethod.GET)
    @ResponseBody
	public void getAnswerUrl (@RequestParam(value = "answerid") int answerid,HttpServletRequest request,HttpServletResponse response){
		String result = null;
		
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if (0 != answerid){
				request.getSession().setAttribute("answerid", answerid); 
				String scheme = request.getScheme();
				String serverName = request.getServerName();
				String path=serverName+request.getContextPath();
				result =scheme+"://"+path+"/wechatapp/page/home/answer.html";
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
	
	//获取答案信息
	@RequestMapping(value="/getAnswerInfo",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getAnswerInfo (HttpSession httpSession){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
	   	
	   	String answerid1 = httpSession.getAttribute("answerid")==null?"":httpSession.getAttribute("answerid").toString();
	   	int answerid =  Integer.valueOf(answerid1);
	   	//获取答案信息
	   aspo = homeService.getAnswerInfo(answerid);	  
	   
		//浏览量+1
	   homeService.addPageviewsFromAnswer(aspo);
		
    	if(aspo!= null){
    		rMap.put("answerinfo", aspo);
    		rMap.put("retflag", "0");
    		rMap.put("msg", "获取答案信息成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "获取答案信息失败");
    	}
    	return rMap;
		
	}
	
	//获取问题搜索结果
	@RequestMapping(value="/searchQuestion",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> searchQuestion (@RequestParam(value = "searchContent") String searchContent){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
	   	
	   	//搜索
	   	List<QuestionListPO> lst = new ArrayList<QuestionListPO>();
	   	try {
			lst = homeService.searchQuestionList(new String(searchContent.getBytes("iso-8859-1"),"utf-8"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		
    	if(lst!= null && !lst.isEmpty()){
    		rMap.put("data", lst);
    		rMap.put("retflag", "0");
    		rMap.put("msg", "获取问题搜索结果成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "获取问题搜索结果失败");
    	}
    	return rMap;
		
	}
	

}

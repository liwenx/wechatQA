package com.bjtu.lwx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjtu.lwx.service.WechatAppService;

@Controller
@RequestMapping("/wechatapp")
public class WeChatAppController {
	
	@Resource
	private WechatAppService wechatService;
	
	//返回微信网页首页
	@RequestMapping(value = { "index" })
	public String getAppIndex(@RequestParam(value = "code") String code,HttpServletRequest request){
		String result = null;
//		System.out.println(code);		
			
		if(null != code && !"".equals(code)){
 			 String openid = wechatService.getUserInfo(code);
			
			if (null != openid){
				request.getSession().setAttribute("openid", openid); 
				result = "wechatapp/page/index";
			}else{
				result = "wechatapp/page/error";
			}
		 }
		return result;
//		return "wechatapp/page/index";
	}
	
	//获取session
	@RequestMapping(value="/getSession",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getSession (HttpSession httpSession){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
    	String openid = httpSession.getAttribute("openid")==null?"":httpSession.getAttribute("openid").toString();
//	   	String openid = "oPI3e0RqmoZvSs-FH5s8H-KE5DlU";
    	if(!"".equals(openid)){
    		rMap.put("retflag", "0");
    		rMap.put("userid", openid);
    		rMap.put("msg", "session获取成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "session获取失败");
    	}
    	return rMap;
		
	}
	
	//获取用户信息
	@RequestMapping(value="/getUserInfo",method = RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getUserInfo (@RequestParam(value = "openid") String openid){
	   	Map<String, Object> rMap = new HashMap<String, Object>();
    	String username = wechatService.getAppUserInfo(openid);
    	if(!"".equals(username)){
    		rMap.put("retflag", "0");
    		rMap.put("username", username);
    		rMap.put("msg", "用户信息获取成功");
    	}else{
    		rMap.put("retflag", "1");
    		rMap.put("msg", "用户信息获取失败");
    	}
    	return rMap;
		
	}
	
}

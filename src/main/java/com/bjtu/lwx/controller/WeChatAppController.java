package com.bjtu.lwx.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			}else{
				result = "wechatapp/error";
			}
		 }
		
		return "wechatapp/index";
	}
}

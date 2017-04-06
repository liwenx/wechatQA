package com.bjtu.lwx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bjtu.lwx.service.WeixinService;
import com.bjtu.lwx.vo.WeixinCheckVO;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	 
	//不用new
	@Resource
	private WeixinService weixinService;
	
	/**
	 * 微信接入校验
	 * @param wxvo
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = { "signature" }, method = RequestMethod.GET)
	public void signature(WeixinCheckVO wxvo,HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		if(weixinService.WeixinCheck(wxvo)){
				out.print(wxvo.getEchostr());
		}
	}
	
	@RequestMapping(value = { "signature" }, method = RequestMethod.POST)
	public void post (HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter(); 
		
		out.println(weixinService.WeixinMessage(req));
		
	}

}

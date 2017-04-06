package com.bjtu.lwx.service;



import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;

import com.bjtu.lwx.vo.WeixinCheckVO;


public interface WeixinService {
	
	//微信接入校验
	public boolean WeixinCheck (WeixinCheckVO wxvo);
	
	//微信消息
	public String WeixinMessage (HttpServletRequest req) throws IOException, DocumentException;

}
	
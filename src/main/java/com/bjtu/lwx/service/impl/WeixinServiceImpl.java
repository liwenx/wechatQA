package com.bjtu.lwx.service.impl;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.bjtu.lwx.service.WeixinService;
import com.bjtu.lwx.util.MessageUtil;
import com.bjtu.lwx.util.WeixinConstant;
import com.bjtu.lwx.vo.WeixinCheckVO;
import com.bjtu.lwx.vo.WeixinMessageVO;


@Service("weixinService")
public class WeixinServiceImpl implements WeixinService{
	@Resource
	private WeixinMessageVO msgvo;
	
	@Override
	public boolean WeixinCheck(WeixinCheckVO wxvo) {
		
		String[] arr = new String[]{WeixinConstant.WEIXIN_TOKEN,wxvo.getTimestamp(),wxvo.getNonce()};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i <arr.length; i ++){
			content.append(arr[i]);
		}
		
		//sha1加密
		String temp = getSha1(content.toString());
		
		//返回与微信参数的比对结果
		return temp.equals(wxvo.getSignature());
	}
	
	//sha1加密
	  public static String getSha1(String str){
	        if(str==null||str.length()==0){
	            return null;
	        }
	        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
	                'a','b','c','d','e','f'};
	        try {
	            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	            mdTemp.update(str.getBytes("UTF-8"));

	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char buf[] = new char[j*2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                buf[k++] = hexDigits[byte0 & 0xf];      
	            }
	            return new String(buf);
	        } catch (Exception e) {
	            // TODO: handle exception
	            return null;
	        }
	    }

	  
	 /**
	  * 回复微信消息
	  */
	@Override
	public String WeixinMessage(HttpServletRequest req) throws IOException, DocumentException {
		
		
		Map<String,String> map = MessageUtil.xmlToMap(req);
		
		msgvo.setFromUserName(map.get("FromUserName"));
		msgvo.setToUserName(map.get("ToUserName"));
		msgvo.setCreateTime(map.get("CreateTime"));
		msgvo.setMsgType(map.get("MsgType"));
		msgvo.setContent(map.get("Content"));
		msgvo.setMsgId(map.get("MsgId"));
		
		String message = null;
		if ("1".equals(msgvo.getContent())){
			
			message = MessageUtil.initText(msgvo.getToUserName(), msgvo.getFromUserName(), "111");			
		}
		return message;
		
	}
	
	
	
}

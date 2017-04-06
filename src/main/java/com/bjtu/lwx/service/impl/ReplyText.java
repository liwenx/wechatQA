package com.bjtu.lwx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bjtu.lwx.api.translate.BaiduTranslateConstant;
import com.bjtu.lwx.api.translate.TransApi;
import com.bjtu.lwx.dao.BaiduTranslateDao;
import com.bjtu.lwx.util.MessageUtil;
import com.bjtu.lwx.vo.WeixinMessageVO;

import net.sf.json.JSONObject;

/**
 * 回复文本消息
 * @author liwenxing
 *
 */
@Component
public class ReplyText {
	
	@Resource  
    private BaiduTranslateDao btDao;  
	public String reply(WeixinMessageVO msgvo) throws Exception{
		
		String message = null;
		String type = msgvo.getContent();
		if ("1".equals(type)){
			message = MessageUtil.initText(msgvo.getToUserName(), msgvo.getFromUserName(), "111");	
		}
		
		//百度翻译服务
		else if ("翻译".equals(type.substring(0, 2))){
			String from = "auto";
			System.out.println(btDao.getAbbByName(type.substring(3,5)));
//			String to = this.btDao.getAbbByName(type.substring(3,5)).get(0).getLanguageAbb();
			String to = "en";
			System.out.println(to);
			
			 TransApi api = new TransApi(BaiduTranslateConstant.APP_ID,BaiduTranslateConstant.SECURITY_KEY);
			 
			 String translateJson = api.getTransResult(type.substring(5,type.length()), from, to);
			 
			 JSONObject jsonObject=JSONObject.fromObject(translateJson);
			 List<Map<String, String>> trans_result = new ArrayList<Map<String,String>>();
			 trans_result = jsonObject.getJSONArray("trans_result");
			 Map<String,String> result = new HashMap();
			 
			 result = trans_result.get(0);
			 
			 
			 StringBuffer sb = new StringBuffer();
			 sb.append(result.get("dst"));
			 
			 message = MessageUtil.initText(msgvo.getToUserName(), msgvo.getFromUserName(),sb.toString());	
			 
		}
		else{
			
		}
		return message;
		
	}

}

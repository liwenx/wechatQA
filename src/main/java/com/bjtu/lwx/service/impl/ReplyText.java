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
import com.bjtu.lwx.util.GetAccessToken;
import com.bjtu.lwx.util.MessageUtil;
import com.bjtu.lwx.vo.TextMessageVO;

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
	
//	@Resource
//	private GetAccessToken gatoken;

	public String reply(TextMessageVO msgvo) throws Exception{

		StringBuffer content = new StringBuffer();
		String message = null;
		String type = msgvo.getContent();
//		String token = gatoken.getAccessToken();
//		System.out.println(token);
		if ("1".equals(type)){
			content.append("菜单");
		}
		
		//百度翻译服务 格式:以 "翻译成XX"开头
		else if (type.length() > 5 && "翻译".equals(type.substring(0, 2))){
			String from = "auto";
			if(null ==  this.btDao.getAbbByName(type.substring(3,5)) || "".equals(this.btDao.getAbbByName(type.substring(3,5)))){
				content.append("抱歉！暂不支持将\n");
				content.append(type.substring(5,type.length())+"\n");
				content.append("翻译成"+type.substring(3,5)+"\n");
				
			}
			//如果支持翻译至该语言
			else {
			String to = this.btDao.getAbbByName(type.substring(3,5)).get(0).getLanguageAbb();
//			System.out.println(to);
			
			 TransApi api = new TransApi(BaiduTranslateConstant.APP_ID,BaiduTranslateConstant.SECURITY_KEY);
			 
			 String translateJson = api.getTransResult(type.substring(5,type.length()), from, to);
			 
			 JSONObject jsonObject=JSONObject.fromObject(translateJson);
			 List<Map<String, String>> trans_result = new ArrayList<Map<String,String>>();
			 trans_result = jsonObject.getJSONArray("trans_result");
			 Map<String,String> result = new HashMap();			 
			 result = trans_result.get(0);			 			 
			 content.append(result.get("dst"));
			}
			 
		}
		//不能识别该条消息
		else{
			content.append("您发送的消息是："+type);
		}
		message = MessageUtil.initText(msgvo.getToUserName(), msgvo.getFromUserName(),content.toString());
		System.out.println(message);
		return message;
		
	}

}

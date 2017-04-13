package com.bjtu.lwx.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bjtu.lwx.vo.ServiceMessageVO;

import net.sf.json.JSONObject;

/**
 * 微信被动回复5秒超时，使用客服接口来回复消息
 * @author liwenxing
 *
 */
@Component
public class CustomService {
	
	@Resource
	private GetAccessToken gatoken;
	
	public String addServiceAccount (ServiceMessageVO smvo){
		
		String access_token = gatoken.getAccessToken();
		
		String url = WeixinConstant.ADDSERVICEACC_URL.replaceAll("ACCESS_TOKEN", access_token);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("touser", smvo.getTouser());
		map.put("msgtype", smvo.getMsgtype());
		map.put("content", smvo.getContent());
		
		String data  = DataFormatConversion.map2json(map);
		JSONObject jsonObject = RequestUtil.doPostStr(url, data);
		
		return null;
		
	}
	
	public void sendTextMessage (){
		  
	}

}

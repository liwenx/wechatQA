package com.bjtu.lwx.util;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bjtu.lwx.dao.AppAccessTokenDao;
import com.bjtu.lwx.po.AppAccessTokenPO;

import net.sf.json.JSONObject;

@Component
public class GetAppAccessToken {
	
	@Resource 
	private AppAccessTokenDao aatdao;
	@Resource
	private AppAccessTokenPO aatpo;
	
	//根据code 获取AccessToken
	public AppAccessTokenPO getAccessTokenByCode(String code){
		
		String url = AppConstant.APP_ACCESSTOKEN
				.replaceAll("APPID", WeixinConstant.APPID)
				.replaceAll("SECRET", WeixinConstant.appSecret)
				.replaceAll("CODE", code);
		JSONObject jsonObject = RequestUtil.doGetStr(url);
		if(jsonObject != null){
			

			//先删除此人的AccessToken
			this.aatdao.deleteAppAccessTokenByOpenid(jsonObject.getString("openid"));
			//再插入
			aatpo.setAccessToken(jsonObject.getString("access_token"));
			aatpo.setCreateTime(String.valueOf(new Date().getTime()));
			aatpo.setExpiresIn(jsonObject.getInt("expires_in"));
			aatpo.setOpenid(jsonObject.getString("openid"));
			aatpo.setRefreshToken(jsonObject.getString("refresh_token"));
			aatpo.setScope(jsonObject.getString("scope"));
			
			this.aatdao.insertAppAccessToken(aatpo);
		}		
		return aatpo;
	}
}

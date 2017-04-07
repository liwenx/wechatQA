package com.bjtu.lwx.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bjtu.lwx.dao.AccessTokenDao;
import com.bjtu.lwx.po.AccessTokenPO;

import net.sf.json.JSONObject;

@Component
public class GetAccessToken {
	
	@Resource
	private AccessTokenDao atdao;
	@Resource
	private AccessTokenPO atpo;
	
	//先从数据库中取access_token,不行就请求微信
	public String getAccessToken(){


		String token = null; 
		List<AccessTokenPO> lst = new ArrayList<AccessTokenPO>();
		lst = this.atdao.getToken();
//		System.out.println(lst.size()+"---------------------------------------------------------------");
		if (null != lst && !"".equals(lst) &&  !lst.isEmpty()){
//		if (0 != lst.size()){
			long datetime = new Date().getTime();
			long creat_time = Long.parseLong(lst.get(0).getCreatTime());
			if(((datetime - creat_time)/1000 -lst.get(0).getExpiresIn() - 200) < 0){
				token = lst.get(0).getToken();
			}
			else{
				token = getAccessTokenByWeixin();
			}
		}
		else{
			token = getAccessTokenByWeixin();
		}
		return token;
	} 
	
	//从微信获取ACCESS_TOKEN
	public String getAccessTokenByWeixin(){
		String token = null;
		
		
		
		String url = WeixinConstant.ACCESS_TOKEN_URL.replace("APPID",WeixinConstant.APPID).replace("APPSECRET", WeixinConstant.appSecret);
		JSONObject jsonObject = RequestUtil.doGetStr(url);
		if(jsonObject != null){
			
			token = jsonObject.getString("access_token");
			//删除数据库中的access_token
			this.atdao.deleteAccessToken();
			atpo.setCreatTime(String.valueOf(new Date().getTime()));
			atpo.setExpiresIn(jsonObject.getInt("expires_in"));
			atpo.setToken(jsonObject.getString("access_token"));
			//将新的access_token 插入数据库
			this.atdao.insertByAccessToken(atpo);
		}
		
		return token;
	}
//		public static void main(String[] args) {
//			 new GetAccessToken().getAccessToken();
//		}
}

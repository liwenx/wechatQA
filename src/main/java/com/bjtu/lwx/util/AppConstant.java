package com.bjtu.lwx.util;

public class AppConstant {
	
	//微信网页首页
	public static final String REDIRECT_URI = "http://16752730ii.iask.in/wechatQA/wechatapp/index";
	
	//1.用户同意授权，获取code
	public static final String  EMPOWER_GETCODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	
	//2.根据code获取AccessToken
	public static final String APP_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	//4.根据AccessToken拉取用户信息
	public static final String APP_GETUSERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
}

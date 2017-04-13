package com.bjtu.lwx.util;


public class WeixinConstant {
	//微信公众平台接入token
	public static final String WEIXIN_TOKEN = "liwenxing";
	
	//黎文兴de公众号
//	public static final String APPID = "wx0afd731fd29ef096";
//	public static final String appSecret = "a85d30120936c7d5547f715f1619aff8";
	
	//测试号
	public static final String APPID = "wxa39a92824a5b3352";
	public static final String appSecret = "b0741d0128348dbeac0af5d4fc2a7c67";
	
	//获取access_token 
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	//获取临时多媒体素材URL http请求方式：POST/FORM，使用https
	// 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	//添加客服帐号
	//http请求方式: POST
	public static final String ADDSERVICEACC_URL = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
	
	// 客服接口-发消息
	//http请求方式: POST
	public static final String SERVICEACCOUNT_SENDMESSIGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	//创建自定义菜单
	//http请求方式: POST
	public static final String  CREATE_MENU= "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	
	//文本消息
	public static final String MESSAGE_TEXT = "text";
	
	//图片消息
	public static final String MESSAGE_IMAGE = "image";
	
	//图文消息
	public static final String MESSAGE_NEWS = "news";
	
	//语音消息
	public static final String MESSAGE_VOICE = "voice";
	
	//视频消息
	public static final String MESSAGE_VIDEO = "video";
	
	//链接消息
	public static final String MESSAGE_LINK = "link";
	
	//位置消息
	public static final String MESSAGE_LOCATION = "location";
	
	//事件推送
	public static final String MESSAGE_EVENT = "event";
	
	//关注
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	
	//取消关注
	public static final String MESSAGE_UNSBUSCRIBE = "unsubscribe";
	
	//菜单点击
	public static final String MESSAGE_CLICK = "CLICK";
	
	//点击菜单跳转链接时的事件推送
	public static final String MESSAGE_VIEW = "VIEW";
}

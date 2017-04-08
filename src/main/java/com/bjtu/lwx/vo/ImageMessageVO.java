package com.bjtu.lwx.vo;

import org.springframework.stereotype.Component;

@Component
public class ImageMessageVO{

	private String ToUserName;
	
	private String FromUserName;
	
	private String CreateTime;
	
	//image
	private String MsgType;
	
	//图片链接（由系统生成）
	private String PicUrl;
	
	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String MediaId;
	
	//消息id，64位整型
	private String MsgId;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
}

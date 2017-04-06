package com.bjtu.lwx.vo;

import org.springframework.stereotype.Component;

/**
 * 微信公众平台接入校验VO
 * @author liwenxing
 *
 */
@Component
public class WeixinCheckVO {
	
	
	private String signature;
	
	private String timestamp;
	
	private String nonce;
	
	private String echostr;
	
	
	public String getSignature() {
		return signature;
	} 
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	@Override
	public String toString() {
		return "WeixinCheckVO [signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
				+ echostr + "]";
	}

	
	

}

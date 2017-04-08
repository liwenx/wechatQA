package com.bjtu.lwx.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bjtu.lwx.util.GetAccessToken;
import com.bjtu.lwx.util.WeixinFetchTempFile;
import com.bjtu.lwx.vo.ImageMessageVO;

/**
 * 回复图片消息 
 * @author liwenxing
 *
 */
@Component
public class ReplyImage {
	
	@Resource
	private GetAccessToken gatoken;
	@Resource
	private WeixinFetchTempFile wxftfile;
	
	public String reply (ImageMessageVO imvo) {
		
		String token = gatoken.getAccessToken();
//		System.out.println(token);
//		File file = new WeixinFetchTempFile().fetchTmpFile(imvo.getMediaId(), imvo.getMsgType(),token);
		 String filename = wxftfile.fetchTmpFile(imvo.getMediaId(), imvo.getMsgType(),token);
		 
		 //获取到用户发的图片，然后进行OCR处理
		
		return null;
		
	}

}

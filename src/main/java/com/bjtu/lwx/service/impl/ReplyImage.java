package com.bjtu.lwx.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bjtu.lwx.util.GetAccessToken;
import com.bjtu.lwx.util.MessageUtil;
import com.bjtu.lwx.util.WeixinFetchTempFile;
import com.bjtu.lwx.vo.ImageMessageVO;
import com.bjtu.lwx.vo.TextMessageVO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

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
	
	@Resource
	private TextMessageVO msgvo;
	
	public String reply (ImageMessageVO imvo) {
		
		String message = null;
		String token = gatoken.getAccessToken();
		String filename = wxftfile.fetchTmpFile(imvo.getMediaId(), imvo.getMsgType(),token);
		 
		 //获取到用户发的图片，然后进行OCR处理
		String path = "e:\\wechatimage\\";
		File imageFile=new File(path+filename);
//		File imageFile=new File(path+"1.png");

		Tesseract instance=new Tesseract();
		instance.setDatapath("E:\\wechatimage\\tessdata");//设置训练库的位置
		instance.setLanguage("chi_sim");//中文识别
		String result = null;
		try {
			 result=instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		message = MessageUtil.initText(imvo.getToUserName(), imvo.getFromUserName(),result);
		return message;
		
	}

}

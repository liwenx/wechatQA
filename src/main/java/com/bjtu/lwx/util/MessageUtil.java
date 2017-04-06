package com.bjtu.lwx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bjtu.lwx.vo.TextMessageVO;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	
	
	/**
	 * xml转成map集合
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */	
	public static Map<String, String>xmlToMap(HttpServletRequest request) throws IOException, DocumentException{
		
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		
		return map;
	}
	
	/**
	 * 文本消息格式
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		
		TextMessageVO text = new TextMessageVO();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(WeixinConstant.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	
	/**
	 * 将文本消息转为xml
	 * @param textmessage
	 * @return
	 */
	public static String textMessageToXml(TextMessageVO textmessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textmessage.getClass());
		return xstream.toXML(textmessage);
	}
	
}

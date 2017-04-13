package com.bjtu.lwx.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class MenuCreate {
	
	/**
	 * 创建微信公众号菜单，链接至网页
	 */
	public static void creatMenu(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", "view");
		map.put("name", "进入主页");
		map.put("url", "http://16752730ii.iask.in/wechatQA/wechatapp/index");		
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		list.add(map);
		
		Map<String,List> rmap = new HashMap<String,List>();
		rmap.put("button", list);
		String url1 = AppConstant.EMPOWER_GETCODE.replaceAll("APPID",WeixinConstant.APPID)
				.replaceAll("REDIRECT_URI", AppConstant.REDIRECT_URI);
//		String str = DataFormatConversion.map2json(rmap);
		String str = "{\"button\":[{\"type\":\"view\",\"name\":\"进入主页\",\"url\":\""+url1+"\"}]}";
		System.out.println(str);
//		String token = new GetAccessToken().getAccessToken();
		String token = "5Jz779aCcKpqJOVVdNagyc-Od7aK_O9nDEfufZLh4Q6EXTyBOShFnsNqMRcfGkb92x-QVTp6b81yp9gb15WYQ6aOdtdyYJJrGmBuY71I-r2I71IYTyAXfmqHDBN0wuPqWKKcAEAZNN";
		String url = WeixinConstant.CREATE_MENU.replaceAll("ACCESS_TOKEN", token);
		JSONObject result = RequestUtil.doPostStr(url,str);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		creatMenu();
	}
}

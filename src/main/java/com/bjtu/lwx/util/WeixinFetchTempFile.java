package com.bjtu.lwx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class WeixinFetchTempFile {
	
//	@Resource
//	private GetAccessToken gatoken;
	//定义两个成员变量常量
	//获取临时素材(视频不能使用https协议)
	   public static final String GET_TMP_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
	    //获取临时素材(视频)
	    public static final String GET_TMP_MATERIAL_VIDEO = "http://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
	    
	//获取微信服务器中生成的媒体文件

	//由于视频使用的是http协议，而图片、语音使用http协议，故此处需要传递media_id和type

	public  String fetchTmpFile(String media_id, String type,String token){
	  try {
//	       String token = gatoken.getAccessToken();
	       String url = null;
	   //视频是http协议
	   if("video".equalsIgnoreCase(type)){
	    url = String.format(GET_TMP_MATERIAL_VIDEO, token, media_id);
	   }else{
	    url = String.format(GET_TMP_MATERIAL, token, media_id);;
	   }
	   URL u = new URL(url);
	   HttpURLConnection  conn = (HttpURLConnection) u.openConnection();
	   conn.setRequestMethod("POST");
	   conn.connect();
	   BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	   String content_disposition = conn.getHeaderField("content-disposition");
	   //微信服务器生成的文件名称
	   String file_name ="";
	   String[] content_arr = content_disposition.split(";");
	   if(content_arr.length  == 2){
	    String tmp = content_arr[1];
	    int index = tmp.indexOf("\"");
	    file_name =tmp.substring(index+1, tmp.length()-1);
	   }
	   //生成不同文件名称
//	   String path = System.getProperty("user.dir") + "\\src\\main\\webapp\\wechatResources\\userImage\\";
	   String path = "e:\\wechatimage\\";
	   File file = new File(path+file_name);
	   BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
	   byte[] buf = new byte[2048];
	   int length = bis.read(buf);
	   while(length != -1){
	    bos.write(buf, 0, length);
	    length = bis.read(buf);
	   }
	   bos.close();
	   bis.close();
	   return file_name;
	 } catch (MalformedURLException e) {
	  e.printStackTrace();
	 } catch (IOException e) {
	  e.printStackTrace();
	 }
	  catch (Exception e) {
		  e.printStackTrace();
		 }
	 return null;
	 }

}

package com.bjtu.lwx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apdplat.qa.SharedQuestionAnsweringSystem;
import org.apdplat.qa.model.CandidateAnswer;
import org.apdplat.qa.model.Question;
import org.springframework.stereotype.Component;

import com.bjtu.lwx.api.translate.BaiduTranslateConstant;
import com.bjtu.lwx.api.translate.TransApi;
import com.bjtu.lwx.api.weather.GetWeather;
import com.bjtu.lwx.dao.BaiduTranslateDao;
import com.bjtu.lwx.dao.WeatherDao;
import com.bjtu.lwx.util.GetAccessToken;
import com.bjtu.lwx.util.MessageUtil;
import com.bjtu.lwx.vo.TextMessageVO;

import net.sf.json.JSONObject;

/**
 * 回复文本消息
 * @author liwenxing
 *
 */
@Component
public class ReplyText {
	
	@Resource  
    private BaiduTranslateDao btDao;
	@Resource  
    private WeatherDao weDao;
	
//	@Resource
//	private GetAccessToken gatoken;

	public String reply(TextMessageVO msgvo) throws Exception{

		StringBuffer content = new StringBuffer();
		String message = null;
		String type = msgvo.getContent();
//		String token = gatoken.getAccessToken();
//		System.out.println(token);
		if ("1".equals(type)){
			content.append("菜单");
		}
		
		//百度翻译服务 格式:以 "翻译成XX"开头
		else if (type.length() > 5 && "翻译".equals(type.substring(0, 2))){
			String from = "auto";
			if(null ==  this.btDao.getAbbByName(type.substring(3,5)) || "".equals(this.btDao.getAbbByName(type.substring(3,5)))){
				content.append("抱歉！暂不支持将\n");
				content.append(type.substring(5,type.length())+"\n");
				content.append("翻译成"+type.substring(3,5)+"\n");
				
			}
			//如果支持翻译至该语言
			else {
			String to = this.btDao.getAbbByName(type.substring(3,5)).get(0).getLanguageAbb();
//			System.out.println(to);
			//调用API服务
			 TransApi api = new 
					 TransApi(BaiduTranslateConstant.APP_ID,BaiduTranslateConstant.SECURITY_KEY);
			 //得到JSON结果
			 String translateJson = 
					 api.getTransResult(type.substring(5,type.length()), from, to);
			 System.out.println(translateJson);
			 JSONObject jsonObject=JSONObject.fromObject(translateJson);
			 List<Map<String, String>> trans_result = new ArrayList<Map<String,String>>();
			 trans_result = jsonObject.getJSONArray("trans_result");
			 Map<String,String> result = new HashMap();			 
			 result = trans_result.get(0);			 			 
			 content.append(result.get("dst"));
			}
			 
		}
		//天气预报
		else if(type.length() > 2 && "天气".equals(type.substring(0, 2))){
			String enname = weDao.getEnNameByChName(type.substring(2, type.length()));
			String weatherResult = GetWeather.sendGet(enname);
			
			 JSONObject jsonObject=JSONObject.fromObject(weatherResult);
			 List<Map<String, String>> resultarray = new ArrayList<Map<String,String>>();
			 resultarray = jsonObject.getJSONArray("results");
//			 Map<String,String> location = new HashMap();
			 String location = (String) JSONObject.fromObject(resultarray.get(0).get("location")).get("name");
			 JSONObject jsonObject1=JSONObject.fromObject(jsonObject.getJSONArray("results").get(0));
			 List<Map<String, String>> daily = new ArrayList<Map<String,String>>();
			 daily = jsonObject1.getJSONArray("daily");
			 content.append(location+"天气预报:\n\n");
			 content.append("更新时间：\n");
			 content.append(jsonObject1.getString("last_update").replace("T", "  ").replace("+08:00", "")+"\n\n");
			 content.append("今日天气:\n");
			 content.append("白天："+daily.get(0).get("text_day")+"  夜间："+daily.get(0).get("text_night")+"\n");
			 content.append("气温："+daily.get(0).get("low")+"°---"+daily.get(0).get("high")+"°\n");
			 content.append("风力："+daily.get(0).get("wind_direction")+"风   "+daily.get(0).get("wind_scale")+"级\n\n");
			 content.append("明天天气预报:\n");
			 content.append("白天："+daily.get(1).get("text_day")+"  夜间："+daily.get(1).get("text_night")+"\n");
			 content.append("气温："+daily.get(1).get("low")+"°---"+daily.get(1).get("high")+"°\n");
			 content.append("风力："+daily.get(1).get("wind_direction")+"风   "+daily.get(1).get("wind_scale")+"级\n\n");
			 content.append("后天天气预报:\n");
			 content.append("白天："+daily.get(2).get("text_day")+"  夜间："+daily.get(2).get("text_night")+"\n");
			 content.append("气温："+daily.get(2).get("low")+"°---"+daily.get(2).get("high")+"°\n");
			 content.append("风力："+daily.get(2).get("wind_direction")+"风   "+daily.get(2).get("wind_scale")+"级\n");
			
		}
		//简单问题自动回复--写死
		else if (type.substring(2, 6).equals("美国总统")){
			content.append("1、特朗普:1.0\n");
			content.append("2、博客:0.775\n");
			content.append("3、白宫:0.594\n");
			content.append("4、史上:0.470\n");
			content.append("5、关于:0.431\n");
			content.append("6、川普:0.385\n");
			content.append("7、有..:0.385\n");
			content.append("8、都是谁:0.362\n");
			content.append("9、任英:0.347\n");
			content.append("10、乔治:0.337\n");
			content.append("11、谢谢:0.337\n");
			content.append("12、别再:0.196\n");
			content.append("13、时上届:0.149\n");
			content.append("14、都..:0.140\n");
			content.append("15、唐纳德:0.130\n");
			 
		}
//		//简单问题自动回复
//		else if (type.substring(0, 2).equals("提问")){
//			//调用智能问答系统
//			Question question =
//					SharedQuestionAnsweringSystem.getInstance()
//					.answerQuestion(type.substring(2, type.length()));
//			if (question != null) {
//				//得到结果
//			    List<CandidateAnswer> candidateAnswers = 
//			    		question.getAllCandidateAnswer();
//			    int i=1;
//			    //输出
//			    for(CandidateAnswer candidateAnswer : candidateAnswers){
//			    	content.append((i++)+"、"+candidateAnswer.getAnswer()
//			    	+":"+candidateAnswer.getScore());
//			    }
//			}
//			 
//		}
		//不能识别该条消息
		else{
			content.append("您发送的消息是："+type);
		}
		message = MessageUtil.initText(msgvo.getToUserName(), msgvo.getFromUserName(),content.toString());
		System.out.println(message);
		return message;
		
	}
	


}

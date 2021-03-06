package com.bjtu.lwx.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjtu.lwx.dao.AppFourGetUserInfoDao;
import com.bjtu.lwx.dao.AppUserInfoDao;
import com.bjtu.lwx.po.AppAccessTokenPO;
import com.bjtu.lwx.po.AppUserInfoPO;
import com.bjtu.lwx.po.QuestionListPO;
import com.bjtu.lwx.service.WechatAppService;
import com.bjtu.lwx.util.AppConstant;
import com.bjtu.lwx.util.DownLoadUserImg;
import com.bjtu.lwx.util.GetAppAccessToken;
import com.bjtu.lwx.util.RequestUtil;

import net.sf.json.JSONObject;

@Service("wechatService")
public class WechatAppServiceImpl implements WechatAppService {
	
	@Resource
	private GetAppAccessToken gaatoken;
	@Resource
	private AppUserInfoPO auipo;
	@Resource
	private AppAccessTokenPO aatpo;
	
	@Resource
	private AppUserInfoDao auidao;
	@Resource
	private AppFourGetUserInfoDao afgdao;
	
	@Override
	public String getUserInfo(String code) {
		String openid = null;
		 aatpo = gaatoken.getAccessTokenByCode(code);
		if (aatpo != null){
			
			String url = AppConstant.APP_GETUSERINFO
					.replaceAll("ACCESS_TOKEN", aatpo.getAccessToken())
					.replaceAll("OPENID", aatpo.getOpenid());
			JSONObject jsonObject = RequestUtil.doGetStr(url);
			
			if(jsonObject != null){
				//更新用户信息
				 openid = jsonObject.getString("openid");
				if(auidao.getAppUserInfoByOpenid(openid) != null){
					return openid;
				}
				else{
					
					auipo.setCity(jsonObject.getString("city"));
					auipo.setCountry(jsonObject.getString("country"));
					auipo.setHeadimgurl(jsonObject.getString("headimgurl"));
					auipo.setNickname(jsonObject.getString("nickname"));
					auipo.setOpenid(jsonObject.getString("openid"));
					auipo.setProvince(jsonObject.getString("province"));
					auipo.setSex(jsonObject.getString("sex"));
					
					auidao.insertUserInfo(auipo);
					String path = this.getClass().getResource("/").getPath().replaceAll("WEB-INF/classes/", "")+"wechatapp/resources/userimg"+File.separator;
					try {
						new DownLoadUserImg().download(auipo.getHeadimgurl(),auipo.getOpenid()+".jpg",path);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			else{
				return null;
			}
		}
		return openid;
	}

	@Override
	public AppUserInfoPO getAppUserInfo(String openid) {
		
		List<AppUserInfoPO> lst = new ArrayList<AppUserInfoPO>();
		 lst = afgdao.getUserInfoByOpenid(openid);
		auipo = lst.get(0);
		return auipo;
	}
}

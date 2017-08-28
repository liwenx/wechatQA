package com.bjtu.lwx.api.translate;

public class BaiduTranslateConstant {
	
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    public static final String APP_ID = "20170406000044270";
    public static final String SECURITY_KEY = "svX9TbBS_QumGDJAWOb3";
    
    public static void main(String[] args) throws Exception {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "黎文兴所有";
        System.out.println(api.getTransResult(query, "auto", "en"));
    }
    
    
}

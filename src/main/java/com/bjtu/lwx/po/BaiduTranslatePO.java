package com.bjtu.lwx.po;

import org.springframework.stereotype.Component;

/**
 * baidutranslate_api 表
 * @author liwenxing
 *
 */

@Component
public class BaiduTranslatePO {
	
	private String languageAbb;
	private String languageName;
	
	public String getLanguageAbb() {
		return languageAbb;
	}
	public void setLanguageAbb(String languageAbb) {
		this.languageAbb = languageAbb;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}

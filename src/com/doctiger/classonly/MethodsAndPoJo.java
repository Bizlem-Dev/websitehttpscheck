package com.doctiger.classonly;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MethodsAndPoJo {
	
	private static final MethodsAndPoJo INSTANCE = new MethodsAndPoJo();

	private String clientIp;
	private String geoplugin_countryCode;
	private String http_url;
	private String https_url;
	private String jsonStr;

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getGeoplugin_countryCode() {
		return geoplugin_countryCode;
	}

	public void setGeoplugin_countryCode(String geoplugin_countryCode) {
		this.geoplugin_countryCode = geoplugin_countryCode;
	}

	public String getHttp_url() {
		return http_url;
	}

	public void setHttp_url(String http_url) {
		this.http_url = http_url;
	}

	public String getHttps_url() {
		return https_url;
	}

	public void setHttps_url(String https_url) {
		this.https_url = https_url;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	 public static MethodsAndPoJo getInstance() {
	        return INSTANCE;
	    }

	public boolean isNullString(String p_text) {
		if (p_text != null && p_text.trim().length() > 0 && !"null".equalsIgnoreCase(p_text.trim())) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}
	
}

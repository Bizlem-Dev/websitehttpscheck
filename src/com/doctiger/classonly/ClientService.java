package com.doctiger.classonly;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ClientService {

	public static final String[] IP_HEADER_CANDIDATES = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" 
		    };
	
	public static final String geoplugin_countryCode="geoplugin_countryCode";
	public static final String fetchSqlHttpUrl = "select https_url from listofcountrieswebsite where code=?";
	
	String getClientIpAddress(HttpServletRequest request,HttpServletResponse response)throws Exception;
	String sendGet(String clientIp)throws Exception;
	
	String getHttpsUrlData(String countryCode);
	
}

package com.doctiger.classonly;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ClientServiceImpl implements ClientService{

	Connection con=null;
	PreparedStatement ps =null;
	ResultSet rs=null;
	
	@Override
	public String getClientIpAddress(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//PrintWriter out=response.getWriter();

		if(request!=null){
			
			if (request.getRemoteAddr() != null && request.getRemoteAddr().length() != 0 && !"unknown".equalsIgnoreCase(request.getRemoteAddr())) {
				 MethodsAndPoJo.getInstance().setClientIp( request.getRemoteAddr() );
				 
				 MethodsAndPoJo.getInstance().setJsonStr( sendGet(MethodsAndPoJo.getInstance().getClientIp()) );
				 if( MethodsAndPoJo.getInstance().isJSONValid(MethodsAndPoJo.getInstance().getJsonStr()) ){
		        		JSONObject obj=new JSONObject(MethodsAndPoJo.getInstance().getJsonStr());
		        		if( obj.length()!=0 && obj!=null ){
		        			if( obj.has(geoplugin_countryCode) ){
		        				MethodsAndPoJo.getInstance().setGeoplugin_countryCode(obj.getString(geoplugin_countryCode));
		        			}
		        		}
		        	}
			}
		}
			
			
		/*for (String header : IP_HEADER_CANDIDATES) {
	        String ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	        	String jsonStr=sendGet(ip);
	        	out.println("jsonStr: " +jsonStr);
	        	if( MethodsAndPoJo.getInstance().isJSONValid(jsonStr) ){
	        		JSONObject obj=new JSONObject(jsonStr);
	        		if( obj.length()!=0 && obj!=null ){
	        			if( obj.has(geoplugin_countryCode) ){
	        				geoplugin_countryCodeLocal=obj.getString(geoplugin_countryCode);
	        			}
	        		}
	        	}
	        }
	    }*/
		
		return getHttpsUrlData(MethodsAndPoJo.getInstance().getGeoplugin_countryCode());
	}

	@Override
	public String sendGet(String clientIp) throws Exception {
		
		 String url = Config.getInstance().getProperty("geopluginurl")+clientIp;

	        HttpURLConnection httpClient =(HttpURLConnection) new URL(url).openConnection();

	        // optional default is GET
	        httpClient.setRequestMethod("GET");
	        BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream())) ;
	            StringBuilder response = new StringBuilder();
	            String line;

	            while ((line = in.readLine()) != null) {
	                response.append(line);
	            }

	              return response.toString();
	}

	@Override
	public String getHttpsUrlData(String countryCode) {
		
		String httpUrl=null;
		try {
			
			int result = 0;
			con=Connect.getConnectionObj();
		    ps =(PreparedStatement) con.prepareStatement(fetchSqlHttpUrl);
		    ps.setString(1, countryCode);
		    rs= ps.executeQuery();
		    
		    while (rs.next()) {
		    	result++;
		    	MethodsAndPoJo.getInstance().setHttp_url( rs.getString("https_url") );
		    }
		    
		    rs.close();
		    
		    if (result != 0) {
		    	httpUrl=MethodsAndPoJo.getInstance().getHttp_url();
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{   //finally block used to close resources
			try{
		         if(ps!=null)
		            con.close();
		      }catch(SQLException se){
		      }// do nothing
			
			try{
				if(con!=null)
		            con.close();
		      }catch(SQLException se){
		    	  se.printStackTrace();
		      }
		}
		return httpUrl;
	}
	
	
}

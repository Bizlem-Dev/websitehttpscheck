package com.doctiger.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doctiger.classonly.ClientServiceImpl;
/**
 * Servlet implementation class DisplayClientInfo
 */
public class DisplayClientInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final ClientServiceImpl dataObj=new ClientServiceImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayClientInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		
		try {
			
			String httpUrl=dataObj.getClientIpAddress(request,response);
			if( httpUrl!=null && httpUrl.length()!=0 ){
				out.println(httpUrl);
			}
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

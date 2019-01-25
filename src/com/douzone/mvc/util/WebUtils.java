package com.douzone.mvc.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils 
{
	// 브라우저가 받아서 가기때문에 url
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException
	{
		response.sendRedirect(url);
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String location) throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher(location); // 어디로 분기할지 정해줘야함
		rd.forward(request, response); // forward는 서블릿에서 jsp로 제어를 넘김
	}
}

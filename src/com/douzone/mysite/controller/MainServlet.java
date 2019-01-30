package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.AbstractActionFactory;
import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.action.main.MainActionFactory;

//@WebServlet("")  // 스프링은 이걸 사용하지않음
public class MainServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

//	@Override
//	public void init(ServletConfig config) throws ServletException 
//	{
//		// config로 init 파라미터가 넘어옴
//		
//	}
	
	@Override
	public void init() throws ServletException 
	{
		String configPath = getServletConfig().getInitParameter("config"); // 파라미터에 이름설정
		System.out.println("init() called " + configPath);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String actionName = request.getParameter("a");

		AbstractActionFactory af = new MainActionFactory();
		Action action = af.getAction(actionName);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

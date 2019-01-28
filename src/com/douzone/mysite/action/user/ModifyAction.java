package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

public class ModifyAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo vo = (UserVo)hs.getAttribute("authuser");
		
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setGender(request.getParameter("gender"));
			
		new UserDao().update(vo);
		
		WebUtils.redirect(request, response, request.getContextPath());
	}

}

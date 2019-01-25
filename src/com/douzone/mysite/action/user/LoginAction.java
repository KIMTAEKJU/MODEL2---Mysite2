package com.douzone.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authuser = new UserDao().get(email, password);
		
		if (authuser == null)
		{
			/*
			 * 인증 실패
 			 */
			
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response,   "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		// 인증 성공 -> 인증 처리
		HttpSession session = request.getSession(true); //true 는 없으면 만들엇서달라
		
		session.setAttribute("authuser", authuser);
		
		/*
		 *  main redirect
		 */
		
		WebUtils.redirect(request, response, request.getContextPath());
	}

}

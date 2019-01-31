package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.vo.UserVo;

public class BoardCommentModifyFormAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo session = (UserVo)hs.getAttribute("authuser");
		
		if (session != null)
			request.setAttribute("session", session);	
		WebUtils.forward(request, response, "/WEB-INF/views/board/commentModify.jsp");
	}

}

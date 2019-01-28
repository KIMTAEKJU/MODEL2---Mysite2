package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.vo.UserVo;

public class BoardReplyFormAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo vo = (UserVo)hs.getAttribute("authuser");
		
		if (vo != null)
		{
			WebUtils.forward(request, response, "/WEB-INF/views/board/reply.jsp");
			return;
		}
		
		WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");

		
	}

}

package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;

public class BoardModifyFormAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		request.setAttribute("no", request.getParameter("no"));
		WebUtils.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	}

}

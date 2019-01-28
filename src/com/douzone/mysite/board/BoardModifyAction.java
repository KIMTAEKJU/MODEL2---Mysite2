package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;

public class BoardModifyAction implements Action 
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		
		new BoardDao().update(Long.parseLong(no), title, content); 
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
	}

}

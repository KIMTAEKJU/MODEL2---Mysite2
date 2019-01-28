package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.UserVo;

public class BoardModifyAction implements Action 
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo vo = (UserVo)hs.getAttribute("authuser");

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		
		if (vo != null)
			new BoardDao().update(Long.parseLong(no), title, content, vo.getNo()); 
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
	}

}

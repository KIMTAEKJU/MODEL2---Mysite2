package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class BoardWriteAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		HttpSession hs = request.getSession();
		
		UserVo uVo = (UserVo) hs.getAttribute("authuser");
		BoardVo bVo = new BoardVo();
		
		new BoardDao().insert(title, content, uVo.getNo());
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
	}

}

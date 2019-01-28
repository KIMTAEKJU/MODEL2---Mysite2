package com.douzone.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class BoardSelectAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		List<BoardVo> list = new BoardDao().get();
		HttpSession session = request.getSession();
		request.setAttribute("list", list);
		request.setAttribute("session", session.getAttribute("authuser"));
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}

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
import com.douzone.mysite.repository.CommentDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

public class BoardViewAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String no = request.getParameter("no");
		new BoardDao().update(Long.parseLong(no));
		List<BoardVo> list = new BoardDao().get(Long.parseLong(no));
		List<CommentVo> listComment = new CommentDao().get(Long.parseLong(no));
		
		HttpSession session = request.getSession();
		
		request.setAttribute("list", list);
		request.setAttribute("listComment", listComment);
		request.setAttribute("session", session.getAttribute("authuser"));
		request.setAttribute("no", no);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}

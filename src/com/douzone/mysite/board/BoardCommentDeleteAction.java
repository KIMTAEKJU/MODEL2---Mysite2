package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.CommentDao;
import com.douzone.mysite.vo.UserVo;

public class BoardCommentDeleteAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo uVo = (UserVo)hs.getAttribute("authuser");
		
		String password = request.getParameter("password");
		String commentNo = request.getParameter("commentNo");
		String boardNo = request.getParameter("boardNo");
		
		System.out.println("password : " + password);
		System.out.println("commentNo : " + commentNo);
		System.out.println("boardNo : " + boardNo);

		if (uVo == null)
			new CommentDao().delete(password, commentNo);
		else
			new CommentDao().deleteLoginComment(String.valueOf(uVo.getNo()), commentNo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&no=" + boardNo);
	}

}

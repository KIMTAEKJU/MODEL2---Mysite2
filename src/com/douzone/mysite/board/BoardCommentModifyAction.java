package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.CommentDao;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

public class BoardCommentModifyAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo uVo = (UserVo)hs.getAttribute("authuser");
		
		String commentNo = request.getParameter("commentNo");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		String boardNo = request.getParameter("boardNo");
		
		System.out.println("commentNo : " + commentNo);
		System.out.println("password : " + password);
		System.out.println("content : " + content);
		System.out.println("boardNo : " + boardNo);

		CommentVo vo = new CommentVo();
		vo.setCommentNo(Long.parseLong(commentNo));
		vo.setPassword(password);
		vo.setContent(content);
		if (uVo != null)
			vo.setUserNo(vo.getUserNo());
		
		new CommentDao().update(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&no=" + boardNo);
		
		
		
	}

}

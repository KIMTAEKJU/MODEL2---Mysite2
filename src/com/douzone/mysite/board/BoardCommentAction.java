package com.douzone.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.repository.CommentDao;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

public class BoardCommentAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo uVo = (UserVo)hs.getAttribute("authuser");
		
		String no = request.getParameter("no"); // 글번호
		String name = request.getParameter("name"); // 닉네임
		String password = request.getParameter("password"); // 패스워드
		String content = request.getParameter("content"); // 내용
		
		CommentVo vo = new CommentVo();
		
		vo.setBoardNo(Long.parseLong(no));
		vo.setName(name);
		vo.setPassword(password);
		vo.setContent(content);
		vo.setUserNo(( uVo != null) ? String.valueOf(uVo.getNo()) : null); 
		
		new CommentDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&no=" + no);
	}

}

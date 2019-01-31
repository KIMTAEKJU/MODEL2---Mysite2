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
import com.douzone.mysite.vo.UserVo;

public class BoardModifyFormAction implements Action
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo vo = (UserVo)hs.getAttribute("authuser"); // 세션에서 유저정보 no, name 가져옴
		
		if (vo != null) // 로그인 안했을때
		{
			String no = request.getParameter("no");
			List<BoardVo> list = new BoardDao().get(Long.parseLong(no));
			System.out.println("fdsfsfdsfdsfsd : " + list.size());
			System.out.println("vo.getNo() : " + vo.getNo());
			System.out.println("list.get(0).getUserNo() : " + list.get(0).getUserNo());
			
			if (list.size() != 0 && vo.getNo() == list.get(0).getUserNo())
			{
			
				request.setAttribute("title", list.get(0).getTitle());
				request.setAttribute("content", list.get(0).getContents());
				
				WebUtils.forward(request, response, "/WEB-INF/views/board/modify.jsp");
				return;
			}
		}
		
		WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");

		
	}

}

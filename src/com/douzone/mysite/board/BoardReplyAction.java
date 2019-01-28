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

public class BoardReplyAction implements Action 
{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		List<BoardVo> list = new BoardDao().get(no);

		if (list.size() == 0) // 아무것도 온게없을때
		{
			WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");
			return;
		}
		
		long gNo = list.get(0).getgNo();
		long oNo = list.get(0).getoNo() + 1; // oNo + 1
		long depth = list.get(0).getDepth() + 1; // depth + 1
		
		HttpSession session = request.getSession();
		UserVo uVo = (UserVo)session.getAttribute("authuser");
		
		if (uVo != null) // 로그인 안했을때
		{
		
			BoardVo voIn = new BoardVo();
			voIn.setTitle(title);
			voIn.setContents(content);
			voIn.setgNo(gNo);
			voIn.setoNo(oNo);
			voIn.setDepth(depth);
			voIn.setUserNo(uVo.getNo());
			
			BoardVo voUp = new BoardVo();
			voUp.setgNo(gNo);
			voUp.setoNo(oNo);
			
			new BoardDao().update(voUp);
			
			new BoardDao().insert(voIn);
			
			WebUtils.redirect(request, response, request.getContextPath() + "/board?a=list");
			return;
		}
		
		WebUtils.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

}

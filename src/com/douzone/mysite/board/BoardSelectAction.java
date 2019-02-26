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
import com.douzone.mysite.repository.BoardPagingFrameWorkDao;
import com.douzone.mysite.vo.BoardPagingFrameWorkVo;
import com.douzone.mysite.vo.BoardVo;

public class BoardSelectAction implements Action
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String kwd = request.getParameter("kwd");
		BoardPagingFrameWorkVo result = null;
		
		System.out.println("mysite2 kwd : " + kwd);
		if (kwd == null) // 검색어가 없으면 공백을 준다   공백을주면 전체 다 나옴
			kwd = "";
		
		kwd = kwd.replaceAll(" ", ""); // 검색어로 공백만 줄경우 다없애버림
		
		result = new BoardPagingFrameWorkDao().getTotalCount(kwd, request);
		
		List<BoardVo> list = null;
		
		list = new BoardDao().get(kwd,
									((result.getPage()-1) * result.getListCount()) + 1,
										result.getListCount());
		
		//List<BoardVo> commentCount = new BoardDao().getCommentCount(list.get(0).getNo());
		
		HttpSession session = request.getSession();
		request.setAttribute("list", list);
		request.setAttribute("session", session.getAttribute("authuser"));
		request.setAttribute("BoardPagingFrameWorkVo", result);
		request.setAttribute("kwd", kwd);
		//request.setAttribute("commentCount", commentCount);
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}

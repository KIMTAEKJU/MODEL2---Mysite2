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
		String kwd = request.getParameter("kwd");
		List<BoardVo> totalCountList = null;
		
		if (kwd == null)
			// 총 게시물수 DB에서 받아옴 검색어가 없을때
			totalCountList = new BoardDao().get();
		else
		{
			kwd = kwd.replaceAll(" ", "");
			// 총 게시물수 DB에서 받아옴 검색어가 있을때
			totalCountList = new BoardDao().getRetrievingSearchedItems(kwd);
		}
		
		// 총 게시물 수
		long totalCount = totalCountList.get(0).getTotalCount();
		
		// 화면에 보여줄 게시물수
		int listCount = 5;
		
		// 필요한 총 페이지수
		long totalPage = (totalCount % listCount > 0) ?   
							(totalCount / listCount) + 1 : 
								totalCount / listCount;
		// 화면에 보여줄 페이지수
		int pageCount = 5; 
		
		// 현재 페이지
			
		int getPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 0;
		System.out.println("getPage : " + getPage);
		int page = (int) ((getPage < 1) ? 1 : (getPage > totalPage) ? totalPage : getPage);
		System.out.println("page : " + page);
		// 시작 페이지
		int startPage = (( (page-1) / pageCount) * pageCount) + 1;

		// 마지막 페이지
		int endPage = startPage + pageCount - 1;
		
		System.out.println("totalCount : " + totalCount);
		System.out.println("listCount : " + listCount);
		System.out.println("totalPage : " + totalPage);
		System.out.println("pageCount : " + pageCount);
		System.out.println("page : " + page);
		System.out.println("startPage : " + startPage);
		System.out.println("endPage : " + endPage);
		
		List<BoardVo> list = null;
		
		if (kwd == null)
			list = new BoardDao().get("", ((page-1) * listCount) + 1, listCount); // 보여줄 게시물들
		
		else
			list = new BoardDao().get(kwd, ((page-1) * listCount) + 1, listCount);
		
		HttpSession session = request.getSession();
		request.setAttribute("list", list);
		request.setAttribute("session", session.getAttribute("authuser"));
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("listCount", listCount);
		request.setAttribute("page", page);
		if (kwd != null)
			request.setAttribute("kwd", kwd);
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}

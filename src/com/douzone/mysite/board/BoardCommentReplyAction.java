package com.douzone.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.CommentDao;
import com.douzone.mysite.vo.CommentVo;
import com.douzone.mysite.vo.UserVo;

public class BoardCommentReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		HttpSession hs = request.getSession();
		UserVo uVo = (UserVo)hs.getAttribute("authuser");
		
		String name = request.getParameter("name");
		String password = request.getParameter("password"); 
		String content = request.getParameter("content");
		String commentNo = request.getParameter("commentNo");
		String boardNo = request.getParameter("boardNo");
		
		System.out.println("name : " + name);
		System.out.println("password : " + password);
		System.out.println("content : " + content);
		System.out.println("commentNo : " + commentNo);
		System.out.println("boardNo : " + boardNo);
		
		List<CommentVo> list = new CommentDao().get(commentNo); // 대댓글 달려는 댓글의 정보가져옴
		
		long gNo = list.get(0).getgNo(); // g_no
		long oNo = list.get(0).getoNo(); // o_no
		long depth = list.get(0).getDepth(); // depth
		
		

		
		String check = new CommentDao().check(gNo, oNo, depth);
		
		System.out.println("check : " + check);
		
		if (check == null) // 맨밑으로 댓글
		{
			oNo = new CommentDao().getMaxONO(gNo).getoNo(); // 가장 큰 oNo를 받아서 저장
		}
		else // 대댓글
		{
			oNo = Long.parseLong(check);
			System.out.println("Update : " + new CommentDao().updateONOGreaterThanEqual(gNo, oNo));
		}
		
		depth++;
		
		CommentVo vo = new CommentVo();
		vo.setContent(content);
		if (name == null)
			vo.setName(uVo.getName());
		else
			vo.setName(name);
		vo.setBoardNo(Long.parseLong(boardNo));
		
		if (uVo != null)
		{
			vo.setUserNo(String.valueOf(uVo.getNo()));
			vo.setPassword(null);
		}
		else
		{
			vo.setUserNo(null);
			vo.setPassword(password);
		}
		
		vo.setgNo(gNo);
		vo.setoNo(oNo);
		vo.setDepth(depth);
		
		System.out.println("gNo : " + gNo);
		System.out.println("oNo : " + oNo);
		System.out.println("depth : " + depth);
		
		new CommentDao().insertReplyComment(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/board?a=view&no=" + boardNo);
		
	}

}

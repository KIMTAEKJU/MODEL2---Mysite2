package com.douzone.mysite.action.guestbook;

import java.awt.print.Book;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.GuestBookDao;
import com.douzone.mysite.vo.GuestBookVo;

import net.sf.json.JSONObject;

public class AjaxDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		System.out.println("AjaxDeleteAction  no : " + no);
		System.out.println("AjaxDeleteAction  password : " + password);
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(Long.parseLong(no));
		vo.setPassword(password);
		
		int noVal = new GuestBookDao().delete(vo); // 삭제후 no를 다시 받아옴
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("result", noVal);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().println(jsonObject);
	
	}

}

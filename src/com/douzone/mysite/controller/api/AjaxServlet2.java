package com.douzone.mysite.controller.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.UserVo;

import net.sf.json.JSONObject;

@WebServlet("/ajax2")
public class AjaxServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// java object -> json string
		UserVo vo = new UserVo();
		
		vo.setNo(10);
		vo.setName("둘리");
		vo.setEmail("dooly@gmail.com"); // TODO 생성자 호출
		vo.setGender("male");
		
		JSONObject jsonObject = JSONObject.fromObject(vo); // vo 라는 object로부터 json을 만들겠다
		String jsonString = jsonObject.toString();
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

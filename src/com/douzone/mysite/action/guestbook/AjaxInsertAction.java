package com.douzone.mysite.action.guestbook;

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

public class AjaxInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		String password = request.getParameter("password");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setMessage(message);
		vo.setPassword(password);
		
		GuestBookDao dao = new GuestBookDao();
		long no = dao.insert(vo); // 여기선 no만 가져오지만
		System.out.println("AjaxInsertAction   no : " + no);
		GuestBookVo newVo = dao.get(no); // 나중에 다른것들이 필요할지모르니까 다른것들을 가져옴
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", newVo);
		
		response.setContentType("application/json; charset=UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.getWriter().println(jsonObject.toString());
		
	}

}

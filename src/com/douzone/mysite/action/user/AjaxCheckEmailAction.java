package com.douzone.mysite.action.user;

import java.io.IOException; 
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.UserDao;
import com.douzone.mysite.vo.UserVo;

import net.sf.json.JSONObject;

public class AjaxCheckEmailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String email = request.getParameter("email");
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email);
		
		boolean bExist = vo != null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exist", bExist); // 통신용 DTO는 여기
		
		JSONObject jsonObject = JSONObject.fromObject(map); // vo 라는 object로부터 json을 만들겠다
		String jsonString = jsonObject.toString();
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);
	}

}

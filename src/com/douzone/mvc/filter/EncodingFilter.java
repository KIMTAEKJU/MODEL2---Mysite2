package com.douzone.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/")  "/"로 해놓으면 모든경로를 다포함
public class EncodingFilter implements Filter 
{
	private String encoding;
	
	public void destroy() 
	{
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		/*
		 * request 처리
		 */
		request.setCharacterEncoding(encoding); // 서블릿의 공통적으로 처리해야할부분들을 여기서 처리
											   // 여기서 처리함으로써 서블릿은 자신의 비즈니스에 집중할수있다
											   // 스프링에서는 알아서 이런것들 다처리해줌
		chain.doFilter(request, response);
		
		/*
		 * response 처리
		 */
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
		System.out.println("Encoding Filter Init Initialized...");
		
		encoding = fConfig.getInitParameter("encoding"); // 파라미터안주면 널일수도있음 없는경우도 생각
		
		if (encoding == null)
			encoding = "UTF-8";
	}

}

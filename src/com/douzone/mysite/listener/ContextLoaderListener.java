package com.douzone.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


//@WebListener //서블릿처럼 자동등록해주는거
public class ContextLoaderListener implements ServletContextListener 
{

    public void contextDestroyed(ServletContextEvent arg0)  
    { 
    	
    }

	
    public void contextInitialized(ServletContextEvent servletContextEvent)  
    { 
    	String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	
    	System.out.println("Container Starts.... " + contextConfigLocation);
    }
	
}

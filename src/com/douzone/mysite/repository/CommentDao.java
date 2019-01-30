package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.CommentVo;

public class CommentDao 
{
	public List<CommentVo> get(long no)
	{
		CommentVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CommentVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select name, contents, write_date, user_no from comment where board_no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 String name = rs.getString(1);
				 String content = rs.getString(2);
				 String writeDate = rs.getString(3);
				 String userNo = rs.getString(4);
				
				 result = new CommentVo();
				 result.setName(name);
				 result.setContent(content);
				 
				 list.add(result);
			 }
		} 
		catch (SQLException e) 
		{
			System.out.println("error : " + e);
		}
		finally 
		{
			try 
			{
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public boolean insert(CommentVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "insert into comment values(null, ?, now(), ?, ?, ?, ?, (select ifnull(max(g_no)+1,1) from comment a), 1, 0)";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, vo.getContent());
			 pstmt.setLong(2, vo.getBoardNo());
			 pstmt.setString(3, vo.getUserNo());
			 pstmt.setString(4, vo.getName());
			 pstmt.setString(5, vo.getPassword());
			 
			 int count = pstmt.executeUpdate();
			 result = count == 1;
		} 
		catch (SQLException e) 
		{
			System.out.println("error : " + e);
		}
		finally 
		{
			try 
			{
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private static Connection getConnection() throws SQLException
	{
		Connection conn = null;
		
		try 
		{
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // 제대로 로딩됐는지 확인
			
			// 2. 연결하기 (Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
		return conn;
	}
}

package com.douzone.mysite.repository;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.GuestBookVo;

public class GuestBookDao 
{
	public boolean insert(GuestBookVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "insert into guestbook values (null, ?, password(?), ?, now())";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, vo.getName());
			 pstmt.setString(2, vo.getPassword());
			 pstmt.setString(3, vo.getMessage());
			 
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
	
	public boolean delete(GuestBookVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "delete from guestbook where no = ? and password=password(?)";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, vo.getNo());
			 pstmt.setString(2, vo.getPassword());
			 
			 int count = pstmt.executeUpdate();
			 result = count == 1;
		} 
		catch (SQLException e) 
		{
			System.out.println("error 여기 : " + e);
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
	
	public List<GuestBookVo> getList()
	{
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select no, name, reg_date, message from guestbook order by reg_date desc";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 long no = rs.getLong(1);
				 String name = rs.getString(2);
				 String regDate = rs.getString(3);
				 String message = rs.getString(4);
				 
				 GuestBookVo guVo = new GuestBookVo();
				 guVo.setNo(no);
				 guVo.setName(name);
				 guVo.setRegDate(regDate);
				 guVo.setMessage(message);
				 
				 list.add(guVo);
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

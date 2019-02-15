package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.GuestBookVo;
import com.douzone.mysite.vo.UserVo;

public class UserDao 
{
	
	public UserVo get(String email)
	{
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select no, name from user where email=?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, email);
			 
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 long no = rs.getLong(1);
				 String name = rs.getString(2);
				 
				 result = new UserVo();
				 result.setNo(no);
				 result.setName(name);
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
		
		return result;
	}
	
	public boolean update(UserVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
			 String sql2 = "update user set name = ?, gender = ? where no = ?";
			 

			 if (vo.getPassword().equals(""))
			 {
				 pstmt = conn.prepareCall(sql2);
				 pstmt.setString(1, vo.getName());
				 pstmt.setString(2, vo.getGender());
				 pstmt.setLong(3, vo.getNo());

			 }
			 else
			 {
				 pstmt = conn.prepareCall(sql);
				 pstmt.setString(1, vo.getName());
				 pstmt.setString(2, vo.getPassword());
				 pstmt.setString(3, vo.getGender());
				 pstmt.setLong(4, vo.getNo());

			 }
			 			 
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
	
	public boolean insert(UserVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "insert into user values (null, ?, ?, ?, ?, now())";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, vo.getName());
			 pstmt.setString(2, vo.getEmail());
			 pstmt.setString(3, vo.getPassword());
			 pstmt.setString(4, vo.getGender());
			 
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
	
	public UserVo get(String email, String password)
	{
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select no, name from user where email=? and password=?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, email);
			 pstmt.setString(2, password);
			 
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 long no = rs.getLong(1);
				 String name = rs.getString(2);
				 
				 result = new UserVo();
				 result.setNo(no);
				 result.setName(name);
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
		
		return result;
	}
	
	public UserVo get(Long no)
	{
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select no, name, email, gender from user where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 long nos = rs.getLong(1);
				 String name = rs.getString(2);
				 String email = rs.getString(3);
				 String gender = rs.getString(4);
				 
				 
				 result = new UserVo();
				 result.setNo(nos);
				 result.setName(name);
				 result.setEmail(email);
				 result.setGender(gender);
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

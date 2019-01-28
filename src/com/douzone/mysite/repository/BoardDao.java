package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardDao 
{
	public boolean update(long no)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "update board set hit = hit + 1 where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 
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
	
	public boolean update(BoardVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "update board set o_no = ? + 1 where g_no = ? and o_no >= ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, vo.getoNo());
			 pstmt.setLong(2, vo.getgNo());
			 pstmt.setLong(3, vo.getoNo());
			 
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
	
	public boolean update(long no, String title, String content)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "update board set title = ?, contents = ? where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, title);
			 pstmt.setString(2, content);
			 pstmt.setLong(3, no);
			 System.out.println("no : " + no);
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
	
	public boolean delete(long no, long userNo, long userNoFromSession)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "delete from board where no = ? and ? = ?";
			 System.out.println("userNo : " + userNo);
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 pstmt.setLong(2, userNo);
			 pstmt.setLong(3, userNoFromSession);
			 
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
	
	public boolean insert(String title, String content, long no)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "insert into board values (null, ?, ?, now(), 0, (select ifnull(max(g_no)+1,1) from board a), 1, 0, ?)";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, title);
			 pstmt.setString(2, content);
			 pstmt.setLong(3, no);
			 
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
	
	public boolean insert(BoardVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "insert into board values (null, ?, ?, now(), 0, ?, ?, ?, ?)";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, vo.getTitle());
			 pstmt.setString(2, vo.getContents());
			 pstmt.setLong(3, vo.getgNo());
			 pstmt.setLong(4, vo.getoNo());
			 pstmt.setLong(5, vo.getDepth());
			 pstmt.setLong(6, vo.getUserNo());
			 
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
	
	public List<BoardVo> get(long no)
	{
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select title, contents, user_no from board where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 String title = rs.getString(1);
				 String contents = rs.getString(2);
				 long userNo = rs.getLong(3);
				 
				 result = new BoardVo();
				 result.setTitle(title);
				 result.setContents(contents);
				 result.setUserNo(userNo);
				 
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
	
	public List<BoardVo> get(String no)
	{
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select g_no, o_no, depth from board where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, Long.parseLong(no));
			 rs = pstmt.executeQuery();
			 
			 if (rs.next())
			 {
				 long gNo = rs.getLong(1);
				 long oNo = rs.getLong(2);
				 long depth = rs.getLong(3);
				 
				 result = new BoardVo();
				 result.setgNo(gNo);
				 result.setoNo(oNo);
				 result.setDepth(depth);
				 
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
	
	public List<BoardVo> get()
	{
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select a.title, b.name, a.hit, a.write_date, a.depth, a.contents, a.no, a.user_no, a.o_no" + 
					 	  "	from board a, user b" + 
					 	  "		where a.user_no = b.no" +
					 	  "			order by a.g_no desc, a.o_no asc";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 String title = rs.getString(1);
				 String name = rs.getString(2);
				 long hit = rs.getLong(3);
				 String write_Date = rs.getString(4);
				 long depth = rs.getLong(5);
				 String contents = rs.getString(6);
				 long no = rs.getLong(7);
				 long userNo = rs.getLong(8);
				 long oNo = rs.getLong(9);
				 
				 result = new BoardVo();
				 result.setTitle(title);
				 result.setName(name);
				 result.setHit(hit);
				 result.setWrite_Date(write_Date);
				 result.setDepth(depth);
				 result.setContents(contents);
				 result.setNo(no);
				 result.setUserNo(userNo);
				 result.setoNo(oNo);
				 
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

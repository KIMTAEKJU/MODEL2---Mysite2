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
	public boolean updateONOGreaterThanEqual(long gNo, long oNo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 String sql = "update comment set o_no = o_no + 1 where g_no = ? and o_no >= ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, gNo);
			 pstmt.setLong(2, oNo);
			 
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
	
	public CommentVo getMaxONO(long gNo)
	{
		CommentVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select max(o_no) + 1 from comment where g_no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, gNo);
			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 long maxONO = rs.getLong(1);
		
				 result = new CommentVo();
				 result.setoNo(maxONO);
				 
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
	
	public String check(long gNo, long oNo, long depth)
	{
		String result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CommentVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select min(o_no) from comment where g_no = ? and o_no > ? and depth <= ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, gNo);
			 pstmt.setLong(2, oNo);
			 pstmt.setLong(3, depth);

			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 result = rs.getString(1);
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
	
	public boolean deleteLoginComment(String userNo, String commentNo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "delete from comment where no = ? and user_no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, commentNo);
			 pstmt.setString(2, userNo);
			 
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
	
	public boolean delete(String password, String commentNo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "delete from comment where no = ? and password = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, commentNo);
			 pstmt.setString(2, password);
			 
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
	
	public boolean update(CommentVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 String sql = null;
			 if (vo.getPassword() != null)
				 sql = "update comment set contents = ? where no = ? and password = ?";
			 else 
				 sql = "update comment set contents = ? where no = ? and password is ? and user_no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, vo.getContent());
			 pstmt.setLong(2, vo.getCommentNo());
			 pstmt.setString(3, vo.getPassword());
			 
			 if (vo.getPassword() == null)
				 pstmt.setString(4, vo.getUserNo());
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
	
	public List<CommentVo> get(String no)
	{
		CommentVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<CommentVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "select no, g_no, o_no, depth from comment where no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, no);
			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 long commentNo = rs.getLong(1);
				 long gNo = rs.getLong(2);
				 long oNo = rs.getLong(3);
				 long depth = rs.getLong(4);
		
				 result = new CommentVo();
				 result.setNo(commentNo);
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
			 
			 String sql = "select no, name, contents, write_date, user_no, board_no, depth from comment where board_no = ? order by g_no asc, o_no asc";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 long commentNo = rs.getLong(1);
				 String name = rs.getString(2);
				 String content = rs.getString(3);
				 String writeDate = rs.getString(4);
				 String userNo = rs.getString(5);
				 long boardNo = rs.getLong(6);
				 long depth = rs.getLong(7);
				 
				 result = new CommentVo();
				 result.setNo(commentNo);
				 result.setName(name);
				 result.setContent(content);
				 result.setWriteDate(writeDate);
				 result.setUserNo(userNo);
				 result.setBoardNo(boardNo);
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
	
	public boolean insertReplyComment(CommentVo vo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 //(select ifnull(max(g_no)+1,1) from comment a)
			 String sql = "insert into comment values(null, ?, now(), ?, ?, ?, ?, ?, ?, ?)";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 System.out.println("vo.getoNo : " + vo.getoNo());
			 
			 pstmt.setString(1, vo.getContent());
			 pstmt.setLong(2, vo.getBoardNo());
			 pstmt.setString(3, vo.getUserNo());
			 pstmt.setString(4, vo.getName());
			 pstmt.setString(5, vo.getPassword());
			 pstmt.setLong(6, vo.getgNo());
			 pstmt.setLong(7, vo.getoNo());
			 pstmt.setLong(8, vo.getDepth());
			 
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

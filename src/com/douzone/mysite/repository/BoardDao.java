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
	
	public boolean update(long no, String title, String content, long sessionUserNo)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "update board set title = ?, contents = ? where no = ? and user_no = ?";
			 
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setString(1, title);
			 pstmt.setString(2, content);
			 pstmt.setLong(3, no);
			 pstmt.setLong(4, sessionUserNo);
			 
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
	
	public boolean delete(long no, long userNoFromSession)
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			 conn = getConnection();
			 
			 String sql = "delete from board where no = ? and user_no = ?";
			 pstmt = conn.prepareCall(sql);
			 
			 pstmt.setLong(1, no);
			 pstmt.setLong(2, userNoFromSession);
			 
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
	
//	public List<BoardVo> getCommentCount(long boardNo)
//	{
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		List<BoardVo> list = new ArrayList<>();
//		try 
//		{
//			 conn = getConnection();
//			 
//			 String sql = "select b.no, count(*) from comment a, board b where a.board_no = b.no group by b.no";
//			 
//			 pstmt = conn.prepareCall(sql);
//			 
//			 pstmt.setLong(1, boardNo);
//			 
//			 rs = pstmt.executeQuery();
//			 
//			 while (rs.next())
//			 {
//				 long boardNos = rs.getLong(1);
//				 long commentCount = rs.getLong(2);
//				 
//				 BoardVo vo = new BoardVo();
//				 vo.setNo(boardNos);
//				 vo.setCommentCount(commentCount);
//				 
//				 list.add(vo);
//				 
//			 }
//			 
//		} 
//		catch (SQLException e) 
//		{
//			System.out.println("error : " + e);
//		}
//		finally 
//		{
//			try 
//			{
//				if (rs != null)
//					rs.close();
//				if (pstmt != null)
//					pstmt.close();
//				if (conn != null)
//					conn.close();
//			} 
//			catch (SQLException e) 
//			{
//				e.printStackTrace();
//			}
//		}
//		
//		return list;
//	}
	
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
	
	public List<BoardVo> get(String kwd, int startPage, int listCount)
	{
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 System.out.println(kwd);
			 String sql = "SELECT \r\n" + 
			 		"    a.title,\r\n" + 
			 		"    b.name,\r\n" + 
			 		"    a.hit,\r\n" + 
			 		"    a.write_date,\r\n" + 
			 		"    a.depth,\r\n" + 
			 		"    a.contents,\r\n" + 
			 		"    a.no,\r\n" + 
			 		"    a.user_no,\r\n" + 
			 		"    a.o_no,\r\n" + 
			 		"    (SELECT \r\n" + 
			 		"    COUNT(*) '댓글수'\r\n" + 
			 		"FROM\r\n" + 
			 		"    comment c,\r\n" + 
			 		"    board d\r\n" + 
			 		"WHERE\r\n" + 
			 		"    c.board_no = d.no AND d.no = a.no) '댓글수'\r\n" + 
			 		"FROM\r\n" + 
			 		"    board a,\r\n" + 
			 		"    user b\r\n" + 
			 		"WHERE\r\n" + 
			 		"    a.user_no = b.no\r\n" + 
			 		"        AND (a.title LIKE '%%'\r\n" + 
			 		"        OR a.contents LIKE '%%'\r\n" + 
			 		"        OR b.name LIKE '%%')\r\n" + 
			 		"GROUP BY a.no\r\n" + 
			 		"ORDER BY a.g_no DESC , a.o_no ASC\r\n" + 
			 		"limit ?, ?";
			 
			 pstmt = conn.prepareCall(sql);
			 pstmt.setInt(1, startPage-1);
			 pstmt.setInt(2, listCount);
			 
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
				 long commentCount = rs.getLong(10);
				 
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
				 result.setCommentCount(commentCount);
				 
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
	
	public List<BoardVo> getTotalCount(String kwd)
	{
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardVo> list = new ArrayList<>();
		
		try 
		{
			 conn = getConnection();
			 String sql = "SELECT \r\n" + 
				 		"    COUNT(*)\r\n" + 
				 		"FROM\r\n" + 
				 		"    (SELECT \r\n" + 
				 		"        COUNT(*)\r\n" + 
				 		"    FROM\r\n" + 
				 		"        board a, user b\r\n" + 
				 		"    WHERE\r\n" + 
				 		"        a.user_no = b.no\r\n" + 
				 		"            AND (a.title LIKE '%" + kwd + "%'\r\n" + 
				 		"            OR a.contents LIKE '%" + kwd + "%'\r\n" + 
				 		"            OR b.name LIKE '%" + kwd + "%')\r\n" + 
				 		"    GROUP BY a.no\r\n" + 
				 		"    ORDER BY a.g_no DESC , a.o_no ASC) a";
			 
			 pstmt = conn.prepareCall(sql);

			 rs = pstmt.executeQuery();
			 
			 while (rs.next())
			 {
				 long totalCount = rs.getLong(1);
				 
				 
				 result = new BoardVo();
				 result.setTotalCount(totalCount);
				 
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

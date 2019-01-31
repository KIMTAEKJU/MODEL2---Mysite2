package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.douzone.mysite.vo.BoardPagingFrameWorkVo;
import com.douzone.mysite.vo.BoardVo;

public class BoardPagingFrameWorkDao
{
	
	public BoardPagingFrameWorkVo getTotalCount(String kwd, HttpServletRequest request)
	{
		BoardPagingFrameWorkVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

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

				// 화면에 보여줄 게시물수
				int listCount = 5;

				// 필요한 총 페이지수
				long totalPage = (totalCount % listCount > 0) ?   
						(totalCount / listCount) + 1 : 
							totalCount / listCount;
				// 화면에 보여줄 페이지수
				int pageCount = 5; 

				// 현재 페이지

				// 값을 가져와서 체크
				int getPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 0;
				System.out.println("getPage : " + getPage);

				// 범위를 벗어난값을 바로 잡아줌
				int page = (int) ((getPage < 1) ? 1 : (getPage > totalPage) ? totalPage : getPage);
				System.out.println("page : " + page);

				// 시작 페이지
				int startPage = (( (page-1) / pageCount) * pageCount) + 1;

				// 마지막 페이지
				int endPage = startPage + pageCount - 1;

				System.out.println("totalCount : " + totalCount);
				System.out.println("listCount : " + listCount);
				System.out.println("totalPage : " + totalPage);
				System.out.println("pageCount : " + pageCount);
				System.out.println("page : " + page);
				System.out.println("startPage : " + startPage);
				System.out.println("endPage : " + endPage);


				result = new BoardPagingFrameWorkVo();
				result.setTotalCount(totalCount);
				result.setListCount(listCount);
				result.setTotalPage(totalPage);
				result.setPageCount(pageCount);
				result.setGetPage(getPage);
				result.setPage(page);
				result.setStartPage(startPage);
				result.setEndPage(endPage);
				
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

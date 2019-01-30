<%@page import="com.douzone.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	long totalPage = (long)request.getAttribute("totalPage");
	int startPage = (int)request.getAttribute("startPage");
	int endPage = (int)request.getAttribute("endPage");

%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board" method="post">
					<input type="hidden" name="a" value="list">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${list}" var="vo" varStatus = "status">
					
							<tr>
								<td>${(status.index + (page - 1) * listCount) + 1} </td>
								<td style="padding-left:${20 * vo.depth}px">
								
									<c:if test="${vo.oNo != 1 }">
										<img src="/mysite2/assets/images/reply.png"/>
									</c:if>
								<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${vo.no}">${vo.title}</a></td>
								<td>${vo.name}</td>
								<td>${vo.hit}</td>
								<td>${vo.write_Date}</td>
								<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}" class="del"><img src="/mysite2/assets/images/recycle.png"></a></img></td>
							</tr>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				 <!-- 프리비어스 페이지를 계산해줘야함 시작과 끝이 몇인지 계산해라
														1~5면 5까지 게시물이있는지 확인 현재페이지도 넘겨줘야함 -->
				<div class="pager">
					<ul>
					<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page - 1}">◀</a></li>
					<%
						for (int i = startPage; i <= ((endPage < totalPage) ? endPage : totalPage) ; i++)
						{
					%>
						<%
							if (i == (int)(request.getAttribute("page")))
							{
						%>
								<li class="selected"><a href="${pageContext.servletContext.contextPath }/board?a=list&page=<%=i%>"><%=i %></a></li>
						<%
							}
							else
							{
						%>
								<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=<%=i%>"><%=i %></a></li>
						<%
							}
						%>
					<%
						}
					%>	
					<li><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${page + 1}">▶</a></li>
					
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
			
				<c:if test="${!empty authuser }">
						<a href="${pageContext.servletContext.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
				</c:if>
				
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>	
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
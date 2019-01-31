<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@page import="com.douzone.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<div id="board" class="board-form">
			<c:forEach items="${list}" var="vo" varStatus = "status">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								${vo.contents}
							</div>
						</td>
					</tr>
			
				</table>
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board?a=replyform&no=${no}">답글달기</a>
					<a href="${pageContext.servletContext.contextPath }/board?a=list&page=1">글목록</a>
					
					<!-- if (sessionVo != null && list.get(0).getUserNo() == sessionVo.getNo()) -->
					
					<c:if test="${!empty session && vo.userNo == session.no}">
						<a href="${pageContext.servletContext.contextPath }/board?a=modifyform&no=${no}">글수정</a>
					</c:if>
				</div>
				<table>
					<c:forEach items="${listComment}" var="vo" varStatus="status">
						<tr>
							<td>
								<c:choose>
									<c:when test="${vo.userNo != null }">
										<strong style="color:red;">${vo.name}</strong> ${vo.writeDate } 
									</c:when>
									
									<c:otherwise>
										<strong>${vo.name}</strong> ${vo.writeDate } 
									</c:otherwise>
								</c:choose>
								<a href="${pageContext.servletContext.contextPath }/board?a=commentModifyform&commentNo=${vo.no}&boardNo=${vo.boardNo}">수정</a> 
								
								<c:choose>
									<c:when test="${session == null }">
										<a href="${pageContext.servletContext.contextPath }/board?a=commentDeleteform&commentNo=${vo.no}&boardNo=${vo.boardNo}&userNo=${vo.userNo}">삭제</a><br/>
									</c:when>
									
									<c:otherwise>
										<a href="${pageContext.servletContext.contextPath }/board?a=commentDelete&commentNo=${vo.no}&boardNo=${vo.boardNo}">삭제</a><br/>
									</c:otherwise>
								</c:choose>
								
								
								<span>${vo.content}</span>
								<hr>
								
							</td>
						</tr>
						
					</c:forEach>
				</table>
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board">
					<input type = "hidden" name = "a" value="comment">
					<input type = "hidden" name = "no" value="${no}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">댓글</th>
						</tr>
						<tr>
							<td class="label">닉네임</td>
							<c:choose>
								<c:when test="${session == null }">
									<td><input type="text" name="name" value="" ></td>
								</c:when>
								
								<c:otherwise>
									<td>${session.name }</td>
								</c:otherwise>
							</c:choose>
							
							<c:if test="${session == null }">
								<td class="label">비밀번호</td>
								<td><input type="text" name="password" value=""></td>
							</c:if>	
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<input type="submit" value="등록">
					</div>
				</form>	
			</div>
		</div>
		</c:forEach>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
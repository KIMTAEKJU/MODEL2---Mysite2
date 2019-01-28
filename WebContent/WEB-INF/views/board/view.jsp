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
					<a href="${pageContext.servletContext.contextPath }/board?a=list">글목록</a>
					
					<!-- if (sessionVo != null && list.get(0).getUserNo() == sessionVo.getNo()) -->
					
					<c:if test="${!empty session && vo.userNo == session.no}">
						<a href="${pageContext.servletContext.contextPath }/board?a=modifyform&no=${no}">글수정</a>
					</c:if>
				</div>
			</div>
		</div>
		</c:forEach>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
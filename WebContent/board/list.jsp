<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<style>
div {
	width: 70%;
	margin:0 auto;
}
table tr, th, td {
	border: 1px solid #444444;
}

table {
	border-collapse: collapse;
	border-top: 3px solid #168;
	margin: auto;
	width: 100%;
}

th {
	color: #168;
	background: f0f6f9;
	text-align: center;
	padding: 10px;
}

td {
	color: #444444;
	padding: 8px;
	text-align: left;
}

ul {
	width: 600px;
	height: 50px;
	margin: 50px auto;
}

li {
	list-style: none;
	text-align: center;
}


</style>
<body>
	<div>
		<h3 align="center">게시판 목록</h3>
	</div>
	<div>
	<form action="board-checkdelete.do" method="post">
		<table>
			<tr>
				<th align="center" width="5%">No.</th>
				<th align="center" width="50%">제목</th>
				<th align="center" width="10%">작성자</th>
				<th align="center" width="15%">작성일</th>
				<th align="center" width="10%">조회수</th>
				<th align="center" width="10%">일괄삭제</th>
			</tr>
			
			<c:forEach items="${plist}" var="item"  varStatus="status">
				
				<tr>
					<td>${item.b_idx}</td>
					<td><a href="board-view.do?b_idx=${item.b_idx}"> ${item.b_title}</a></td>
					<td>${item.b_writer}</td>
					<td>${item.b_date}</td>
					<td></td>
					<td><input type="checkbox" name="del-id" value="${item.b_idx}" ></td>
				</tr>
			</c:forEach>

		</table>

		<p align="right">${Bpagination.page}/${Bpagination.lastPage }</p>

		<button type="button" onclick="location.href='board/reg.jsp'">글쓰기</button>
		<span><input type="submit" value="삭제"></span>
		</form>
	</div>

	<div>
		<ul>
			<c:forEach var="i" begin="${Bpagination.startPage }" end="${Bpagination.endPage }" step="1">
				<c:choose>
					<c:when test="${i==Bpagination.page}">
						<span style= "font-weight:bold;">${i}</span>
					</c:when>
					<c:when test= "${i!=Bpagination.page }">
						<span><a href="board-list.do?page=${i}">${i}</a></span>
					</c:when>
				</c:choose>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
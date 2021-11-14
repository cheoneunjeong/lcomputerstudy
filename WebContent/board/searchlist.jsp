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
	width: 60%;
	margin: 0 auto;
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
		<form action="search.do" method="post" >
			<select name="f">
				<option ${(f=="b_title")?"selected" : "" } value="b_title">제목</option>
				<option ${(f=="u_idx")?"selected" : "" } value="u_idx">작성자</option>
			</select> 
			<input type="text" name="search" value="${search}">
			<input type ="submit" value="검색">
		</form>
	</div>
	<br>
	<div>
		<form action="board-checkdelete.do" method="post">
			<table>
				<tr>
					<th align="center" width="5%">No.</th>
					<th align="center" width="50%">제목</th>
					<th align="center" width="10%">작성자</th>
					<th align="center" width="20%">작성일</th>
					<th align="center" width="10%">조회수</th>
					<th align="center" width="5%">일괄삭제</th>
				</tr>

				<c:forEach items="${plist}" var="item" varStatus="status">

					<tr>
						<td>${item.b_idx}</td>
						<td><a href="board-view.do?b_idx=${item.b_idx}">
								${item.b_title}</a></td>
						<td>${item.u_idx}</td>
						<td>${item.b_date}</td>
						<td>${item.hit}</td>
						<td><input type="checkbox" name="del-id"
							value="${item.b_idx}"></td>
					</tr>
				</c:forEach>

			</table>

			<p align="right">${Bpagination.page}/${Bpagination.lastPage }</p>

			<button type="button" onclick="location.href='board/reg.jsp'">글쓰기</button>
			<span><input type="submit" value="삭제"></span>
			<button type="button" onclick="location.href='user/login-result.jsp'">돌아가기</button>
		</form>
	</div>

	<div>
	<form action="search.do" method="post" >
		<ul>
			<c:forEach var="i" begin="${Bpagination.startPage }"
				end="${Bpagination.endPage }" step="1">
				<c:choose>
					<c:when test="${i==Bpagination.page}">
						<span style="font-weight: bold;">${i}</span>
					</c:when>
					<c:when test="${i!=Bpagination.page }">
						<input type="hidden" name ="f" value="${f}">
						<input type="hidden" name ="search" value="${search}">
						<span><input type ="submit" name ="page" value="${i}"></span>
					</c:when>
				</c:choose>
			</c:forEach>
		</ul>
		</form>
	</div>
</body>
</html>
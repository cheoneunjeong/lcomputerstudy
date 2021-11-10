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
		<table>
			<tr>
				<th align="center" width="10%">No.</th>
				<th align="center" width="60%">제목</th>
				<th align="center" width="10%">작성자</th>
				<th align="center" width="10%">작성일</th>
				<th align="center" width="10%">조회수</th>
			</tr>
			<tr>
				<td>1</td>
				<td>제목입니다.</td>
				<td>관리자</td>
				<td>2021-11-09</td>
				<td>0</td>
			</tr>
			<tr>
				<td>1</td>
				<td>제목입니다.</td>
				<td>관리자</td>
				<td>2021-11-09</td>
				<td>0</td>
			</tr>
			<tr>
				<td>1</td>
				<td>제목입니다.</td>
				<td>관리자</td>
				<td>2021-11-09</td>
				<td>0</td>
			</tr>
			<tr>
				<td>1</td>
				<td>제목입니다.</td>
				<td>관리자</td>
				<td>2021-11-09</td>
				<td>0</td>
			</tr>
		</table>
	</div>
	
	<div>
		<p align="right">0/0</p>
	</div>

	<div>
		<button type="button" onclick="location.href='reg.jsp'">글쓰기</button>
		<button>삭제</button>
	</div>

	<div>
		<ul>
			<c:forEach var="i" begin="${Bpagination.startPage }" end="${Bpagination.endPage }" step="1">
			
			</c:forEach>
		</ul>
	</div>
</body>
</html>
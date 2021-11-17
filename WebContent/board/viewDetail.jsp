<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
div {
width: 50%;
margin: 0 auto;
}
	table {
		border: 1px solid #444444;
		border-collapse: collapse;
		width: 100%;
		margin: 0 auto;
	}
	th, td {
		border: 1px solid #444444;
	}
</style>
<body>
<div>
	<table>
		<tr>
			<th align="center">${Post.b_title }</th>
		</tr>
		<tr>
			<td align="right">${Post.u_idx}  조회수 : ${Post.hit}</td>
		</tr>
		<tr>
			<td align="right">${Post.b_date}</td>
		</tr>
		<tr>
			<td align="center">${Post.b_content}</td>
		</tr>
	</table>
</div>
<div>
<br>
	<button type="button" onclick="location.href='board-fix.do?u_idx=${Post.u_idx}&&b_idx=${Post.b_idx}'">수정</button>
	<button type="button" onclick="location.href='board-delete.do?u_idx=${Post.u_idx}&&b_idx=${Post.b_idx}'">삭제</button>
</div>
</body>
</html>
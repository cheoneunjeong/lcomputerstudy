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
width: 70%;
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
			<td align="right">${Post.b_writer}</td>
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
	<button type="button" onclick="location.href='board-list.do'">돌아가기</button>
</div>
</body>
</html>
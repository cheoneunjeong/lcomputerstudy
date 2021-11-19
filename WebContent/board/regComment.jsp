<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<style>
	fieldset {
		width: 50%;
		margin: 0 auto;
	}
</style>
<body>
<h2 align="center">답글작성</h2>
<div>
<fieldset>
<form action="reg-Comment2.do" method="post">
	<p> 제목 <input type="text" name="title"> </p>
	<p> ${bidx}번 답글 </p>
	내용<br>
	<textarea rows="20" cols="50" name="content"></textarea>
	<input type="hidden" name="cgroups" value="${cgroups}">
	<input type="hidden" name="orders" value="${orders}">
	<input type="hidden" name="depth" value="${depth}">
	
	<br>
	<span> <input type="submit" value="등록"> </span>
	<button type="button" onclick="location.href='/lcomputerstudy/board-list.do'">돌아가기</button>
</form>
</fieldset>
</div>
</body>
</html>
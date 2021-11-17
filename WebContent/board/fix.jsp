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
<h2 align="center">수정</h2>
<div>
<fieldset>
<form action="board-fix2.do" method="post">
	<p> 제목 <input type="text" name="title" > </p>
	<p> 작성자 : ${uidx} / 게시글 번호 : ${bidx} <input type="hidden" name="bidx" value = "${bidx}"> </p>
	내용<br>
	<textarea rows="20" cols="50" name="content"></textarea>
	<br>
	<span> <input type="submit" value="수정하기"> </span>
	<button type="button" onclick="location.href='/lcomputerstudy/board-list.do'">돌아가기</button>
</form>
</fieldset>
</div>
</body>
</html>
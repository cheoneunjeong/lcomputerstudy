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
<h2 align="center">글작성</h2>
<div>
<fieldset>
<form action="reg.do" method="post">
	<p> 제목 <input type="text" name="title"> </p>
	내용<br>
	<textarea rows="20" cols="50" name="content"></textarea>
	<br>
	<span> <input type="submit" value="등록"> </span>
	<span> <input type="submit" value="수정"> </span>
	<span> <input type="submit" value="삭제"> </span>
</form>
</fieldset>
</div>
</body>
</html>
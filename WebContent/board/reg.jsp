<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	
</style>
<body>
<form action="reg.do" method="post">
	<p> 제목 <input type="text" name="title"> </p>
	내용<br>
	<textarea rows="20" cols="50" name="contents"></textarea>
	<br>
	<span> <input type="submit" value="글작성"> </span>
	<span> <input type="submit" value="수정"> </span>
	<span> <input type="submit" value="삭제"> </span>
</form>
</body>
</html>
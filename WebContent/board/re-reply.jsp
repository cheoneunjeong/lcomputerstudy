<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="reg-Re-relply2.do" method="post">
<input type="text" name="content"> 
<input type="hidden"name="c_num" value="${c_num }"> 
<input type="hidden"name="b_idx" value="${b_idx }"> 
<input type="hidden"name="groups" value="${groups }"> 
<input type="hidden"name="orders" value="${orders }"> 
<input type="hidden"name="depth" value="${depth }"> 
<input type="submit"value="등록">
</form>

</body>
</html>
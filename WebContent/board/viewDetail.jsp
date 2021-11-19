<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	.a {
		border: 1px solid #444444;
		border-collapse: collapse;
		width: 100%;
		margin: 0 auto;
	}

</style>
<body>
<div>
	<table class="a">
		<tr>
			<th class="a" align="center">${Post.b_title }</th>
		</tr>
		<tr>
			<td class="a" align="right">${Post.u_idx}  조회수 : ${Post.hit}</td>
		</tr>
		<tr>
			<td class="a" align="right">${Post.b_date}</td>
		</tr>
		<tr>
			<td class="a" align="center">${Post.b_content}</td>
		</tr>
	</table>
</div>
<div align="right">
<br>
	<button type="button" onclick="location.href='board-fix.do?u_idx=${Post.u_idx}&&b_idx=${Post.b_idx}'">수정</button>
	<button type="button" onclick="location.href='board-delete.do?u_idx=${Post.u_idx}&&b_idx=${Post.b_idx}'">삭제</button>
	<button type="button" onclick="location.href='reg-Comment.do?b_idx=${Post.b_idx}&&groups=${Post.groups}&&orders=${Post.orders}&&depth=${Post.depth}'">답글 작성</button>
</div>
<br>
<div>
<form action="reg-reply.do" method="post">
		<p> 0 개의 댓글 </p>
	
		<p> <input type="text" name="content">
			<input type="hidden" name="bidx" value="${Post.b_idx }">
			<input type="submit" value="등록">
		</p>
<br>
<hr>
</form>
		<c:forEach items="${re}" var="re" varStatus="status">
			<table>
					<tr>
						<td>${re.u_idx}</td>
					</tr>
					<tr>
						<td>${re.c_date}</td>
					</tr>
					<tr>
						<td>${re.c_content}</td>
					</tr>
					<tr>
						<td><br>
							<button type="button">답글작성</button>
							</td>
					</tr>
			</table>
		</c:forEach>
		<hr>	
</div>
</body>
</html>
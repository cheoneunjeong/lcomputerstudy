<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록2</title>
</head>
<style>
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	ul {
		width:600px;
		height:50px;
		margin:10px auto;
	}
	li {
	 	list-style:none;
	 	width:50px;
	 	line-height:50px;
	 	border:1px solid #ededed;
	 	float:left;
	 	text-align:center;
	 	margin:0 5 px;
	 	border-radius:5px;
	 }
	 
</style>
<body>
<h1>회원 목록</h1>
<form action="set-manager.do" method="post">
<table>
	<tr>	
		<td colspan="4"> 전체 회원 수 : ${pagination.userCount} </td>
	<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>관리자 설정 <input type="submit" value="V"></th>
		<tr>
		<c:forEach items="${list}" var="item" varStatus="status">
			 <tr>
				<td><a href="user-detail.do?u_idx=${item.u_idx}">${item.u_idx} </a></td>
				<td>${item.u_id}</td>
				<td>${item.u_name}</td>
				<td><input type="checkbox" name="mids" ${(item.manager == 1)? "checked= 'checked'" : "" }
							value="${item.u_idx}"> </td>
		     </tr>
		 </c:forEach>
	</table>
	<c:set var="ids" value=""/>
	<c:forEach var="n" items="${list}">
	<c:set var="ids" value="${ids} ${n.u_idx}"></c:set>
	</c:forEach>
	<input type="hidden" name="ids" value="${ids}">
	</form>
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.prevPage >= 1}">
					<li style="">
						<a href="user-list.do?page=${pagination.prevPage}">
							◀
						</a>
					</li>
				</c:when>
			</c:choose>
			
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
						<c:when test="${ pagination.page eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a href="user-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${ pagination.nextPage <= pagination.lastPage }">
					<li style="">
						<a href="user-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>
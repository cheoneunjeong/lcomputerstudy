<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

			<p>${count}개의 댓글</p>


			<p>
				<textarea rows="5" cols="50" ></textarea> 
				<button type="button" class="ReplyReg">등록</button>
			</p>
			<br>
		<c:forEach items="${list}" var="list" varStatus="status">
			<table>
				<tr>
					<td>${list.con}</td>
				</tr>
				<tr>
					<td>${list.c_date}</td>
				</tr>
				<tr>
					<td>작성자 : ${list.u_idx}</td>
				</tr>
				<tr> 
					<td>
				
				 		<button type="button" class="btnReply">답글작성</button>
						<button type="button" onclick="location.href='delete-reply.do?c_num=${list.c_num}&&b_idx=${list.b_idx}&&u_idx=${list.u_idx}'">삭제</button>
					</td> 
				</tr>
				<tr style="display:none;"> 
					<td>
						<textarea rows="5" cols="50"></textarea>
				 		<button type="button" class="btnReplyReg" groups="${list.groups}" order="${list.orders}" depth="${list.depth}">작성</button>
						<button type="button">취소</button>
					</td> 
				</tr>
			</table>
		</c:forEach>
		<hr>

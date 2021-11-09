package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet("*.do") // test
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;

		command = checkSession(request, response, command);

		int usercount = 0;
		int page = 1;
		String pw = null;
		String idx = null;

		switch (command) {
		case "/user-list.do":
			String reqPage = request.getParameter("page");
			if (reqPage != null)
				page = Integer.parseInt(reqPage);

			UserService userService = UserService.getInstance();
			ArrayList<User> list = userService.getUsers(page);
			Pagination pagination = new Pagination(page);

			request.setAttribute("list", list);
			request.setAttribute("pagination", pagination);

			view = "user/list";
			break;

		case "/user-insert.do":
			view = "user/insert";
			break;

		case "/user-insert-process.do":
			User user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("password"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-"
					+ request.getParameter("tel3"));
			user.setU_age(request.getParameter("age"));

			userService = UserService.getInstance();
			userService.insertUser(user);

			view = "user/insert-result";
			break;

		case "/user-login.do":
			view = "user/login";
			break;

		case "/user-login-process.do":
			idx = request.getParameter("login_id");
			pw = request.getParameter("login_password");

			userService = UserService.getInstance();
			user = userService.loginUser(idx, pw);

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				view = "/user/login-result";
			} else {
				view = "/user/login-fail";
			}
			break;

		case "/user/logout.do":
			HttpSession session = request.getSession();
			session.invalidate();

			view = "/user/login";
			break;

		case "/access-denied.do":
			view = "/user/access-denied";
			break;

		case "/board/reg.do":

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			UserService userservice = UserService.getInstance();
			userservice.reg(title, content);

			view = "/board/view";
			break;
		}

		RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
		rd.forward(request, response);
	}

	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {

		HttpSession session = request.getSession();

		String[] authList = { "/user-list.do", "/user-insert.do", "/user-insert-process.do", "/user-detail.do",
				"/user-edit.do", "/user-edit-process.do", "/logout.do" };

		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}

		return command;
	}
}

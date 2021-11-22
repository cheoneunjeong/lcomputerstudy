package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.dao.BoardHitDAO;
import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.BSpagination;
import com.lcomputerstudy.testmvc.vo.Bpagination;
import com.lcomputerstudy.testmvc.vo.Detail;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Post;
import com.lcomputerstudy.testmvc.vo.Reply;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet("*.do")
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
		String url = null;
		
		command = checkSession(request, response, command);

		int page = 1;
		String pw = null;
		String idx = null;
		Post post = null;
		HttpSession session = null;
		User user = null;
		BoardService boardService = null;
		ArrayList<Post> list = null;
		Reply reply = null;
		Detail detail = null;
		int count = 0;
		String content = null;
		
		switch (command) {
		case "/user-list.do":
			String reqPage = request.getParameter("page");
			if (reqPage != null)
				page = Integer.parseInt(reqPage);

			UserService userService = UserService.getInstance();
			ArrayList<User> ulist = userService.getUsers(page);
			Pagination pagination = new Pagination(page);

			request.setAttribute("list", ulist);
			request.setAttribute("pagination", pagination);

			view = "user/list";
			break;

		case "/user-insert.do":
			view = "user/insert";
			break;

		case "/user-insert-process.do":
			user = new User();
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
				session = request.getSession();
				session.setAttribute("user", user);

				view = "/user/login-result";
			} else {
				view = "/user/login-fail";
			}
			break;

		case "/user/logout.do":
			session = request.getSession();
			session.invalidate();

			view = "login";
			break;

		case "/access-denied.do":
			view = "/user/access-denied";
			break;

		case "/board/reg.do":
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}

			user = (User)session.getAttribute("user");
			
			post = new Post();
			post.setB_title(request.getParameter("title"));
			post.setB_content(request.getParameter("content"));
			post.setB_date_timestamp(new Timestamp(System.currentTimeMillis()));
			post.setU_idx(user.getU_idx());
		
			boardService = BoardService.getInstance();
			boardService.reg(post);
			
			view = "/board/regRs";
			
			break;
	
			
		case "/board-list.do" :
			
			String page_=request.getParameter("page");
			if(page_ !=null)
				page= Integer.parseInt(page_);
			else page=1;
			
			boardService = BoardService.getInstance();
			list = boardService.getPost(page);
			Bpagination Bpagination = new Bpagination(page);
			
			request.setAttribute("Bpagination", Bpagination);
			request.setAttribute("plist", list);
		
			view = "/board/list";
			break;
			
		case "/board-view.do" :
			
			String idx_ = request.getParameter("b_idx");
			int bidx = Integer.parseInt(idx_);

			boardService = BoardService.getInstance();
			detail = boardService.getPostDetail(bidx);
			count = boardService.getReplyCount(bidx);
			
			ArrayList replys = detail.getList();
			post = detail.getPost();
		
			request.setAttribute("Post", post);
			request.setAttribute("replys", replys);
			request.setAttribute("count", count);
			
			view = "/board/viewDetail";
			break;
			
		case "/board-delete.do" :
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}
				
			user = (User)session.getAttribute("user");
			
			String uidx_ = request.getParameter("u_idx");
			int uidx = Integer.parseInt(uidx_);
			
			String bidx_ = request.getParameter("b_idx");
			bidx = Integer.parseInt(bidx_);
			
			if((user.getU_idx() == uidx) || (user.getManager() == 1)) {
			
			boardService = BoardService.getInstance();
			boardService.deletePost(bidx);
			
			view = "/board/delete";
			}
			else view = "/board/noaccess";
			
			break;
			
			
		case "/board-checkdelete.do":
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}
				
			user = (User)session.getAttribute("user");

			if(user.getManager() == 1) {
			
			String[] delIds = request.getParameterValues("del-id");
			request.setAttribute("delIds", delIds);
			
			boardService = BoardService.getInstance();
			boardService.checkdelete(delIds);
			
			
			view = "/board/delete";
			}
			
			else view = "/board/noaccess";
			
			
			break;
			
		case "/board-fix.do" :
			
			uidx = Integer.parseInt(request.getParameter("u_idx"));
			bidx = Integer.parseInt(request.getParameter("b_idx"));
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}
				
			user = (User)session.getAttribute("user");
			
			if(user.getU_idx() == uidx) {
			
			request.setAttribute("bidx", bidx);
			view = "/board/fix";
			}
			
			else view = "/board/noaccess";
			
			break;
			
		case "/board-fix2.do" :
			
			post = new Post();
			post.setB_title(request.getParameter("title"));
			post.setB_content(request.getParameter("content"));
			post.setB_idx(Integer.parseInt(request.getParameter("bidx")));
			post.setB_date_timestamp(new Timestamp(System.currentTimeMillis()));
			
			request.setAttribute("post", post);
			
			boardService = BoardService.getInstance();
			boardService.fixPost(post);

			view = "/board/fixRs";
			break;
			
		case "/search.do" :
			
			page_=request.getParameter("page");
			if(page_ !=null)
				page= Integer.parseInt(page_);
			else page=1;
			
			String f = request.getParameter("f");
			String search_ = request.getParameter("search");
			
			String search = "";
			if( search_ != null) search = search_;
			
			boardService = BoardService.getInstance();
			list = boardService.searchPost(page, f, search);
			
			BSpagination Bspagination = new BSpagination(page, f, search);
					
			request.setAttribute("plist", list);
			request.setAttribute("Bpagination", Bspagination);
			request.setAttribute("f", f);
			request.setAttribute("search", search);
			
			
			view = "/board/searchlist";
			
			break;	
			
		case "/set-manager.do" :
			
			String[] mids = request.getParameterValues("mids");
			request.setAttribute("mids", mids);
			
			userService = UserService.getInstance();
			userService.setmanager(mids);
			
			url ="http://localhost:8080/lcomputerstudy/user-list.do";
			
			break;
			
		case "/reg-Comment.do":
			
			bidx = Integer.parseInt(request.getParameter("b_idx"));
			int cgroups = Integer.parseInt(request.getParameter("groups"));
			int orders = Integer.parseInt(request.getParameter("orders"));
			int depth = Integer.parseInt(request.getParameter("depth"));
			

			request.setAttribute("bidx", bidx);
			request.setAttribute("cgroups", cgroups);
			request.setAttribute("orders", orders);
			request.setAttribute("depth", depth);
			
			view = "board/regComment";
			
			break;
			
		case "/reg-Comment2.do" :
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}
			
			user = (User)session.getAttribute("user");
			
			post = new Post();
			post.setB_title(request.getParameter("title"));
			post.setB_content(request.getParameter("content"));
			post.setB_date_timestamp(new Timestamp(System.currentTimeMillis()));
			post.setU_idx(user.getU_idx());
			
			cgroups = Integer.parseInt(request.getParameter("cgroups"));
			orders = Integer.parseInt(request.getParameter("orders"));
			depth = Integer.parseInt(request.getParameter("depth"));
		
			boardService = BoardService.getInstance();
			boardService.regComment(post, cgroups, orders, depth);
			
			view = "/board/regRs";
			
			break;

			
		case "/reg-reply.do" :
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}
			
			user = (User)session.getAttribute("user");
			int b_idx = Integer.parseInt(request.getParameter("bidx"));
			
			reply = new Reply();
			reply.setB_idx(b_idx);
			reply.setU_idx(user.getU_idx());
			reply.setC_date_timestamp(new Timestamp(System.currentTimeMillis()));
			reply.setC_content(request.getParameter("content"));
			
			boardService = BoardService.getInstance();
			boardService.regReply(reply);
			
			url = "http://localhost:8080/lcomputerstudy/board-view.do?b_idx="+b_idx;
			
			break;
			
		case "/delete-reply.do" :
			
			int c_num = Integer.parseInt(request.getParameter("c_num"));
			int u_idx = Integer.parseInt(request.getParameter("u_idx"));			
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
			}
			
			user= (User)session.getAttribute("user");
			if(user.getU_idx() == u_idx || user.getManager() == 1)
				{
			
				boardService = BoardService.getInstance();
				boardService.deleteReply(c_num);
			
				url = "http://localhost:8080/lcomputerstudy/board-view.do?b_idx="+b_idx;
				
				break;
				}
			
			else view = "/board/noaccess";
			
			break;
			
		case "/reg-Re-relply.do" :
			
			c_num = Integer.parseInt(request.getParameter("c_num"));
			b_idx = Integer.parseInt(request.getParameter("b_idx"));		
			cgroups = Integer.parseInt(request.getParameter("groups"));
			orders = Integer.parseInt(request.getParameter("orders"));
			depth = Integer.parseInt(request.getParameter("depth"));
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
				}
			
			request.setAttribute("c_num", c_num);
			request.setAttribute("b_idx", b_idx);
			request.setAttribute("groups", cgroups);
			request.setAttribute("orders", orders);
			request.setAttribute("depth", depth);
			
			view = "/board/re-reply";
			
		case "/reg-Re-relply2.do" :
			
			c_num = Integer.parseInt(request.getParameter("c_num"));
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			content = request.getParameter("content");
			cgroups = Integer.parseInt(request.getParameter("groups"));
			orders = Integer.parseInt(request.getParameter("orders"));
			depth = Integer.parseInt(request.getParameter("depth"));
			
			session = request.getSession();
			if(session.getAttribute("user")==null) {
				url = "http://localhost:8080/lcomputerstudy/user-login.do";
				break;
			}
			
			user= (User)session.getAttribute("user");
			u_idx = user.getU_idx();
			
			reply  = new Reply();
			reply.setB_idx(b_idx);
			reply.setC_content(content);
			reply.setC_date_timestamp((new Timestamp(System.currentTimeMillis())));
			reply.setDepth(depth);
			reply.setGroups(cgroups);
			reply.setOrders(orders);
			reply.setU_idx(u_idx);
			
			boardService = BoardService.getInstance();
			boardService.re_Reply(reply);
			
			url = "http://localhost:8080/lcomputerstudy/board-view.do?b_idx="+b_idx;
			
			break;
			
		}
			
		
		if(view==null)
			response.sendRedirect(url);
		
		else {
			
			RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
					rd.forward(request, response);
			}
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

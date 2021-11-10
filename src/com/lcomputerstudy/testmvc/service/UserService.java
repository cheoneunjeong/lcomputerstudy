package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Post;
import com.lcomputerstudy.testmvc.vo.User;

public class UserService {

	private static UserService service = null;
	private static UserDAO dao = null;
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		if(service == null) {
			service = new UserService();
			dao = UserDAO.getInstance();
			
		}
		return service;
	}
	
	public ArrayList<User> getUsers() {
		return dao.getUsers();
	}
	
	public void insertUser(User user) {
		dao.insertUser(user);
	}
	
	public int getUsersCount() {
		return dao.getUsersCount();
	}
	
	public ArrayList<User> getUsers(int page) {
		return dao.getUsers(page);
	}
	
	public User loginUser(String idx, String pw) {
		return dao.loginUser(idx,pw);
	}
	
	public void reg(String title, String content, int num ) {
		dao.reg(title, content, num);
	}
	
	public int getPostCount() {
		return dao.getPostCount();
	}

	public ArrayList<Post> getPost(){
		return dao.getPost();
	}
	
	public ArrayList<Post> getPost(int page) {
		return dao.getPost(page);
	}
	
	public Post getPostDetail(int bidx) {
		return dao.getPostDetail(bidx);
	}
}

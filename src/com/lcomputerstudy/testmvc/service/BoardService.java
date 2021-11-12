package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.BoardHitDAO;
import com.lcomputerstudy.testmvc.vo.Post;

public class BoardService {
	
	private static BoardService service = null;
	private static BoardDAO dao = null;
	private static BoardHitDAO hitdao = null;
	
	private BoardService() {
		
	}
	
	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
			hitdao = BoardHitDAO.getInstance();
			
		}
		return service;
	}


	public void reg(Post post) {
		dao.reg(post);
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
	
	public void deletePost(int Bidx) {
		dao.deletePost(Bidx);
	}
	
	public void fixPost(Post post) {
		dao.fixPost(post);
	}
	
	public void checkdelete(String[] delIds) {
		dao.checkdelete(delIds);
	}
	
	public void hit(int idx) {
		hitdao.insert();
	}
	
}

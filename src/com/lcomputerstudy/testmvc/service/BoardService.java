package com.lcomputerstudy.testmvc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.Part;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.BoardHitDAO;
import com.lcomputerstudy.testmvc.vo.Detail;
import com.lcomputerstudy.testmvc.vo.Post;
import com.lcomputerstudy.testmvc.vo.Reply;

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
	
	public Detail getPostDetail(int bidx) {
		
		hitdao.insertHit(bidx);
		
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

	public ArrayList<Post> searchPost(int page, String f, String search) {
		return dao.searchPost(page, f, search);
	}

	public int getSearchPostCount(String f, String search) {
		return dao.getSearchPostCount(f,search);
	}
	
	public void regComment(Post post, int groups, int orders, int depth) {
		dao.regComment(post, groups, orders, depth);
	}

	public ArrayList<Reply> regReply(Reply reply) {
		return dao.regReply(reply);
		
	}

	public void deleteReply(int c_num) {
		dao.deleteReply(c_num);
		
	}

	public int getReplyCount(int bidx) {
		return dao.getReplyCount(bidx);
	}

	public ArrayList<Reply> re_Reply(Reply reply) {
		return dao.re_Reply(reply);
		
	}
	
	public ArrayList<String> getfileName(Post post) {
		
		String file = post.getB_file();
		
		if(file == null) 
			file = "";
		
		ArrayList<String> fileName = new ArrayList<>();
		
		if(file.contains(",")) {
			String[] files = post.getB_file().split(",");
			for(String file_ : files)
				fileName.add(file_);	
		} else 
			fileName.add(file);
		
		return fileName;
		
	}
	
}

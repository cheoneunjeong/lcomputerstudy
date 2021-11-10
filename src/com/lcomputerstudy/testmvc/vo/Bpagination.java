package com.lcomputerstudy.testmvc.vo;

import com.lcomputerstudy.testmvc.service.UserService;

public class Bpagination {


	int postCount;
	int page;
	int pageNum;
	int startPage;
	int endPage;
	int lastPage;
	int prevPage;
	int nextPage;
	public static final int pageUnit=5;
	public static final int perPage=3;
	UserService userService = null;
	
	public Bpagination() {
		
	}
	
	public Bpagination(int page) {
		this.page = page;
		userService = UserService.getInstance();
		postCount = userService.getPostCount();
		startPage = ((page-1)/pageUnit)*pageUnit+1;
		lastPage = (int)Math.ceil(postCount/(float)perPage);
		endPage = startPage+pageUnit-1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage = (endPage-pageUnit);
		nextPage = (startPage+pageUnit);
	}
	
	public int getPostCount() {
		return postCount;
	}
	
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public void setStartPage(int startpage) {
		this.startPage = startpage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	
	public int getPerPage() {
		return perPage;
	}
	
	public int getPrevPage() {
		return prevPage;
	}
	
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	
	public int getNextPage() {
		return nextPage;
	}
	
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
}
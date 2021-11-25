package com.lcomputerstudy.testmvc.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Post {

	private int b_idx;
	private String b_title;
	private String b_content;
	private String b_date;
	private Timestamp b_date_timestamp;
	private int u_idx; 
	private int hit;
	private int ROWNUM;
	private int groups;
	private int orders;
	private int depth;
	private String concat;
	private String b_file;


	public int getB_idx() {
		return b_idx;
	}
	
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	
	public String getB_title() {
		return b_title;
	}
	
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	
	public String getB_content() {
		return b_content;
	}
	
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	
	public String getB_date() {
		return b_date;
	}
	
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	
	public int getROWNUM() {
		return ROWNUM;
	}
	
	public void setROWNUM(int ROWNUM) {
		this.ROWNUM = ROWNUM;
	}

	public int getU_idx() {
		return u_idx;
	}

	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}

	public Timestamp getB_date_timestamp() {
		return b_date_timestamp;
	}

	public void setB_date_timestamp(Timestamp b_date_timestamp) {
		this.b_date_timestamp = b_date_timestamp;
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd \n hh:mm:ss");
		this.b_date = fm.format(b_date_timestamp); 
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	public int getGroups() {
		return groups;
	}

	public void setGroups(int groups) {
		this.groups = groups;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public String getConcat() {
		return concat;
	}

	public void setConcat(String concat) {
		this.concat = concat;
	}

	public String getB_file() {
		return b_file;
	}

	public void setB_file(String b_file) {
		this.b_file = b_file;
	}
	
	
}

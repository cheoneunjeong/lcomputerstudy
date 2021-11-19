package com.lcomputerstudy.testmvc.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Reply {
	
		private int c_num;
		private int b_idx;
		private int u_idx;
		private String c_content;
		private String c_date;
		private Timestamp c_date_timestamp;

		public int getC_num() {
			return c_num;
		}

		public void setC_num(int c_num) {
			this.c_num = c_num;
		}

		public int getB_idx() {
			return b_idx;
		}

		public void setB_idx(int b_idx) {
			this.b_idx = b_idx;
		}

		public int getU_idx() {
			return u_idx;
		}

		public void setU_idx(int u_idx) {
			this.u_idx = u_idx;
		}

		public String getC_content() {
			return c_content;
		}

		public void setC_content(String c_content) {
			this.c_content = c_content;
		}

		public String getC_date() {
			return c_date;
		}

		public void setC_date(String c_date) {
			this.c_date = c_date;
		}

		public Timestamp getC_date_timestamp() {
			return c_date_timestamp;
		}

		public void setC_date_timestamp(Timestamp c_date_timestamp) {
			this.c_date_timestamp = c_date_timestamp;

			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd \n hh:mm:ss");
			this.c_date = fm.format(c_date_timestamp);
		}

	}

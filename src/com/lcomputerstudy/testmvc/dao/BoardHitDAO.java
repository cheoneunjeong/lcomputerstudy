package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lcomputerstudy.testmvc.database.DBConnection;

public class BoardHitDAO {

	private static BoardHitDAO hitdao  = null;
	
	private BoardHitDAO() {
		
	}
	
	public static BoardHitDAO getInstance() {
		if(hitdao == null) {
			hitdao = new BoardHitDAO();
		}
		return hitdao;
	}
	
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
//		try {
//			conn = DBConnection.getConnection();
//			String query = "UPDATE board SET hit = hit+1 WHERE b_idx = ?";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, bidx);/  board-delete.do에 있는 bidx
		
//
//		}
	}
	
}

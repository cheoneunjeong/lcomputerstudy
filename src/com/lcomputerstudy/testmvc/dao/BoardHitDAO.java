package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public void insertHit(int bidx) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = "UPDATE test SET b_hit = b_hit+1 WHERE b_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bidx);
			int r = pstmt.executeUpdate();
			System.out.println(r);

		} catch (Exception e) {
		
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

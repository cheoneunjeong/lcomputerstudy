package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Post;
import com.lcomputerstudy.testmvc.vo.Reply;

public class BoardDAO {
	
	private static BoardDAO dao = null;

	private BoardDAO() {

	}

	public static BoardDAO getInstance() {
		if (dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}

	public void reg(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO test (b_title, b_content, b_date, u_idx) VALUES(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getB_title());
			pstmt.setString(2, post.getB_content());
			pstmt.setString(3, post.getB_date());
			pstmt.setInt(4, post.getU_idx());
			pstmt.executeUpdate();
			
			if (pstmt != null)
				pstmt.close();
			
			sql = "UPDATE test SET groups = LAST_INSERT_ID(), orders = '1' WHERE b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getPostCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM test";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public ArrayList<Post> getPost() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Post> list = null;

		try {
			conn = DBConnection.getConnection();
			String query = "select * from test";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<Post>();

			while (rs.next()) {
				Post post = new Post();
				post.setB_idx(rs.getInt("b_idx"));
				post.setB_title(rs.getString("b_title"));
				post.setB_content(rs.getString("b_content"));
				post.setB_date(rs.getString("b_date"));
				post.setU_idx(rs.getInt("u_idx"));
				post.setHit(rs.getInt("b_hit"));
				post.setGroups(rs.getInt("groups"));
				post.setOrders(rs.getInt("orders"));
				post.setDepth(rs.getInt("depth"));
				list.add(post);
			}

		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<Post> getPost(int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Post> list = null;
		int pageNum = (page - 1) * 3;

		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("select * FROM(select @rownum := @rownum-1 as rownum, (concat(repeat('¦¦', depth), b_title))as con,")
					.append("    ta.*\n")
					.append("from test ta, \n")
					.append("    (select @rownum := (select count(*)-?+1 from test ta)) tb \n")
					.append("order by groups desc, orders asc\n")
					.append("limit   ?,3)a")
					.toString();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs = pstmt.executeQuery();
			list = new ArrayList<Post>();

			while (rs.next()) {
				Post post = new Post();
				post.setROWNUM(rs.getInt("ROWNUM"));
				post.setB_idx(rs.getInt("b_idx"));
				post.setB_title(rs.getString("b_title"));
				post.setB_content(rs.getString("b_content"));
				post.setB_date(rs.getString("b_date"));
				post.setU_idx(rs.getInt("u_idx"));
				post.setHit(rs.getInt("b_hit"));
				post.setGroups(rs.getInt("groups"));
				post.setOrders(rs.getInt("orders"));
				post.setDepth(rs.getInt("depth"));
				post.setConcat(rs.getString("con"));
				list.add(post);
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

	public Post getPostDetail(int bidx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Post post = new Post();

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM test WHERE b_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			System.out.println(rs);

			while (rs.next()) {
				post.setB_idx(bidx);
				post.setB_title(rs.getString("b_title"));
				post.setB_content(rs.getString("b_content"));
				post.setB_date(rs.getString("b_date"));
				post.setU_idx(rs.getInt("u_idx"));
				post.setHit(rs.getInt("b_hit"));
				post.setGroups(rs.getInt("groups"));
				post.setOrders(rs.getInt("orders"));
				post.setDepth(rs.getInt("depth"));
			}
			
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return post;
	}

	public void deletePost(int bidx) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String query = "DELETE FROM test WHERE b_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bidx);
			pstmt.executeUpdate();
			
		} catch (Exception e) {

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fixPost(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String query = "UPDATE test SET b_title=?, b_content=?, b_date=? WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, post.getB_title());
			pstmt.setString(2, post.getB_content());
			pstmt.setString(3, post.getB_date());
			pstmt.setInt(4, post.getB_idx());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void checkdelete(String[] delIds) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		for(String delId_ : delIds) {
			
			int delId = Integer.parseInt(delId_);
			
			try {
				conn = DBConnection.getConnection();
				String query = "DELETE FROM test WHERE b_idx = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, delId);
				int r = pstmt.executeUpdate();
				System.out.println(r);
			} catch (Exception e) {

			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int getSearchPostCount(String f, String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM test WHERE "+f+" LIKE ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public ArrayList<Post> searchPost(int page, String f, String search) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageNum = (page - 1) * 3;
		ArrayList<Post> list = null;
		
		try {
			conn= DBConnection.getConnection();
			
			String query = new StringBuilder()
					.append("select * FROM")
					.append("(SELECT     @ROWNUM := @ROWNUM -1 AS ROWNUM, (concat(repeat('¦¦', depth), b_title))AS con,\n")
					.append("           ta.*\n")
					.append("FROM       (SELECT * FROM test WHERE "+f+" LIKE ? ) ta, \n")
					.append("           (SELECT @ROWNUM := (SELECT COUNT(*)-?+1 FROM (SELECT * FROM test WHERE "+f+" LIKE ? ) ta)) tb\n")
					.append("order by groups desc, orders asc\n")
					.append("LIMIT ?,3)a")
					.toString();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, pageNum);
			pstmt.setString(3, "%"+search+"%");
			pstmt.setInt(4, pageNum);
			
			rs = pstmt.executeQuery();
	
			list = new ArrayList<Post>();

			while (rs.next()) {
				Post post = new Post();
				post.setROWNUM(rs.getInt("ROWNUM"));
				post.setB_idx(rs.getInt("b_idx"));
				post.setB_title(rs.getString("b_title"));
				post.setB_content(rs.getString("b_content"));
				post.setB_date(rs.getString("b_date"));
				post.setU_idx(rs.getInt("u_idx"));
				post.setHit(rs.getInt("b_hit"));
				post.setGroups(rs.getInt("groups"));
				post.setOrders(rs.getInt("orders"));
				post.setDepth(rs.getInt("depth"));
				post.setConcat(rs.getString("con"));
				list.add(post);
			}
			
		} catch (Exception e) {

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} return list;
	}

	public void regComment(Post post, int cgroups, int orders, int depth) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO test (b_title, b_content, b_date, u_idx, groups, orders, depth) VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getB_title());
			pstmt.setString(2, post.getB_content());
			pstmt.setString(3, post.getB_date());
			pstmt.setInt(4, post.getU_idx());
			pstmt.setInt(5, cgroups);
			pstmt.setInt(6, orders+1);
			pstmt.setInt(7, depth+1);
			
			pstmt.executeUpdate();
			
			if (pstmt != null)
				pstmt.close();
			
			sql = "update test set orders = orders+1 WHERE (not b_idx =  LAST_INSERT_ID() ) && (not orders < ?) && (groups = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orders+1);
			pstmt.setInt(2, cgroups);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void regReply(Reply reply) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO test_reply (b_idx, u_idx, c_date, c_content) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getB_idx());
			pstmt.setInt(2, reply.getU_idx());
			pstmt.setString(3, reply.getC_date());
			pstmt.setString(4, reply.getC_content());

			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("SQLException : " + ex.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
}


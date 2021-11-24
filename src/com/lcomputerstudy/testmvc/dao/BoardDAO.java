package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Detail;
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
					.append("select * FROM(select @rownum := @rownum-1 as rownum, (concat(repeat('ㄴ', depth), b_title))as con,")
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

	public Detail getPostDetail(int bidx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Post post = new Post();
		ArrayList<Reply> list = new ArrayList<>();
		Detail detail = new Detail();

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT * FROM test WHERE b_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();

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
				
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				
				query = new StringBuilder()
						.append("select * FROM(select @rownum := @rownum-1 as rownum, (concat(repeat('ㄴ', depth), c_content))as con,    ta.*\n")
						.append("from (SELECT * FROM test_reply WHERE b_idx= ?)ta,\n")
						.append("    (select @rownum := (select count(*)+1 from (SELECT * FROM test_reply WHERE b_idx= ?)ta)) tb \n")
						.append("order by groups desc, orders asc)a \n")
						.toString();
	
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, bidx);
				pstmt.setInt(2, bidx);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Reply reply = new Reply();
					reply.setB_idx(bidx);
					reply.setC_content(rs.getString("c_content"));
					reply.setC_date(rs.getString("c_date"));
					reply.setU_idx(rs.getInt("u_idx"));
					reply.setC_num(rs.getInt("c_num"));
					reply.setGroups(rs.getInt("groups"));
					reply.setOrders(rs.getInt("orders"));
					reply.setDepth(rs.getInt("depth"));
					reply.setCon(rs.getString("con"));
					list.add(reply);
				}
		
				
			detail.setList(list);
			detail.setPost(post);	
			
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
		return detail;
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
					.append("(SELECT     @ROWNUM := @ROWNUM -1 AS ROWNUM, (concat(repeat('ㄴ', depth), b_title))AS con,\n")
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

	public ArrayList<Reply> regReply(Reply reply) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Reply> list = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO test_reply (b_idx, u_idx, c_date, c_content) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getB_idx());
			pstmt.setInt(2, reply.getU_idx());
			pstmt.setString(3, reply.getC_date());
			pstmt.setString(4, reply.getC_content());

			pstmt.executeUpdate();
		
			if (pstmt != null)
				pstmt.close();
			
			sql = "UPDATE test_reply SET groups = LAST_INSERT_ID(), orders = '1' WHERE c_num = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			if (pstmt != null)
				pstmt.close();
			
			
			sql = new StringBuilder()
					.append("select * FROM(select @rownum := @rownum-1 as rownum, (concat(repeat('ㄴ', depth), c_content))as con,    ta.*\n")
					.append("from (SELECT * FROM test_reply WHERE b_idx= ?)ta,\n")
					.append("    (select @rownum := (select count(*)+1 from (SELECT * FROM test_reply WHERE b_idx= ?)ta)) tb \n")
					.append("order by groups desc, orders asc)a \n")
					.toString();
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getB_idx());
			pstmt.setInt(2, reply.getB_idx());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Reply replys = new Reply();
				replys.setB_idx(rs.getInt("b_idx"));
				replys.setC_content(rs.getString("c_content"));
				replys.setC_date(rs.getString("c_date"));
				replys.setU_idx(rs.getInt("u_idx"));
				replys.setC_num(rs.getInt("c_num"));
				replys.setGroups(rs.getInt("groups"));
				replys.setOrders(rs.getInt("orders"));
				replys.setDepth(rs.getInt("depth"));
				replys.setCon(rs.getString("con"));
				list.add(replys);
			}
			
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
		} return list;
	}

	public void deleteReply(int c_num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String query = "DELETE FROM test_reply WHERE c_num = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, c_num);
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

	public int getReplyCount(int bidx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM test_reply WHERE b_idx = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bidx);
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

	public ArrayList<Reply> re_Reply(Reply reply) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<Reply> list = new ArrayList<>();;

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO test_reply (b_idx, u_idx, c_date, c_content, groups, orders, depth) VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getB_idx());
			pstmt.setInt(2, reply.getU_idx());
			pstmt.setString(3, reply.getC_date());
			pstmt.setString(4, reply.getC_content());
			pstmt.setInt(5, reply.getGroups());
			pstmt.setInt(6, (reply.getOrders())+1);
			pstmt.setInt(7, (reply.getDepth())+1);
			
			pstmt.executeUpdate();
			
			if (pstmt != null)
				pstmt.close();
			
			sql = "update test_reply set orders = orders+1 WHERE (not c_num =  LAST_INSERT_ID() ) && (not orders < ?) && (groups = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,(reply.getOrders())+1);
			pstmt.setInt(2, reply.getGroups());
			pstmt.executeUpdate();
			
			if (pstmt != null)
				pstmt.close();
			
			sql = new StringBuilder()
					.append("select * FROM(select @rownum := @rownum-1 as rownum, (concat(repeat('ㄴ', depth), c_content))as con,    ta.*\n")
					.append("from (SELECT * FROM test_reply WHERE b_idx= ?)ta,\n")
					.append("    (select @rownum := (select count(*)+1 from (SELECT * FROM test_reply WHERE b_idx= ?)ta)) tb \n")
					.append("order by groups desc, orders asc)a \n")
					.toString();
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getB_idx());
			pstmt.setInt(2, reply.getB_idx());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Reply replys = new Reply();
				replys.setB_idx(rs.getInt("b_idx"));
				replys.setC_content(rs.getString("c_content"));
				replys.setC_date(rs.getString("c_date"));
				replys.setU_idx(rs.getInt("u_idx"));
				replys.setC_num(rs.getInt("c_num"));
				replys.setGroups(rs.getInt("groups"));
				replys.setOrders(rs.getInt("orders"));
				replys.setDepth(rs.getInt("depth"));
				replys.setCon(rs.getString("con"));
				list.add(replys);
			}
			
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
		return list;
	} 

		
}


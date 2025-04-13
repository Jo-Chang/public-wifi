package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.Bookmark;

public class BookmarkService {

	private DbService dbService;

	public BookmarkService() {
		this.dbService = new DbService();
	}
	
	public List<Bookmark> getAll() {
		dbService.createConnection();
		
		List<Bookmark> bookmarkList = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = " select * from Bookmark "
					+ " order by bm_order ";
			
			stmt = dbService.getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				bookmarkList.add(Bookmark.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.bmOrder(rs.getInt("bm_order"))
						.createTs(rs.getTimestamp("create_ts"))
						.updateTs(rs.getTimestamp("update_ts"))
						.build()
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbService.closeConnection();
		}
		
		return bookmarkList;
	}
	
	public Bookmark getById(int id) throws Exception {
		dbService.createConnection();
		
		Bookmark bookmark = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = " select * from Bookmark where id = ? ";
			
			pstmt = dbService.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			bookmark = Bookmark.builder()
					.id(rs.getInt("id"))
					.name(rs.getString("name"))
					.bmOrder(rs.getInt("bm_order"))
					.createTs(rs.getTimestamp("create_ts"))
					.updateTs(rs.getTimestamp("update_ts"))
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbService.closeConnection();
		}
		
		if (bookmark == null) {
			throw new Exception("북마크 정보 불러오기에 실패했습니다. id = " + id);
		}
		
		return bookmark;
	}
	
	public void insert(String name, int order) {
		dbService.createConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = 
					" insert into Bookmark "
					+ " (name, bm_order, create_ts) "
					+ " values(?, ?, ?) ";
			
			pstmt = dbService.getConnection()
					.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, order);
			pstmt.setTimestamp(3, 
					new Timestamp(System.currentTimeMillis())
					);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbService.closeConnection();
		}
	}
	
	public void deleteById(int id) {
		dbService.createConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = " delete from Bookmark where id = ? ";
			
			pstmt = dbService.getConnection()
					.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbService.closeConnection();
		}
	}
	
	public void update(int id, String name, int order) {
		dbService.createConnection();
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = 
					" update Bookmark "
					+ " set name = ?, bm_order = ?, update_ts = ? "
					+ " where id = ? ";
			
			pstmt = dbService.getConnection()
					.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, order);
			pstmt.setTimestamp(3, 
					new Timestamp(System.currentTimeMillis())
					);
			pstmt.setInt(4, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			dbService.closeConnection();
		}
	}
	
}

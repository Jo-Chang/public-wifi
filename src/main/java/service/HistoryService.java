package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.History;

public class HistoryService {

	private DbService dbService;
	
	public void insertHistory(double lat, double lnt) {
		dbService = new DbService();
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = dbService.getConnection().prepareStatement(
					" insert into History (lat, lnt, view_ts) "
					+ " values(?, ?, ?) "
					);
			
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lnt);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			
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
	
	public List<History> getAllHistory() {
		dbService = new DbService();
		List<History> historyList = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = dbService.getConnection().createStatement();
			rs = stmt.executeQuery("select * from History order by id desc");
			
			while (rs.next()) {
				historyList.add(History.builder()
						.id(rs.getInt("id"))
						.lat(rs.getDouble("lat"))
						.lnt(rs.getDouble("lnt"))
						.viewTs(rs.getTimestamp("view_ts"))
						.build());
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
		
		return historyList;
	}
	
	public void deleteHistory(int id) {
		dbService = new DbService();
		
		PreparedStatement pstmt = null;
		try {
			pstmt = dbService.getConnection().prepareStatement(
					" delete from History "
					+ " where id = ? "
					);
			
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
	
}

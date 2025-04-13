package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.WifiBookmarkDto;

public class WifiBookmarkService {

	private DbService dbService;

	public WifiBookmarkService() {
		this.dbService = new DbService();
	}

	public void insert(int bookmarkId, String wifiMgrNo) {
		dbService.createConnection();

		PreparedStatement pstmt = null;

		try {
			String sql = " insert into Wifi_Bookmark "
					+ " (bookmark_id, wifi_mgr_no, create_ts) " 
					+ " values (?, ?, ?) ";
			
			pstmt = dbService.getConnection().prepareStatement(sql);

			pstmt.setInt(1, bookmarkId);
			pstmt.setString(2, wifiMgrNo);
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
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
			String sql = " delete from Wifi_Bookmark " 
						+ " where id = ? ";
			pstmt = dbService.getConnection().prepareStatement(sql);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			dbService.closeConnection();
		}
	}

	public List<WifiBookmarkDto> findAll() {
		dbService.createConnection();
		
		List<WifiBookmarkDto> wbList = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql = " select wb.id as id, w.mgr_no as mgr_no, "
					+ " bm.name as bm_name, w.main_nm as wifi_name, "
					+ " wb.create_ts as create_ts "
					+ " from Wifi_Bookmark wb "
					+ " left join Wifi w on wb.wifi_mgr_no = w.mgr_no "
					+ " left join Bookmark bm on wb.bookmark_id = bm.id "
					+ "	order by id desc ";
			stmt = dbService.getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				wbList.add(WifiBookmarkDto.builder()
						.id(rs.getInt("id"))
						.wifiMgrNo(rs.getString("mgr_no"))
						.bookmarkName(rs.getString("bm_name"))
						.wifiName(rs.getString("wifi_name"))
						.createTs(rs.getTimestamp("create_ts"))
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
		
		return wbList;
	}
	
	public WifiBookmarkDto findById(int id) {
		dbService.createConnection();
		
		WifiBookmarkDto wbDto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = " select wb.id as id, bm.name as bm_name, "
					+ " w.main_nm as wifi_name, wb.create_ts as create_ts "
					+ " from Wifi_Bookmark wb "
					+ " left join Wifi w on wb.wifi_mgr_no = w.mgr_no "
					+ " left join Bookmark bm on wb.bookmark_id = bm.id "
					+ "	where wb.id = ? ";
			
			pstmt = dbService.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				wbDto = WifiBookmarkDto.builder()
						.id(rs.getInt("id"))
						.bookmarkName(rs.getString("bm_name"))
						.wifiName(rs.getString("wifi_name"))
						.createTs(rs.getTimestamp("create_ts"))
						.build();
			}
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
		
		return wbDto;
	}

}

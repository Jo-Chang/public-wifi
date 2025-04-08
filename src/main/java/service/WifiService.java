package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Wifi;

public class WifiService {

	private final DbService dbService;
	
	public WifiService() {
		this.dbService = new DbService();
	}
	
	public void close() {
		this.dbService.closeConnection();
	}
	
	public void callDbInsert(String responseStr) {
		if (responseStr.indexOf("<row>") < 0) return;
		
		int startIdx = responseStr.indexOf("<row>");
		
		while (startIdx >= 0) {
			responseStr = responseStr.substring(startIdx + 5);
			int endIdx = responseStr.indexOf("</row>");
			
			Wifi wifi = ParseService.parseXmlToWifi(responseStr.substring(0, endIdx));
			insertWifiData(wifi);
			
			startIdx = responseStr.indexOf("<row>", endIdx);
		}
		
	}
	
	public void insertWifiData(Wifi wifi) {
		String sql = "insert into Wifi (mgr_no, wrdofc, main_nm, "
				+ " adres1, adres2, instl_floor, instl_ty, instl_mby, "
				+ " svc_se, cmcwr, cnstc_year, inout_door, remars3, "
				+ " lat, lnt, work_dttm) "
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = dbService.getConnection().prepareStatement(sql);
			
			pstmt.setString(1, wifi.getMgrNo());
			pstmt.setString(2, wifi.getWrdofc());
			pstmt.setString(3, wifi.getMainNm());
			pstmt.setString(4, wifi.getAdres1());
			pstmt.setString(5, wifi.getAdres2());
			pstmt.setString(6, wifi.getInstlFloor());
			pstmt.setString(7, wifi.getInstlTy());
			pstmt.setString(8, wifi.getInstlMBy());
			pstmt.setString(9, wifi.getSvcSe());
			pstmt.setString(10, wifi.getCmcwr());
			pstmt.setInt(11, wifi.getCnstcYear());
			pstmt.setString(12, wifi.getInoutDoor());
			pstmt.setString(13, wifi.getRemars3());
			pstmt.setDouble(14, wifi.getLat());
			pstmt.setDouble(15, wifi.getLnt());
			pstmt.setTimestamp(16, wifi.getWorkDttm());
			
			int affectedRow = pstmt.executeUpdate();
			System.out.println(affectedRow + " Rows Updated!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<Wifi> getPublicWifi(double lat, double lnt) {
		List<Wifi> wifiList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = dbService.getConnection().prepareStatement(
					"select ( 6371 * acos( cos( radians( ? ) ) * cos( radians( w.lat ) ) * cos( radians( w.lnt ) - radians( ? ) ) + sin( radians( ? ) ) * sin( radians( w.lat ) ) ) ) as distance, "
					+ " w.* from Wifi w order by distance limit 20 ");
			pstmt.setDouble(1, lat);
			pstmt.setDouble(2, lnt);
			pstmt.setDouble(3, lat);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Wifi wifi = new Wifi();
				
				wifi.setMgrNo(rs.getString("mgr_no"));
				wifi.setWrdofc(rs.getString("wrdofc"));
				wifi.setMainNm(rs.getString("main_nm"));
				wifi.setAdres1(rs.getString("adres1"));
				wifi.setAdres2(rs.getString("adres2"));
				wifi.setInstlFloor(rs.getString("instl_floor"));
				wifi.setInstlTy(rs.getString("instl_ty"));
				wifi.setInstlMBy(rs.getString("instl_mby"));
				wifi.setSvcSe(rs.getString("svc_se"));
				wifi.setCmcwr(rs.getString("cmcwr"));
				wifi.setCnstcYear(rs.getInt("cnstc_year"));
				wifi.setInoutDoor(rs.getString("inout_door"));
				wifi.setRemars3(rs.getString("remars3"));
				wifi.setLat(rs.getDouble("lat"));
				wifi.setLnt(rs.getDouble("lnt"));
				wifi.setWorkDttm(rs.getTimestamp("work_dttm"));
				wifi.setDistance(
						(double) Math.round(rs.getDouble("distance") * 10000) / 10000
				);
				
				wifiList.add(wifi);
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
		
		return wifiList;
	}
	
}

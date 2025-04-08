package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import property.DbProperties;

public class DbService {
	
	private Connection conn;
	
	public DbService() {
		createConnection();
	}
	
	public Connection createConnection() {
		try {
			Class.forName(DbProperties.JDBC_DRIVER);
			conn = DriverManager.getConnection(DbProperties.DB_URL);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		
		System.out.println("DB connected");
		return conn;
	}
	
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.commit();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
		
		System.out.println("DB closed");
	}
	
	public void initWifiTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			
			stmt.executeUpdate("drop table if exists Wifi");
			stmt.executeUpdate("create table Wifi( "
					+ "	mgr_no      VARCHAR(20) PRIMARY KEY, "
					+ "	wrdofc      VARCHAR(20), "
					+ "	main_nm     VARCHAR(50), "
					+ "	adres1      VARCHAR(20), "
					+ "	adres2      VARCHAR(50), "					+ "	instl_floor VARCHAR(20), "
					+ "	instl_ty    VARCHAR(20), "
					+ "	instl_mby   VARCHAR(20), "
					+ "	svc_se      VARCHAR(10), "
					+ "	cmcwr       VARCHAR(10), "
					+ "	cnstc_year  INTEGER, "
					+ "	inout_door  VARCHAR(10), "
					+ "	remars3     VARCHAR(255), "
					+ "	lat         DOUBLE, "
					+ "	lnt         DOUBLE, "
					+ "	work_dttm   TIMESTAMP )"
					);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		closeConnection();
	}
	
	public void initHistoryTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			
			stmt.executeUpdate("drop table if exists History");
			stmt.executeUpdate("create table History( "
					+ "	id      INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
					+ " lat     DOUBLE, "
					+ "	lnt		DOUBLE, "
					+ "	view_ts   TIMESTAMP ) "
					);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		closeConnection();
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
}

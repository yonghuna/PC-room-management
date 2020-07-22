package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	private Connection conn; //

	private PreparedStatement pstmt;

	private ResultSet rs;

	public DBConnection() {
		try {

			String dbURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC"; // localhost:3306 포트는 컴퓨터설치된

			String dbID = "root";

			String dbPassword = "0000";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public boolean login(String id, String password) {
		String SQL = "SELECT * FROM member_test where id = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("password").equals(password)) {
					System.out.println(rs.getString("password"));
					return true;
				} else {
					System.out.println("Password error");
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("db err");
		return false;
	}

	public int register(String num, String id, String password, String age, String phone, String mileage) {

		pstmt = null;
		ResultSet re = null;
		String SQL = "INSERT INTO member_test VALUES (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, phone);
			pstmt.setString(4, mileage);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1; // 오류
	}
}

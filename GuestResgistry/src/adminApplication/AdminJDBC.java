package adminApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.*;

public class AdminJDBC {
	private static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}

	/*
	 * Reads from the database
	 */

	public static List<List<String>> getEmails() {
		Connection con = null;
		Statement stmt;
		
		List<List<String>> resultSet = new ArrayList<List<String>>();

		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);

			if (!con.isClosed()) {
				System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
				stmt = con.createStatement();

				// create and select db

				stmt.execute("CREATE DATABASE IF NOT EXISTS VisitorsDB");
				stmt.execute("USE VisitorDB");

				/**
				 * Query entries with the Zip '71467'
				 */

				ResultSet res = stmt.executeQuery("SELECT * FROM visitors WHERE Email IS NOT NULL");

				/**
				 * Iterate over the result set from the above query
				 */
				int i = 0;
				int j = 0;
				
				while (res.next()) {
					List<String> currentResult = new ArrayList<String>();
					currentResult.add(((Integer) res.getInt("VisitorID")).toString());
					currentResult.add(res.getString("Fname"));
					currentResult.add(res.getString("MiddleInitial"));
					currentResult.add(res.getString("Lname"));
					currentResult.add(res.getString("Email"));
					
					resultSet.add(currentResult);
				}

			}
			
			return resultSet;
		}

		catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return null;
	}
}

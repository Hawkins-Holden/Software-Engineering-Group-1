package adminApplication;

import java.util.ArrayList;
import java.util.List;

import application.Visitor;

import java.sql.*;

public class AdminJDBC {
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

				stmt.execute("CREATE DATABASE IF NOT EXISTS visitordb");
				stmt.execute("USE visitordb");

				/**
				 * Query entries with the Zip '71467'
				 */

				ResultSet res = stmt.executeQuery("SELECT * FROM visitors WHERE Email IS NOT NULL ORDER BY VisitorID");

				/**
				 * Iterate over the result set from the above query
				 */
				while (res.next()) {
					List<String> currentResult = new ArrayList<String>();
					Integer id = res.getInt("VisitorID");
					currentResult.add(id.toString());
					currentResult.add(res.getString("Fname"));
					currentResult.add((String) res.getObject("MiddleInitial"));
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

	public static List<List<String>> generateReport() {
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

				stmt.execute("CREATE DATABASE IF NOT EXISTS visitordb");
				stmt.execute("USE visitordb");
				/**
				 * Query entries with the Zip '71467'
				 */

				// ResultSet res = stmt.executeQuery("SELECT * FROM visitors
				// NATURAL JOIN visits NATURAL JOIN visitorlocations ORDER BY
				// Visiting_Day");
				ResultSet res = stmt.executeQuery(
						"SELECT * FROM visitors LEFT JOIN visits ON visitors.VisitorID = visits.VisitorID LEFT JOIN visitorlocations on visitors.VisitorID = visitorlocations.VisitorID ORDER BY Visiting_Day");

				/**
				 * Iterate over the result set from the above query
				 */
				while (res.next()) {
					List<String> currentResult = new ArrayList<String>();

					currentResult.add(((Integer) res.getInt("VisitorID")).toString());
					currentResult.add(res.getString("Fname"));
					currentResult.add((String) res.getObject("MiddleInitial"));
					currentResult.add(res.getString("Lname"));
					currentResult.add(res.getString("Email"));
					currentResult.add(res.getString("Latitude"));
					currentResult.add(res.getString("Longitude"));
					currentResult.add(((Integer) res.getInt("Party")).toString());
					currentResult.add(res.getString("Heard"));
					if (res.getInt("Hotel") == 1) {
						currentResult.add("Yes");
					} else {
						currentResult.add("No");
					}
					currentResult.add(res.getString("Destination"));
					if (res.getInt("RepeatVisit") == 1) {
						currentResult.add("Yes");
					} else {
						currentResult.add("No");
					}
					currentResult.add(res.getString("TravelingFor"));
					currentResult.add(res.getString("Visiting_Day"));

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

	public static List<Visitor> getVisitors() {
		Connection con = null;
		Statement stmt;

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

				ResultSet res = stmt.executeQuery("SELECT * FROM visitors WHERE Date >= '2011/02/25' and Date <= '2011/02/27'");

				/**
				 * Iterate over the result set from the above query
				 */
				List<Visitor> visitors = new ArrayList<Visitor>();
				while (res.next()) {
					Visitor visitor = new Visitor();
					System.out.println(res.getInt("VisitorID") + " " + res.getString("Email"));
				}

			}
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
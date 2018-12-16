package com.alkapa.tradeshift.jersey.docker.demo;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbStorage {
	public void testDatabase(String connectionString)
		throws SQLException, ClassNotFoundException {

		Connection conn = null;
		try {

			conn = openJDBCConnection(connectionString);
 
			log("\n---------- Get Data from DB ----------");
			Company[] companies = getCompanies(conn);
			printCompanies(companies);
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
 
	private Connection openJDBCConnection(String connectionString) 
		throws SQLException, ClassNotFoundException {
		log("-------- Open JDBC connection to MySQL DB  ------------");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			log("MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			throw e;
		}
 
		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			Connection conn = DriverManager.getConnection(connectionString);

			if (conn != null) {
				log("Connection Successful!");
			} else {
				log("Failed to make connection!");
			}
			return conn;
		} catch (SQLException e) {
			log("MySQL Connection Failed!");
			e.printStackTrace();
			throw e;
		}
	}

	private static void printCompanies(Company[] companies) {
		for (int i = 0; i < companies.length; i++) {
			Company c = companies[i];
			System.out.format("%s, %s, %s\n",
				c.getId(), c.getName(), c.getParentId());
		}
	}

	private static Company[] getCompanies(Connection conn) throws SQLException {
		PreparedStatement st = null;
		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT id, name, parent_id FROM Companies";
 
			st = conn.prepareStatement(getQueryStatement);
 
			// Execute the Query, and get a java ResultSet
			ResultSet rs = st.executeQuery();
 
			ArrayList<Company> companies = new ArrayList<Company>();
			// Let's iterate through the java ResultSet
			while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
				int parentId = rs.getInt("parent_id");
				
				companies.add(new Company(id, name, parentId));
			}
			return companies.toArray(new Company[0]);
		}
		finally {
			if (st != null) {
				st.close();
			}
		}
	}
 
	// Simple log utility
	private static void log(String string) {
		System.out.println(string);
	}
}
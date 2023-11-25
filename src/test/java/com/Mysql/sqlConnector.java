package com.Mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.Statement;


public class sqlConnector {
	private final String dburl;
	private final String userName;
	private final String password;
	private final String driver;
	private Connection connection;
	private Statement statement;

	@SuppressWarnings("deprecation")
	sqlConnector(String jdbcUrl, String username, String password, String driver) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.dburl = jdbcUrl;
		this.userName = username;
		this.password = password;
		this.driver =  driver;
		connection = DriverManager.getConnection(dburl, userName, this.password);
		statement = connection.createStatement();
		Class.forName(this.driver).newInstance();
	}

	
	public boolean createTable(String query) throws SQLException {
		statement.executeUpdate(query);
		return true;
	}
	
	public boolean insertRecords(String query) throws SQLException {
		statement.executeUpdate(query);
		return true;
	}
	
	public boolean deleteTable(String query) throws SQLException {
		statement.executeUpdate(query);
		return true;
	}
	
	public void executeQuery(String query) throws SQLException {
		ResultSet resultSet = statement.executeQuery(query);
		ResultSetMetaData rsmd = (ResultSetMetaData) resultSet.getMetaData();
		int columnNumber = rsmd.getColumnCount();
		
		for (int i = 1; i <= columnNumber; i++) {
			System.out.printf("%-15s", rsmd.getColumnName(i));
        }
		System.out.println("");
		while (resultSet.next()) {
			for (int i = 1; i <= columnNumber; i++) {
				System.out.printf("%-15s", resultSet.getString(i));
			}
			System.out.println("");
		}
		
	}
	
	
	

}

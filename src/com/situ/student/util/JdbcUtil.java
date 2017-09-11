package com.situ.student.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.omg.CORBA.PUBLIC_MEMBER;



public class JdbcUtil {
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }

	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/java1707","root","root");
		
	}
	public static  void close(Connection connection, Statement statement, ResultSet resultSet) {
		if(resultSet != null){
			try{
				resultSet.close();
			}catch (SQLException e) {
		           e.printStackTrace();
		       }

		}
		if (statement != null) {
			try {
			statement.close();
			}catch (SQLException e) {
		           e.printStackTrace();
		       }

			
		}
		if (connection != null) {
			try {
			connection.close();
			}catch (SQLException e) {
		           e.printStackTrace();
		       }

      }
	}
	public static void close(Connection connection, Statement statement){
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
}
}

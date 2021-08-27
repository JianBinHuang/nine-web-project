package com.hjb.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 13699
 */
public class DBUtils {
	private static String driverName;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try {
			Properties properties = new Properties();
			InputStream inStream = DBUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			properties.load(inStream);
			driverName = properties.getProperty("driverName");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			Class.forName(driverName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeAll(AutoCloseable...cs){
		for(AutoCloseable c:cs){
			if(c!=null){
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

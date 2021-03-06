package com.springrestservicesample.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtility {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection != null)
			return connection;
		else {
			try {
				Properties prop = new Properties();
				InputStream inputStream = DBUtility.class.getClassLoader()
						.getResourceAsStream("/jdbc.properties");
				prop.load(inputStream);
				String driver = prop.getProperty("jdbc.driverClassName");
				String url = prop.getProperty("jdbc.url");
				String user = prop.getProperty("jdbc.username");
				String password = prop.getProperty("jdbc.password");
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return connection;
		}

	}

}
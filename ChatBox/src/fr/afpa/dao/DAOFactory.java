package fr.afpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {
	private String login;
	private String password;
	private String url;

	public DAOFactory(String url, String username, String password) {
		this.url = url;
		this.login = username;
		this.password = password;
	}

	public static DAOFactory getInstance() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
		DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/chatbox", "root", "root");
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Properties properties = new Properties();
		properties.setProperty("user", login);
		properties.setProperty("password", password);
		properties.setProperty("useSSL", "false");
		properties.setProperty("autoReconnect", "true");
		properties.setProperty("verifyServerCertificate", "false");
		return DriverManager.getConnection(url, properties);
	}

}

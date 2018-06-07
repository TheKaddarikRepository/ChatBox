package fr.afpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.afpa.chat.ListeUsers;
import fr.afpa.chat.Message;
import fr.afpa.chat.User;
import fr.afpa.security.PasswordRedirection;

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

	public DAOImplementation<User> getDaoUser() {
		return new DaoUser(this);
	}

	public DAOGroupItems<Message> getDAOMessageList(ListeUsers users) {
		return new DaoMessageList(this, users);
	}

	public DAOImplementation<PasswordRedirection> getDAOLogin() {
		return new DaoLogin(this);
	}

	public DAOGroupItems<User> getDAOMembers(ListeUsers users) {
		return new DaoMembers(this, users);
	}
}

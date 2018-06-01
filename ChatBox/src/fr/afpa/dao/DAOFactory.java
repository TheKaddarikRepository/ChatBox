package fr.afpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import fr.afpa.forum.Mission;
import fr.afpa.forum.Stagiaire;
import javafx.util.Pair;

public class DAOFactory {
	private String login;
	private String password;
	private String url;

	public DAOFactory(String url, String username, String password) {
		this.url = url;
		this.login = username;
		this.password = password;
	}

	public DAOImplementation<Stagiaire> getDAOStagiaires() {
		return new DAOStagiaire(this);

	}

	public DAOImplementation<Mission> getDAOMissions() {
		return new DAOMissions(this);
	}

	public DAOImplementation<Pair<Stagiaire, Mission>> getDAOAffectation() {
		return new DAOAffectation(this);

	}

	public static DAOFactory getInstance() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
		DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/forum", "root", "root");
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

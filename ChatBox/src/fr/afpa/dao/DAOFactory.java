package fr.afpa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import fr.afpa.chat.Group;
import fr.afpa.chat.ListeUsers;
import fr.afpa.chat.Message;
import fr.afpa.chat.User;
import fr.afpa.security.PasswordRedirection;
import fr.afpa.security.UserPermission;

public class DAOFactory {
	private static String login;
	private static String password;
	private static String url;
	private static ListeUsers listeUtilisateurs;

	public DAOFactory(String url, String username, String password) throws DaoException {
		DAOFactory.url = url;
		DAOFactory.login = username;
		DAOFactory.password = password;
		getListeUtilisateurs();
	}

	private DAOFactory() {
	}

	public static void init(ServletContext context) throws DaoException {
		try {
			Class.forName(context.getInitParameter("JDBC_DRIVER"));
			DAOFactory.url = context.getInitParameter("JDBC_URL");
			DAOFactory.login = context.getInitParameter("JDBC_LOGIN");
			DAOFactory.password = context.getInitParameter("JDBC_PASSWORD");
			getListeUtilisateurs();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DAOFactory getInstance() {
		DAOFactory instance = new DAOFactory();
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

	public DAOGroupItems<Message> getDAOMessageList() {
		return new DaoMessageList(this, listeUtilisateurs);
	}

	public DAOImplementation<PasswordRedirection> getDAOLogin() {
		return new DaoLogin(this, listeUtilisateurs);
	}

	public DAOGroupItems<User> getDAOMembers() {
		return new DaoMembers(this, listeUtilisateurs);
	}

	public DAOImplementation<Group> getDAOGroup() {
		return new DaoGroup(this, listeUtilisateurs);
	}

	public DAOLastRead getMember() {
		return new DaoMembers(this, listeUtilisateurs);
	}

	public IntDAOAuthentication getRoles() {
		return new DaoRoles(this);
	}

	public IntDAOImage getImage() {
		return new DaoImage();
	}

	/**
	 * @return the listeUtilisateurs
	 * @throws DaoException
	 */
	private static ListeUsers getListeUtilisateurs() throws DaoException {
		DaoUser daoUser = new DaoUser(DAOFactory.getInstance());
		DAOFactory.listeUtilisateurs = new ListeUsers(daoUser.getListOfElements(new User()));
		return DAOFactory.listeUtilisateurs;
	}

}

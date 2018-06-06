package fr.afpa.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.afpa.chat.User;
import fr.afpa.chat.UserException;

public class DaoUser implements DAOImplementation<User> {
	DAOFactory daoFactory;

	public DaoUser(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<User> getListOfElements(User element) throws DaoException {
		ArrayList<User> utilisateurSubscribed = new ArrayList<User>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT login, email, avatar, name, firstname FROM users WHERE login LIKE CONCAT(?,%) AND email LIKE CONCAT(?,%);");
			if (element.getLogin() != null) {
				statement.setString(1, element.getName());
			} else {
				statement.setString(1, "");
			}
			if (element.getEmail() != null) {
				statement.setString(2, element.getEmail());
			} else {
				statement.setString(2, "");
			}
			resultat = statement.executeQuery();
			while (resultat.next()) {
				String nom = resultat.getString("name");
				String email = resultat.getString("email");
				String image = resultat.getString("avatar");
				String pseudo = resultat.getString("login");
				String prenom = resultat.getString("firstname");

				User utilisateur = new User();
				utilisateur.setName(nom);
				utilisateur.setEmail(email);
				utilisateur.setAvatar(image);
				utilisateur.setLogin(pseudo);
				utilisateur.setFirstname(prenom);

				utilisateurSubscribed.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données", e);
		} catch (UserException e) {
			throw new DaoException("Les données de la base sont invalides", e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données", e);
			}
		}
		if (utilisateurSubscribed.isEmpty()) {
			throw new DaoException("L'utilisateur n'existe pas", null);
		} else {
			return utilisateurSubscribed;
		}
	}

	@Override
	public void insertElement(User element) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementUser = null;
		int id_user;
		PreparedStatement preparedStatementRole = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementUser = connexion
					.prepareStatement("INSERT INTO users (name,firstname,email,avatar) VALUES(?,?,?,?);");
			preparedStatementUser.setString(1, element.getName());
			preparedStatementUser.setString(2, element.getFirstname());
			preparedStatementUser.setString(3, element.getEmail());
			preparedStatementUser.setString(4, element.getAvatar());
			preparedStatementUser.executeUpdate();
			id_user = preparedStatementUser.getGeneratedKeys().getInt("id");
			preparedStatementRole = connexion.prepareStatement(
					"INSERT INTO roles (user_id,name,description, private_key, password) VALUES(?,?,?,?,?);");
			preparedStatementRole.setInt(1, id_user);
			preparedStatementRole.setString(2, element.getPermission().getName());
			preparedStatementRole.setString(3, element.getPermission().getText());
			preparedStatementRole.setBytes(4, element.getPermission().getKey());
			preparedStatementRole.setBytes(5, element.getPermission().getPassword());

			preparedStatementRole.executeUpdate();

			connexion.commit();
		} catch (SQLException | NoSuchAlgorithmException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de communiquer avec la base de données", e);
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données", e);
			}
		}

	}

	@Override
	public void removeElement(User element) throws DaoException {

	}

}

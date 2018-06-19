package fr.afpa.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.afpa.chat.User;
import fr.afpa.security.PermissionException;
import fr.afpa.security.UserPermission;

public class DaoRoles implements IntDAOAuthentication {
	DAOFactory daoFactory;

	public DaoRoles(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.afpa.dao.IntDAOAuthentication#insertElement(fr.afpa.chat.User)
	 */
	@Override
	public void insertElement(User element) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementRole = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementRole = connexion.prepareStatement(
					"INSERT INTO roles (user_id,name,description, private_key, password) VALUES((SELECT id FROM users WHERE login=?),?,?,?,?) ;");
			preparedStatementRole.setString(1, element.getName());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.afpa.dao.IntDAOAuthentication#getElementByUser(fr.afpa.chat.User)
	 */
	@Override
	public UserPermission getElementByUser(User element) throws DaoException {
		ArrayList<UserPermission> utilisateurSubscribed = new ArrayList<UserPermission>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT idroles, name, private_key, password FROM roles, users WHERE users.id=roles.user_id AND users.login=?;");

			statement.executeQuery();
			if (element.getLogin() != null) {
				statement.setString(1, element.getName());
			} else {
				statement.setString(1, "");
			}
			resultat = statement.executeQuery();
			while (resultat.next()) {
				String nom = resultat.getString("name");
				byte[] key = resultat.getBytes("private_key");
				byte[] password = resultat.getBytes("password");
				Integer id = Integer.valueOf(resultat.getInt("idroles"));

				UserPermission utilisateur = new UserPermission(nom, key, password);
				utilisateur.setId(id);

				utilisateurSubscribed.add(utilisateur);
			}
		} catch (SQLException | PermissionException e) {
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
		if (utilisateurSubscribed.size() != 1) {
			throw new DaoException("L'utilisateur n'existe pas.", null);
		} else {
			return utilisateurSubscribed.get(0);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.afpa.dao.IntDAOAuthentication#updateElement(fr.afpa.security.
	 * UserPermission)
	 */
	@Override
	public void updateElement(UserPermission element) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementRole = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);

			preparedStatementRole = connexion.prepareStatement(
					"UPDATE roles SET name=?, description=?, private_key=?, password=?  WHERE idroles=?;");
			preparedStatementRole.setString(1, element.getName());
			preparedStatementRole.setString(2, element.getText());
			preparedStatementRole.setBytes(3, element.getKey());
			preparedStatementRole.setBytes(4, element.getPassword());
			preparedStatementRole.setInt(5, element.getId().intValue());

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

}

package fr.afpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.afpa.chat.ListeUsers;
import fr.afpa.chat.User;
import fr.afpa.chat.UserException;
import fr.afpa.security.PasswordRedirection;

public class DaoLogin implements DAOImplementation<PasswordRedirection> {
	DAOFactory daoFactory;
	ListeUsers listeUtilisateurs;

	public DaoLogin(DAOFactory daoFactory, ListeUsers listeUtilisateurs) {
		super();
		this.daoFactory = daoFactory;
		this.listeUtilisateurs = listeUtilisateurs;
	}

	@Override
	public ArrayList<PasswordRedirection> getListOfElements(PasswordRedirection element) throws DaoException {
		ArrayList<PasswordRedirection> urlPassword = new ArrayList<PasswordRedirection>();

		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet firstStep = null;
		User admin = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT new_password.url_new, new_password.pending, users.login, users.name, users.firstname, users.email FROM new_password JOIN users ON new_password.id_user=users.id WHERE date_sent < NOW() AND pending='true' AND date_expire > NOW();");
			firstStep = statement.executeQuery();
			while (firstStep.next()) {
				String url = firstStep.getString("new_password.url_new");
				String state = firstStep.getString("new_password.pending");
				String login = firstStep.getString("users.login");
				String nom = firstStep.getString("users.name");
				String prenom = firstStep.getString("users.firstname");
				String email = firstStep.getString("users.email");

				admin = new User(nom, prenom, email, login);
				admin = listeUtilisateurs.getItem(admin);
				PasswordRedirection redir = new PasswordRedirection(url, admin, state);

				urlPassword.add(redir);
			}
		} catch (SQLException | UserException e) {
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
		if (urlPassword.isEmpty()) {
			throw new DaoException("Le groupe n'existe pas", null);
		} else {
			return urlPassword;
		}
	}

	@Override
	public void insertElement(PasswordRedirection element) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementRole = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementRole = connexion.prepareStatement(
					"INSERT INTO new_password (url_new, pending, date_sent, date_expire, id_user) VALUES (?,?,NOW(),DATE_ADD(NOW(),INTERVAL 7 DAY),(SELECT id FROM users WHERE login=?));");
			preparedStatementRole.setString(1, element.getUrl());
			preparedStatementRole.setString(2, element.getState());
			preparedStatementRole.setString(3, element.getUser().getLogin());

			preparedStatementRole.executeUpdate();

			connexion.commit();
		} catch (SQLException e) {
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
	public void removeElement(PasswordRedirection element) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateElement(PasswordRedirection element) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementRole = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementRole = connexion.prepareStatement(
					"UPDATE new_password SET pending=?, id_user=(SELECT id FROM users WHERE login=?) WHERE url_new=? ;");
			preparedStatementRole.setString(1, element.getState());
			preparedStatementRole.setString(2, element.getUser().getLogin());
			preparedStatementRole.setString(3, element.getUrl());

			preparedStatementRole.executeUpdate();

			connexion.commit();
		} catch (SQLException e) {
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

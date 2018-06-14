package fr.afpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import fr.afpa.chat.Group;
import fr.afpa.chat.ListeUsers;
import fr.afpa.chat.User;
import fr.afpa.chat.UserException;

public class DaoMembers implements DAOGroupItems<User>, DAOLastRead {
	private DAOFactory daoFactory;
	ListeUsers listeUtilisateurs;

	/**
	 * @param daoFactory
	 */
	public DaoMembers(DAOFactory daoFactory, ListeUsers listeUtilisateurs) {
		super();
		this.daoFactory = daoFactory;
		this.listeUtilisateurs = listeUtilisateurs;
	}

	public ArrayList<User> getListOfElements(Group element) throws DaoException {
		ArrayList<User> subscribers = new ArrayList<User>();

		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet firstStep = null;
		User member;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT users.login, users.email, users.name, users.firstname  FROM group_users, users WHERE users.id=group_users.user_id AND group_users.valid_date<NOW() AND group_users.banned_date==NULL AND gourp_users.grp_id=?;");
			if (element.getGroup_id() != null) {
				statement.setInt(1, element.getGroup_id().intValue());
			} else {
				throw new DaoException("Impossible de communiquer avec la base de données", null);
			}
			firstStep = statement.executeQuery();
			while (firstStep.next()) {
				String login = firstStep.getString("users.login");
				String nom = firstStep.getString("users.name");
				String prenom = firstStep.getString("users.firstname");
				String email = firstStep.getString("users.email");

				member = new User(nom, prenom, email, login);
				member = listeUtilisateurs.getItem(member);

				subscribers.add(member);
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
		if (subscribers.isEmpty()) {
			throw new DaoException("Le groupe n'existe pas", null);
		} else {
			return subscribers;
		}

	}

	@Override
	public void insertElement(User element, Group groupe) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementUser = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementUser = connexion.prepareStatement(
					"INSERT INTO group_users (grp_id, user_id, valid_date, banned_date, last_read) VALUES(?,(SELECT id FROM users WHERE login=?),?,null,?);");
			preparedStatementUser.setInt(1, groupe.getGroup_id().intValue());
			preparedStatementUser.setString(2, element.getLogin());
			preparedStatementUser.setDate(3, new java.sql.Date(new Date().getTime()));
			preparedStatementUser.setDate(4, new java.sql.Date(new Date().getTime()));

			preparedStatementUser.executeUpdate();

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
	public void removeElement(User element, Group groupe) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateElement(User element, Group groupe, Properties modification) throws DaoException {
		// TODO Auto-generated method stub

	}

	public LocalDateTime getLastRead(User user, Group groupe) throws DaoException {
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet firstStep = null;
		LocalDateTime lastRead = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT last_read  FROM group_users, users WHERE users.id=group_users.user_id AND group_users.banned_date=NULL AND users.login=? AND group_users.grp_id=?;");
			if (user.getLogin() != null) {
				statement.setString(1, user.getLogin());
			} else {
				statement.setString(1, "");
			}
			if (groupe.getGroup_id() != null) {
				statement.setInt(1, groupe.getGroup_id().intValue());
			} else {
				throw new DaoException("Impossible de definir le groupe de discussion.", null);
			}
			firstStep = statement.executeQuery();
			while (firstStep.next()) {
				lastRead = LocalDateTime.parse(firstStep.getString("last_read").replaceAll(" ", "T"));
			}
		} catch (SQLException e) {
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
		if (lastRead == null) {
			throw new DaoException("Le groupe n'existe pas", null);
		} else {
			return lastRead;
		}
	}
}

package fr.afpa.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import fr.afpa.chat.Group;
import fr.afpa.chat.GroupException;
import fr.afpa.chat.ListeUsers;
import fr.afpa.chat.User;
import fr.afpa.chat.UserException;

public class DaoGroup implements DAOImplementation<Group> {
	DAOFactory daoFactory;
	ListeUsers listeUtilisateurs;

	/**
	 * @param daoFactory
	 */
	public DaoGroup(DAOFactory daoFactory, ListeUsers listeUtilisateurs) {
		super();
		this.daoFactory = daoFactory;
		this.listeUtilisateurs = listeUtilisateurs;
	}

	@Override
	public ArrayList<Group> getListOfElements(Group element) throws DaoException {
		ArrayList<Group> subscriptions = new ArrayList<Group>();

		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet firstStep = null;
		User admin;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT idgroups, groups.name, group_type, users.login, users.email, users.name, users.firstname, users.avatar  FROM groups, users WHERE users.id=groups.admin AND groups.end_date=NULL AND groups.name LIKE CONCAT(?,%);");
			if (element.getName() != null) {
				statement.setString(1, element.getName());
			} else {
				statement.setString(1, "");
			}
			firstStep = statement.executeQuery();
			while (firstStep.next()) {
				int grp_id = firstStep.getInt("idgroups");
				String grp_name = firstStep.getString("groups.name");
				String grp_type = firstStep.getString("group_type");
				String login = firstStep.getString("users.login");
				String nom = firstStep.getString("users.name");
				String prenom = firstStep.getString("users.firstname");
				String image = firstStep.getString("users.avatar");
				String email = firstStep.getString("users.email");

				admin = new User(nom, prenom, email, login);
				admin = listeUtilisateurs.getItem(admin);
				Group groupe = new Group(grp_name, admin, grp_type);
				groupe.setGroup_id(Integer.valueOf(grp_id));

				subscriptions.add(groupe);
			}
		} catch (SQLException | UserException | GroupException e) {
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
		if (subscriptions.isEmpty()) {
			throw new DaoException("Le groupe n'existe pas", null);
		} else {
			return subscriptions;
		}

	}

	@Override
	public void insertElement(Group element) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementUser = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementUser = connexion.prepareStatement(
					"INSERT INTO groups (name,admin, creation_date, end_date, group_type) VALUES(?,(SELECT id FROM users WHERE login=?),?,null,?);");
			preparedStatementUser.setString(1, element.getName());
			preparedStatementUser.setString(2, element.getAdmin().getLogin());
			preparedStatementUser.setDate(3, new java.sql.Date(new Date().getTime()));
			preparedStatementUser.setString(4, element.getType().toString());
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
	public void removeElement(Group element) throws DaoException {
		// TODO Auto-generated method stub

	}

	

}

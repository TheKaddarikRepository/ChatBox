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
import fr.afpa.chat.Message;
import fr.afpa.chat.MessageException;
import fr.afpa.chat.User;
import fr.afpa.chat.UserException;

public class DaoMessageList implements DAOGroupItems<Message> {
	private DAOFactory daoFactory;
	ListeUsers listeUtilisateurs;

	/**
	 * @param daoFactory
	 */
	public DaoMessageList(DAOFactory daoFactory, ListeUsers listeUtilisateurs) {
		super();
		this.daoFactory = daoFactory;
		this.listeUtilisateurs = listeUtilisateurs;
	}

	@Override
	public ArrayList<Message> getListOfElements(Group element) throws DaoException {
		ArrayList<Message> discussion = new ArrayList<Message>();

		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet firstStep = null;
		User member;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT users.login, users.email, users.name, users.firstname, messages.content, messages.attachement, messages.date_sent, messages.type  FROM messages, users WHERE users.id=messages.author_id AND messages.group_id=?;");
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

				String content = firstStep.getString("messages.content");
				String attachement = firstStep.getString("messages.attachement");
				LocalDateTime dateEnvoie = firstStep.getTimestamp("messages.date_sent").toLocalDateTime();
				String typeMessage = firstStep.getString("messages.type");

				Message message = new Message(content, attachement, member, typeMessage, dateEnvoie);

				discussion.add(message);
			}
		} catch (SQLException | UserException | MessageException e) {
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
		if (discussion.isEmpty()) {
			throw new DaoException("Le groupe n'existe pas", null);
		} else {
			return discussion;
		}
	}

	@Override
	public void insertElement(Message element, Group groupe) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatementUser = null;

		try {
			connexion = daoFactory.getConnection();
			connexion.setAutoCommit(false);
			preparedStatementUser = connexion.prepareStatement(
					"INSERT INTO messages (group_id,content, attachment, date_sent, author_id, type) VALUES(?,?,?,?,(SELECT id FROM users WHERE login=?),?);");
			preparedStatementUser.setInt(1, groupe.getGroup_id().intValue());
			preparedStatementUser.setString(2, element.getContent());
			preparedStatementUser.setString(3, element.getAttachment());
			preparedStatementUser.setDate(4, new java.sql.Date(new Date().getTime()));
			preparedStatementUser.setString(5, element.getAuthor().getLogin());
			preparedStatementUser.setString(6, element.getType().toString());

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
	public void removeElement(Message element, Group groupe) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateElement(Message element, Group groupe, Properties modification) throws DaoException {
		// TODO Auto-generated method stub

	}

}

package fr.afpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.afpa.chat.Group;
import fr.afpa.chat.ListeUsers;
import fr.afpa.forum.Mission;
import fr.afpa.forum.PojoException;

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
		ArrayList<Group> utilisateurSubscription = new ArrayList<Group>();
		Connection connexion = null;
		PreparedStatement statement = null;
		ResultSet resultat = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion
					.prepareStatement("SELECT name,  FROM missions WHERE mission=\\'\" + mission.getNom() + \"\\';");
			statement.setString(1, element.getName());
			statement.setString(2, element.getAdmin().getLogin());

			statement.executeQuery();
			if (mission.getNom() != null) {
				resultat = statement.executeQuery(
						"SELECT mission, description FROM missions WHERE mission=\'" + mission.getNom() + "\';");
			} else if (mission.getDescription() != null) {
				resultat = statement.executeQuery("SELECT mission, description FROM missions WHERE description LIKE \'%"
						+ mission.getDescription() + "%\';");
			} else {
				resultat = statement.executeQuery("SELECT mission, description FROM missions;");
			}
			while (resultat.next()) {
				String nom = resultat.getString("mission");
				String description = resultat.getString("description");

				Mission utilisateur = new Mission();
				utilisateur.setNom(nom);
				utilisateur.setDescription(description);

				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données", e);
		} catch (PojoException e) {
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
		if (utilisateurs.isEmpty()) {
			throw new DaoException("La mission n'existe pas", null);
		} else {
			return utilisateurs;
		}

	}

	@Override
	public void insertElement(Group element) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeElement(Group element) throws DaoException {
		// TODO Auto-generated method stub

	}

}

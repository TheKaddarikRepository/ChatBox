package fr.afpa.chat;

import java.util.ArrayList;

import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DAOImplementation;
import fr.afpa.dao.DaoException;

public class ListeUsers extends Listing<User> {
	private DAOFactory daoFactory;

	/**
	 * @param liste
	 */
	public ListeUsers(ArrayList<User> liste) {
		super(liste);
		this.daoFactory = fr.afpa.dao.DAOFactory.getInstance();
	}

	public ArrayList<User> getListe() throws DaoException {
		return liste;
	}

	@Override
	public void setListe(ArrayList<User> liste) {
		this.liste = liste;
	}

	@Override
	public void addItem(User item) throws DaoException {
		DAOImplementation<User> myDAO = daoFactory.getDaoUser();
		myDAO.insertElement(item);
		liste.add(item);
	}

	@Override
	public void suppItem(User item) throws DaoException {
		DAOImplementation<User> myDAO = daoFactory.getDaoUser();
		myDAO.removeElement(item);
		liste.remove(item);
	}

	@Override
	public User getItem(User item) throws DaoException {
		if (liste.isEmpty()) {
			DAOImplementation<User> myDAO = daoFactory.getDaoUser();
			this.liste = myDAO.getListOfElements(new User());
		}
		int index = liste.indexOf(item);
		if (index != -1) {
			return liste.get(index);
		} else {
			throw new DaoException("L'utilisateur n'est pas dans la liste.", null);
		}

	}

}

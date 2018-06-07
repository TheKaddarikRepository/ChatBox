package fr.afpa.dao;

import java.util.ArrayList;
import java.util.Properties;

import fr.afpa.chat.Group;

public interface DAOGroupItems<T> {
	/**
	 * to get all the messages/users sent into a group of discussion.
	 * 
	 * @param element
	 * @return
	 * @throws DaoException
	 */
	public ArrayList<T> getListOfElements(Group element) throws DaoException;

	/**
	 * To insert one message/user in the database.
	 * 
	 * @param element
	 * @throws DaoException
	 */
	public void insertElement(T element, Group groupe) throws DaoException;

	/**
	 * to remove one message/user from the database.
	 * 
	 * @param element
	 * @throws DaoException
	 */
	public void removeElement(T element, Group groupe) throws DaoException;

	/**
	 * The modification fields indicates the attributes changed.
	 * @param element
	 * @param groupe
	 * @param modification
	 * @throws DaoException
	 */
	public void updateElement(T element, Group groupe, Properties modification) throws DaoException;
}

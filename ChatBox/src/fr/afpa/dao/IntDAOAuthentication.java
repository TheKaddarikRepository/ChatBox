package fr.afpa.dao;

import fr.afpa.chat.User;
import fr.afpa.security.UserPermission;

public interface IntDAOAuthentication {

	void insertElement(User element) throws DaoException;

	UserPermission getElementByUser(User element) throws DaoException;

	void updateElement(UserPermission element) throws DaoException;

}
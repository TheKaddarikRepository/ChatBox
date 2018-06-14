package fr.afpa.dao;

import java.time.LocalDateTime;

import fr.afpa.chat.Group;
import fr.afpa.chat.User;

public interface DAOLastRead {

	public LocalDateTime getLastRead(User user, Group groupe) throws DaoException ;
}

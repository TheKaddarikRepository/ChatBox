package fr.afpa.chat;

import fr.afpa.dao.DAOImplementation;

public class LoungeFactory {
	private DAOImplementation daoMessages;

	public Lounge getPrivateLounge() {
		return new PrivateLounge();
	}

	public Lounge getPublicLounge() {
		return new PublicLounge();
	}

	public Lounge getFriendLounge() {
		return new FriendLounge();
	}

}

package fr.afpa.chat;

import java.util.ArrayList;

public class PublicLounge implements Lounge {
	private Group groupe;
	private User reader;

	/**
	 * @param groupe
	 * @param reader
	 */
	public PublicLounge(Group groupe, User reader) {
		super();
		this.groupe = groupe;
		this.reader = reader;
	}

	@Override
	public ArrayList<Message> getUnread() {
		ArrayList<Message> unread = new ArrayList<Message>();
		DAOLastRead memeber;
		for (Message mess : groupe.getDiscussion(reader)) {

		}
		return null;
	}

	@Override
	public ArrayList<Message> sendMessage(Message myMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> acceptMember(Message memberRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> bannMember(Message memberBanned) {
		// TODO Auto-generated method stub
		return null;
	}
}

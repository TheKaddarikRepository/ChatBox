package fr.afpa.chat;

import java.util.ArrayList;

public interface Lounge {
	
	public ArrayList<Message> getUnread();
	public ArrayList<Message> sendMessage(Message myMessage);
	public ArrayList<User> acceptMember(Message memberRequest);
	public ArrayList<User> bannMember(Message memberBanned);
	

}

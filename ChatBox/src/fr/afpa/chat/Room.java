package fr.afpa.chat;

import java.util.List;

public class Room extends Group {
	private String name;
	private String description;
	private List<Message> discussion;
	private int id;
	private boolean ouvert;

	public Room(Association status, String name, String description, List<Message> discussion, int id, boolean ouvert) {
		super(status);
		this.name = name;
		this.description = description;
		this.discussion = discussion;
		this.id = id;
		this.ouvert = ouvert;
	}

}

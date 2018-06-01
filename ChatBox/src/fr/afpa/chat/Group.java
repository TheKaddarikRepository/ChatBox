package fr.afpa.chat;

import java.util.List;

public class Group {
	private String name;
	private User admin;
	private List<User> members;
	private List<Message> discussion;

	/**
	 * @param name
	 * @param admin
	 * @param members
	 * @param discussion
	 */
	public Group(String name, User admin, List<User> members, List<Message> discussion) throws GroupException{
		super();
		this.setName(name);
		this.setAdmin(admin);
		this.setMembers(members);
		this.setDiscussion(discussion);
	}
	
	public Group(String name, User admin) throws GroupException{
		super();
		this.setName(name);
		this.setAdmin(admin);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<Message> getDiscussion() {
		return discussion;
	}

	public void setDiscussion(List<Message> discussion) {
		this.discussion = discussion;
	}

}

package fr.afpa.chat;

import java.util.List;

public class Group {
	private String name;
	private User admin;
	private List<User> members;
	private List<Message> discussion;
	private GroupType type =GroupType.PUBLIC;

	/**
	 * 
	 * @param name
	 * @param admin
	 * @param members
	 * @param discussion
	 * @param type
	 * @throws GroupException
	 */
	public Group(String name, User admin, List<User> members, List<Message> discussion, GroupType type) throws GroupException{
		super();
		this.setName(name);
		this.setAdmin(admin);
		this.setMembers(members);
		this.setDiscussion(discussion);
		this.type=type;
	}
	
	public Group(String name, User admin, GroupType type) throws GroupException{
		super();
		this.setName(name);
		this.setAdmin(admin);
		this.type=type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws GroupException{
		this.name = name;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) throws GroupException{
		this.admin = admin;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) throws GroupException{
		this.members = members;
	}

	public List<Message> getDiscussion() {
		return discussion;
	}

	public void setDiscussion(List<Message> discussion) throws GroupException{
		this.discussion = discussion;
	}

}

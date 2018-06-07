package fr.afpa.chat;

import java.util.List;

import fr.afpa.dao.DAOFactory;
import fr.afpa.dao.DAOGroupItems;
import fr.afpa.dao.DaoException;

public class Group {
	private Integer group_id;
	private String name;
	private User admin;
	private List<User> members;
	private List<Message> discussion;
	private GroupType type = GroupType.PUBLIC;

	/**
	 * 
	 * @param name
	 * @param admin
	 * @param members
	 * @param discussion
	 * @param type
	 * @throws GroupException
	 */
	public Group(String name, User admin, List<User> members, List<Message> discussion, GroupType type)
			throws GroupException {
		super();
		this.setName(name);
		this.setAdmin(admin);
		this.setMembers(members);
		this.setDiscussion(discussion);
		this.type = type;
	}

	public Group(String name, User admin, GroupType grp_type) throws GroupException {
		super();
		this.setName(name);
		this.setAdmin(admin);
		this.type = grp_type;
	}

	public Group(String name, User admin, String grp_type) throws GroupException {
		super();
		this.setName(name);
		this.setAdmin(admin);
		this.setType(grp_type);
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		switch (type) {
		case "PUBLIC":
			this.type = GroupType.PUBLIC;
			break;
		case "PRIVATE":
			this.type = GroupType.PRIVATE;
			break;
		case "FRIEND":
			this.type = GroupType.FRIEND;
			break;
		default:
			this.type = GroupType.PRIVATE;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws GroupException {
		this.name = name;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) throws GroupException {
		this.admin = admin;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) throws GroupException {
		this.members = members;
	}

	public List<Message> getDiscussion(User reader) {
		if (this.members.contains(reader)) {

		} else {
			return null;
		}
		return discussion;
	}

	public void publishMessage(Message newMessage) {
		this.discussion.add(newMessage);
	}

	public void setDiscussion(List<Message> discussion) throws GroupException {
		this.discussion = discussion;
	}

	/**
	 * @return the group_id
	 */
	public Integer getGroup_id() {
		return group_id;
	}

	/**
	 * @return the discussion
	 * @throws DaoException
	 */
	public List<Message> getDiscussion(ListeUsers users) throws DaoException {
		DAOGroupItems<Message> myDAO = DAOFactory.getInstance().getDAOMessageList(users);
		return myDAO.getListOfElements(this);
	}

	/**
	 * @param group_id
	 *            the group_id to set
	 */
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	/**
	 * @return the type
	 */
	public GroupType getType() {
		return type;
	}

}

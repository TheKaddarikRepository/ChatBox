package fr.afpa.chat;

import java.util.List;

public class User {
	private String name;
	private String firstname;
	private String email;
	private String password;
	private String login;
	private String avatar;
	private String ip_address;
	private List<Group> Memberships;
	
	public User() {
		
	}

	/**
	 * @param name
	 * @param firstname
	 * @param email
	 * @param password
	 * @param login
	 * @param avatar
	 * @param ip_address
	 * @param memberships
	 */
	public User(String name, String firstname, String email, String password, String login, String avatar,
			String ip_address, List<Group> memberships) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.login = login;
		this.avatar = avatar;
		this.ip_address = ip_address;
		Memberships = memberships;
	}
	
}

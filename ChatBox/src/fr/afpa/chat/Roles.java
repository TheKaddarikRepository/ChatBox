package fr.afpa.chat;

public enum Roles {
	User("Un utilisateur du site de chat. Il ne peut administrer que les groups qu'il à créer ou son groupe d'amis."), Admin(
			"Un utilisateur pouvant administrer les groupes publique.");
	private Roles(String desc) {
		this.description = desc;
	}

	private String description;

	public String getDescription() {
		return this.description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

package fr.afpa.chat;

public enum GroupType {
PUBLIC("all"), PRIVATE("users"), FRIEND("personnel");
	private GroupType(String lounge) {
		type=lounge;
	}
	private LoungeFactory loungeFactory=new LoungeFactory();
	private String type;
	public Lounge getLounge() {
		switch (type) {
		case "all":
			return loungeFactory.getPublicLounge();
		case "users":
			return loungeFactory.getPrivateLounge();
		case "personnel":
			return loungeFactory.getFriendLounge();
		default:
			return loungeFactory.getPublicLounge();
		}
	}
}

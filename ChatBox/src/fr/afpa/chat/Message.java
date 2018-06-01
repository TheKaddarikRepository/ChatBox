package fr.afpa.chat;

public class Message {
	private String content;
	private String attachment;
	private User author;
	private Group recipient;
	private MessageType type;
	
	public Message(String content, String attachment, User author, Group recipient, MessageType type) throws MessageException {
		super();
		this.setContent(content);
		this.setAttachment(attachment);
		this.setAuthor(author);
		this.setRecipient(recipient);
		this.setType(type);
	}
	
	public Message(String content, User author, Group recipient, MessageType type) throws MessageException {
		super();
		this.setContent(content);
		this.setAuthor(author);
		this.setRecipient(recipient);
		this.setType(type);
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) throws MessageException{
		this.attachment = attachment;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
	}

	public Group getRecipient() {
		return recipient;
	}

	public MessageType getType() {
		return type;
	}

	private void setContent(String content) throws MessageException{
		this.content = content;
	}

	private void setAuthor(User author) throws MessageException{
		this.author = author;
	}

	private void setRecipient(Group recipient) throws MessageException{
		this.recipient = recipient;
	}

	private void setType(MessageType type) throws MessageException{
		this.type = type;
	}
	
}

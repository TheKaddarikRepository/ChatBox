package fr.afpa.chat;

import java.io.File;
import java.time.LocalDateTime;

public class Message {
	private String content;
	private String attachment;
	private User author;
	private MessageType type;
	private LocalDateTime sentDate;

	public Message(String content, String attachment, User author, String type, LocalDateTime date)
			throws MessageException {
		super();
		this.setContent(content);
		this.setAttachment(attachment);
		this.setAuthor(author);
		this.setType(type);
		this.setSentDate(date);
	}

	public Message(String content, User author, MessageType type) throws MessageException {
		super();
		this.setContent(content);
		this.setAuthor(author);
		this.type = type;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) throws MessageException {
		File testFile = new File(attachment);
		if (testFile.exists()) {
			this.attachment = attachment;
		} else {
			throw new MessageException("La pièce jointe n'existe pas.");
		}
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
	}

	public MessageType getType() {
		return type;
	}

	private void setContent(String content) throws MessageException {
		this.content = content;
	}

	private void setAuthor(User author) throws MessageException {
		this.author = author;
	}

	private void setType(String type) throws MessageException {
		switch (type) {
		case "INFO":
			this.type = MessageType.INFO;
			break;
		case "REQUEST":
			this.type = MessageType.REQUEST;
			break;
		case "ANSWER":
			this.type = MessageType.ANSWER;
			break;
		default:
			throw new MessageException("Le type de message n'est pas définit.");
		}
	}

	private void setSentDate(LocalDateTime date) throws MessageException {
		if (date.isBefore(LocalDateTime.now())) {
			this.sentDate = date;
		} else {
			throw new MessageException("La date d'envoie est dans le futur.");
		}
	}
}

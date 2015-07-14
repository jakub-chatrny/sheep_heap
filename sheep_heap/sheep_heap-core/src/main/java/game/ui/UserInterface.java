package game.ui;

import java.util.ArrayList;
import java.util.List;


/**
 * User interface.
 * @author jchatrny
 *
 */
public class UserInterface {
	private Info info;
	private List<Message> messages;
	
	public UserInterface(){
		this.info = new Info();
		messages = new ArrayList<Message>();
	}

	/**
	 * @return the info
	 */
	public Info getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(Info info) {
		this.info = info;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
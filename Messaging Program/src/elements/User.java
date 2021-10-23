
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import java.util.ArrayList;

import boxes.Inbox;
import boxes.Outbox;

/**
 * 
 * @author Yavuz
 *
 */
public class User {
	
	/**
	 * id is the id of this user.
	 * Inbox of this user is created and named inbox.
	 * Outbox of this user is created and named outbox.
	 * List of friends of this user is created.
	 */
	private int id;
	Inbox inbox = new Inbox(this);
	Outbox outbox = new Outbox(this);
	private ArrayList<User> friends = new ArrayList<User>();
	
	/**
	 * constructs a user with given id.
	 * @param id is the id of user.
	 */
	public User(int id){
		this.id = id;
	}
	
	/**
	 * adds two user as friends if they are not already.
	 * @param other is the user who is going to be added to the friends list.
	 */
	public void addFriend(User other) {
		if(!(this.isFriendsWith(other))) { //if they are already friends, do nothing.
			if(this.id == other.id) { //if this user and other are the same, make sure the addition is done once.
				friends.add(other);
			} else { 
				friends.add(other);
				other.friends.add(this);
			}
		}
	}
	
	/**
	 * removes two users from each others' friends list.
	 * @param other is the user who is going to be removed from the friends list.
	 */
	public void removeFriend(User other) {
		if(this.isFriendsWith(other)) { //if they are not friends, do nothing.
			if(this.id == other.id) { //if this user and other are the same, make sure remove operation is done once.
				friends.remove(other);
			} else {
				friends.remove(other);
				other.friends.remove(this);
			}
		}
	}
	
	/**
	 * checks whether two users are friends. 
	 * @param other is the user who is going to be checked.
	 * @return true if this user's friends list contains other, otherwise false.
	 */
	public boolean isFriendsWith(User other) {
		return friends.contains(other);
	}
	
	/**
	 * creates a message, adds it to this user's outbox and sends it to server.
	 * @param receiver is the user who is going to receive the message created.
	 * @param body is the string, message body of the message created.
	 * @param time is the timeStampSent of the message created. 
	 * @param server is the server.
	 */
	public void sendMessage(User receiver, String body, int time, Server server) {
		Message outgoing = new Message(this, receiver, body, server, time); //outgoing is the message created.
		this.outbox.sent.add(outgoing); //added to outbox.
		
		server.msgs.add(outgoing); //added to server.
		server.currentSize += outgoing.getBody().length(); //currentSize of the server is changed.
	}
	 
	/**
	 * 
	 * @return the id of this user.
	 */
	int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return the inbox of this user.
	 */
	public Inbox getInbox() {
		return inbox;
	}
	
	/**
	 * 
	 * @return the outbox of this user.
	 */
	public Outbox getoutbox() {
		return outbox;
	}
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


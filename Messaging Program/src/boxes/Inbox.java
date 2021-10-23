
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import elements.Message;
import elements.Server;
import elements.User;

/**
 * 
 * @author Yavuz
 *
 */
public class Inbox extends Box {
	
	/**
	 * unread is a stack which stores the messages have not been read yet.
	 * read is a queue which stores the messages have been read.
	 */
	public Stack<Message> unread = new Stack<Message>();
	public Queue<Message> read = new LinkedList<Message>(); 
	
	/**
	 * constructs an inbox with given user.
	 * @param user is the owner of this inbox.
	 */
	public Inbox(User user){
		super(user);
	}
	
	/**
	 * receives messages from server and pushes messages to user's inbox.
	 * @param server is the server.
	 * @param time is timeStampReceived of the message received.
	 */
	public void receiveMessages(Server server, int time) {
		Iterator<Message> itr = server.msgs.iterator(); //iterator over messages in the server.
		while(itr.hasNext()) {
			Message toUnread = itr.next();
			if(toUnread.getReceiver().equals(this.owner)) { //if receiver of the message is not this inbox's owner, do nothing.
				if(this.owner.isFriendsWith(toUnread.getSender())) { //if sender and receiver are no friends, do nothing.
					toUnread.receivedsetter(time); //changes timeStampReceived of the message.
					this.unread.push(toUnread); //pushes to unread messages stack.
					
					itr.remove(); //the message in operation removed from server.
					server.setCurrentSize(server.getCurrentSize() - toUnread.getBody().length()); //currentSize of server resetted.
				}
			}
		}
	}
	
	/**
	 * reads messages, removes from unread and sends it to read.
	 * @param num is the number of messages to be read.
	 * @param time is timeStampRead of the message read.
	 * @return time passed during operation.
	 */
	public int readMessages(int num, int time) { 
		int inStack = 0;
		if(!(unread.isEmpty())) inStack = unread.size(); //inStack is the total number of messages in unread stack.
		
		if(num == 0 || num > inStack) { //if parameter is 0 or bigger than size of the stack, read all messages.
			for(int i=0; i<inStack; i++) {
				Message x = unread.pop(); //message is removed from unread.
				x.readsetter(time); //changes timeStampRead of the message.
				time++; //time is increased by one after reading a message.
				read.add(x); //message is added to read.
			}
			if(inStack == 0) return 1; //if no messages read, time passed (1) unit.
			else return inStack; //if all messages read, time passed (size of unread) unit.
		}
		else {
			for(int i = 0; i < num; i++) { //read num piece of messages.
				Message x = unread.pop(); //message is removed from unread.
				x.readsetter(time); //changes timeStampRead of the message.
				time++; //time is increased by one after reading a message.
				read.add(x); //message is added to read.
			}
			return num; //return time passed.
		}
		
	}
	
	/**
	 * reads messages, removes from unread and sends it to read.
	 * @param sender is the sender to be checked.
	 * @param time is timeStampRead of the message.
	 * @return time passed during operation.
	 */
	public int readMessages(User sender, int time) {
		ListIterator<Message> itr = this.unread.listIterator(unread.size()); //iterator over messages in unread stack.
		int counter = 0; //counts the messages read.
		while(itr.hasPrevious()) {
			Message checkUser = itr.previous(); 
			if(checkUser.getSender().equals(sender)) { //if sender given in parameter is not equal to sender of the message, do nothing.
				checkUser.readsetter(time); //changes timeStampRead of the message.
				time++; //time is increased by one after reading a message. 
				read.add(checkUser); //message is added to read.
				counter++;
				itr.remove(); //message in operation is removed from unread stack.
			}
		}
		if (counter == 0) return 1; //returns 1 if no messages read.
		else return counter; //returns time passed.
	}
	
	/**
	 * reads message with given id.
	 * @param msgId is the id of the message to be read.
	 * @param time is timeStampRead of the message.
	 */
	public void readMessage(int msgId, int time) {
		ListIterator<Message> itr = unread.listIterator(unread.size()); //iterator over messages in unread stack.
		while(itr.hasPrevious()) {
			Message checkId = itr.previous();
			if(checkId.getId() == msgId) { //if id given in parameter is not equal to id of the message, do nothing.
				checkId.readsetter(time); //changes timeStampRead of the message.
				
				read.add(checkId); //message is added to read.
				itr.remove(); //message in operation is removed from unread stack.
			}
		}
	}
	
	/**
	 * checks whether message is received or not.
	 * @param rec is the message to be checked.
	 * @return true if received, false otherwise.
	 */
	public boolean didReceived(Message rec) {
		return (unread.contains(rec) || read.contains(rec));
	}
	
	/**
	 * checks whether message is read or not.
	 * @param red is the message to be checked.
	 * @return true if read, false otherwise.
	 */
	public boolean didRead(Message red) {
		return read.contains(red);
	}
	
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


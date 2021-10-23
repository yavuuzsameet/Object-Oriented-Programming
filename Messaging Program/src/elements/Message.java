
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

/**
 * 
 * @author Yavuz
 *
 */
public class Message implements Comparable<Message> {
	
	/**
	 * numOfMessages is the number of messages total in this program.
	 * id is the id of this message.
	 * body is the message body of this message.
	 * sender is the user who sends this message.
	 * receiver is the user who receives this message.
	 * timeStampSent is the time of which this message is sent.
	 * timeStampRead is the time of which this message is read.
	 * timeStampReceived is the time of which this message is received.
	 */
	private static int numOfMessages = 0;
	private int id;
	private String body;
	private User sender;
	private User receiver;
	private int timeStampSent;
	private int timeStampRead;
	private int timeStampReceived;
	
	/**
	 * constructs a message with given parameters.
	 * @param sender is the user who sends this message.
	 * @param receiver is the user who received this message.
	 * @param body is the message body of this message.
	 * @param server is the server.
	 * @param time is the time of which this message is sent. 
	 */
	Message(User sender, User receiver, String body, Server server, int time){
		this.id = numOfMessages;
		numOfMessages++;
		this.sender = sender;
		this.receiver = receiver;
		this.body = body;
		this.timeStampSent = time;
	}
	
	/**
	 * changes timeStampSent.
	 * @param time is the new time.
	 */
	public void sentsetter(int time) {
		timeStampSent = time;
	}
	
	/**
	 * changes timeStampRead.
	 * @param time is the time of which this message is read.
	 */
	public void readsetter(int time) {
		timeStampRead = time;
	}
	
	/**
	 * changes timeStampReceived.
	 * @param time is the time of which this message is received.
	 */
	public void receivedsetter(int time) {
		timeStampReceived = time;
	}
	
	/**
	 * 
	 * @return the id of this message.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return the body of this message.
	 */
	public String getBody() {
		return body;
	}
	
	/**
	 * checks whether two messages are same.
	 * @param o is the object which is going to be checked.
	 * @return true if same, false otherwise.
	 */
	public boolean equals(Object o) {
		if(o instanceof Message) { //if o is not a message return false.
			if(this.id == ((Message) o).getId()) { //check ids.
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	/**
	 * compares two messages according to their body length.
	 * @param o is the message which is going to be compared to this message. 
	 */
	public int compareTo(Message o) {
		if(this.body.length() > o.body.length()) return 1; //if this message is longer, return 1.
		else if(this.body.length() < o.body.length()) return -1; //if o is longer, return -1.
		else return 0; //if equal, return 0.
	}
	
	/**
	 * @return this message as string.
	 */
	public String toString() {
		String x = "\tFrom: " + this.sender.getId() + " To: " + this.receiver.getId() + "\n";
		String received = "";
		if(this.receiver.inbox.didReceived(this)) { //if this message has not received yet, do nothing.
			received = Integer.toString(timeStampReceived);
		}
		String read = "";
		if(this.receiver.inbox.didRead(this)) { //if this message has not read yet, do nothing.
			read = Integer.toString(timeStampRead);
		}
		String y = "\tReceived: " + received + " Read: " + read + "\n";
		String z = "\t" + this.body;
		
		return x+y+z;
		
	}
	
	/**
	 * 
	 * @return the receiver of this message.
	 */
	public User getReceiver() {
		return receiver;
	}
	
	/**
	 * 
	 * @return the sender of this message.
	 */
	public User getSender() {
		return sender;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


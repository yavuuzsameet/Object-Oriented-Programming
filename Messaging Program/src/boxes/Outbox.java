
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package boxes;

import java.util.LinkedList;
import java.util.Queue;

import elements.Message;
import elements.User;

/**
 * 
 * @author Yavuz
 *
 */
public class Outbox extends Box {
	
	/**
	 * send is the queue which stores the messages sent.
	 */
	public Queue<Message> sent = new LinkedList<Message>();
	
	/**
	 * constructs an outbox with given owner.
	 * @param owner is the owner of this outbox.
	 */
	public Outbox(User owner){
		super(owner);
	}
	
	
	
	
	
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


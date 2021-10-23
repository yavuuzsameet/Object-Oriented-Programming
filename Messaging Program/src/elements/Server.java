
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package elements;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Yavuz
 *
 */
public class Server {
	
	/**
	 * capacity is the capacity of server.
	 * currentSize is the length of all messages combined in the server.
	 * msgs is the queue that stores messages.
	 */
	private long capacity;
	protected long currentSize = 0;
	public Queue<Message> msgs = new LinkedList<Message>();
	
	/**
	 * constructs a server with given capacity. 
	 * @param capacity is the capacity, which is given in the input, of this server.
	 */
	public Server(long capacity){
		this.capacity = capacity;
	}
	
	/**
	 * prints the warning messages.
	 * @param output is the output file which warnings are going to be printed.
	 * 
	 * ---not used---
	 */
	public void checkServerLoad(PrintStream output) { 
		double ratio = (double)currentSize / capacity; //ratio is the ratio of currentSize over capacity of server.
		if(ratio >= 50.0/100 && ratio < 80.0/100) {
			output.println("Warning! Server is 50% full.");
		} else if(ratio >= 80.0/100 && ratio < 100.0/100) {
			output.println("Warning! Server is 80% full.");
		} else if(ratio >= 100.0/100) {
			output.println("Server is full. Deleting all messages...");
			this.flush();
		}
	}
	
	/**
	 * prints the warning messages.
	 * @param prev is the previous ratio interval of currentSize over capacity of server.
	 * @param now is the current ratio interval of currentSize over capacity of server.
	 * @param out is the output file which warnings are going to be printed.
	 */
	public void myChecker(int prev, int now, PrintStream out) {
		if(((prev == 0) || (prev == 2) || (prev == 3)) && (now == 1)) { //if current ratio interval is 1  
			out.println("Warning! Server is 50% full.");                //prints 50%
		} else if(((prev == 0) || (prev == 1) || (prev == 3)) && (now == 2)) { //if current ratio interval is 2 
			out.println("Warning! Server is 80% full.");                       //prints 80%
		} else if(((prev == 0) || (prev == 1) || (prev == 2)) && (now == 3)) { //if current ratio interval is 3
			out.println("Server is full. Deleting all messages...");           //prints full 
			this.flush();                                                      //flushes
		}
	}
	
	/**
	 * 
	 * @return the current size of server.
	 */
	public long getCurrentSize() {
		return currentSize;
	}
	
	/**
	 * changes current size.
	 * @param size is the new currentSize.
	 */
	public void setCurrentSize(long size) {
		currentSize = size;
	}
	
	/**
	 * clear all messages in the server.
	 * then set currentSize to 0.
	 */
	public void flush() {
		msgs.clear();
		currentSize = 0;
	}
	
	/**
	 * 
	 * @return the ratio interval of currentSize over capacity of server.
	 */
	public int currentServerLoad() {
		if(((double)currentSize / capacity) < 50.0/100) { //if ratio smaller than 50%, return 0. 
			return 0;
		} else if(((double)currentSize / capacity) >= 50.0/100 && ((double)currentSize / capacity) < 80.0/100) { //if ratio smaller than 80%, return 1.
			return 1;
		} else if(((double)currentSize / capacity) >= 80.0/100 && ((double)currentSize / capacity) < 100.0/100) { //if ratio smaller than 100%, return 2.
			return 2;
		} else if(((double)currentSize / capacity) >= 100.0/100) { //if ratio larger than 100%, return 3.
			return 3;
		}else return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


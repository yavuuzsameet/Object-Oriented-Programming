
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import elements.Message;
import elements.Server;
import elements.User;

/**
 * 
 * @author Yavuz
 *
 */
public class Main {
	
	/**
	 * runs the program.
	 * @param args are the arguments given.
	 * @throws FileNotFoundException when lack of argument.
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		/**
		 * input scans the file given as first argument.
		 * output prints to the file given as second argument.
		 */
		Scanner input = new Scanner(new File(args[0]));
		PrintStream output = new PrintStream(new File(args[1]));
		
		/**
		 * myUsers is ArrayList to store users in this program.
		 */
		ArrayList<User> myUsers = new ArrayList<User>();
		
		/**
		 * firstLine scans the first line of input file.
		 * nofUsers is the number of users.
		 * nofQueries is the number of operations this program will do.
		 * server is constructed according to given capacity in the input.
		 */
		Scanner firstLine = new Scanner(input.nextLine());
		int nofUsers = firstLine.nextInt();
		int nofQueries = firstLine.nextInt();
		Server server = new Server(firstLine.nextLong());
		
		/**
		 * time is counter for the time passed, increases by one after every operation but reading messages.
		 * reading messages may have different contributions.
		 */
		int time = 0;
		
		/**
		 * users added to myUsers.
		 */
		for(int i=0; i<nofUsers; i++) {
			myUsers.add(i, new User(i));
		}
		
		for(int i=0; i<nofQueries; i++) {
			/**
			 * line scans the next line of the input.
			 * operation is the number that decides which event is going to be performed. 
			 */
			Scanner line = new Scanner(input.nextLine());
			int operation = line.nextInt();
			
			/**
			 * if event is #0, decides sender, receiver and message body from the line scanner.
			 * then sends a message. 
			 */
			if(operation == 0) {
				int a = line.nextInt();
				int b = line.nextInt();
				if(a < nofUsers && b < nofUsers) { //if users are not valid, do not operate.
					User inAction = myUsers.get(a); //inAction is user, sender.
					User other = myUsers.get(b); //other is user, receiver. 
					String text = ""; //text is body of the message.
					while(line.hasNext()) {
						text += line.next() + " ";
					}
					text = text.substring(0, text.length()-1);
				
					int prev = server.currentServerLoad(); //prev is the ratio interval of currentSize over capacity of server before sending a message.
				
					inAction.sendMessage(other, text, time, server); //message is sent.
					time++; //time is increased by one after sending a message.
				
					int now = server.currentServerLoad(); //now is the ratio interval of currentSize over capacity of server after sending a message.
				
					server.myChecker(prev, now, output); //checks server load, then prints warning message if necessary.		
				}
				else time++;
			}
			
			/**
			 * if event is #1, decides receiver from the line scanner.
			 * then receives messages. 
			 */
			else if(operation == 1) {
				int a = line.nextInt();
				if(a < nofUsers) { //if user is not valid, do not operate.
					User receiver = myUsers.get(a); //receiver is user, receiver.
				
					int prev = server.currentServerLoad(); //prev is the ratio interval of currentSize over capacity of server before receiving a message.
				
					receiver.getInbox().receiveMessages(server, time); //messages are received.
					time++; //time is increased by one after receiving a message.
				
					int now = server.currentServerLoad(); //now is the ratio interval of currentSize over capacity of server after receiving a message.
				
					server.myChecker(prev, now, output); //checks server load, then prints warning message if necessary.
				}
				else time++;
			}
			
			/**
			 * if event is #2, decides reader and number of messages to be read from the line scanner.
			 * then reads messages.
			 */
			else if(operation == 2) {
				int a = line.nextInt();
				if(a < nofUsers) { //if user is not valid, do not operate.
					User receiver = myUsers.get(a); //receiver is user, reader.
					int nofMessages = line.nextInt(); //nofMessages is number of messages to be read.
				
					time += receiver.getInbox().readMessages(nofMessages, time); //time is increased by number of messages read.
				}
				else time++;
			}
			
			/**
			 * if event is #21, decides reader and sender from the line scanner.
			 * then reads messages.
			 */
			else if(operation == 21) {
				int a = line.nextInt();
				int b = line.nextInt();
				if(a < nofUsers && b < nofUsers) { //if users are not valid, do not operate.
					User receiver = myUsers.get(a); //receiver is user, reader.
					User sender = myUsers.get(b); //sender is user, sender.
				
					time += receiver.getInbox().readMessages(sender, time); //time is increased by number of messages read.
				}
				else time++;
			}
			
			/**
			 * if event is #22, decides receiver and message id from the line scanner.
			 * then reads a message.
			 */
			else if(operation == 22) {
				int a = line.nextInt();
				if(a < nofUsers) { //if user is not valid, do not operate.
					User receiver = myUsers.get(a); //receiver is user, reader.
					int msgId = line.nextInt(); //msgId is the id of message to be read.
				
					receiver.getInbox().readMessage(msgId, time); //message is read.
					time++; //time is increased by one after reading a message.
				}
				else time++;
			}
			
			/**
			 * if event is #3 or #4, decides the users according to id's from the line scanner.
			 * if #3, make them friends.
			 * if #4, break friendship.
			 */
			else if(operation == 3 || operation == 4) {
				int a = line.nextInt();
				int b = line.nextInt();	
				if(a < nofUsers && b < nofUsers) { //if users are not valid, do not operate.
					User inAction = myUsers.get(a); //inAction is user, adder.
					User other = myUsers.get(b); //other is user, added.
				
					if(operation == 3) inAction.addFriend(other); //adding each other to friends list.
					if(operation == 4) inAction.removeFriend(other); //removing each other from friends list.
					time++; //time is increased by one after operation happened.
				}
				else time++;
			}
			
			/**
			 * if event is #5, flushes server.
			 */
			else if(operation == 5) {
				
				server.flush(); //server is flushed.
				time++; //time is increased by one after operation happened.
				
			}
			
			/**
			 * if event is #6, prints the current size of the server.
			 */
			else if(operation == 6) {
				
				output.println("Current load of the server is " + server.getCurrentSize() + " characters."); //currentSize is printed.
				time++; //time is increased by one after operation happened.
				
			}
			
			/**
			 * if event is #61, decides reader from the line scanner.
			 * then prints the last message user has read.
			 */
			else if(operation == 61) {
				int a = line.nextInt();
				if(a < nofUsers) { //if user is not valid, do not operate.
					User reader = myUsers.get(a); //reader is user, reader.
				
					if(!(reader.getInbox().read.isEmpty())) { //if user has not read any messages yet, do nothing.
						Iterator<Message> itr = reader.getInbox().read.iterator(); //iterator over messages user has read.
						Message x = reader.getInbox().read.peek(); //x is first message user has read.
						while(itr.hasNext()) {
							x = itr.next(); //x is now the last message user has read.
						}
						output.println(x); //print the last message.
					}
					time++; //time is increased by one after operation happened.
				}
				else time++;
			}
			
		}
		
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


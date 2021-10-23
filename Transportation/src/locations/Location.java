
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package locations;

import java.util.*;
import passengers.Passenger;

/**
 * 
 * @author Yavuz
 *
 */
public class Location{
	
	/**
	 * history : keeps track of every passenger who has visited.
	 * current : list of passenger who is here at the moment.
	 */
	private int ID;
	private double locationX;
	private double locationY;
	public ArrayList<Passenger> history = new ArrayList<Passenger>();
	public ArrayList<Passenger> current = new ArrayList<Passenger>();
	
	/**
	 * constructs a location.
	 * @param ID : is the number which in order is given when location is created.
	 * @param locationX : x-coordinate of the location.
	 * @param locationY : y-coordinate of the location.
	 */
	public Location(int ID, double locationX, double locationY){
		this.ID = ID;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	/**
	 * indicates distance between two locations.
	 * @param other : is the location which the distance between is going to be calculated.
	 * @return the distance between this location and other location. 
	 */
	public double getDistance(Location other) {
		return Math.sqrt(Math.pow((other.locationX - this.locationX),2) + Math.pow((other.locationY - this.locationY), 2));
	}
	
	/**
	 * adds the incoming passenger to current list of this location and adds the incoming passenger to history list if the incoming passenger has not visited this location yet.
	 * @param p : is the passenger which is going to be added to current passengers list and history of passengers list who is at this location at the moment.
	 */
	public void incomingPassenger(Passenger p) {
		this.current.add(p);
		
		boolean tf = true;
		for(int i=0; i<history.size(); i++) {
			if(p.getID() == history.get(i).getID()) tf = false;
		}
		if(tf) this.history.add(p);
		
	}
	
	/**
	 * removes the outgoing passenger from current passengers list.
	 * @param p : is the passenger which is going to be removed from current passengers list while leaving this location.
	 */
	public void outgoingPassenger(Passenger p) {
		this.current.remove(p);
	}
	
	/**
	 * 
	 * @return the x-coordinate of this location.
	 */
	public double getX() {
		return this.locationX;
	}
	
	/**
	 * 
	 * @return the y-coordinate of this location.
	 */
	public double getY() {
		return this.locationY;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


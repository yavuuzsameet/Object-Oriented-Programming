
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;

import locations.Location;

/**
 * 
 * @author Yavuz
 *
 */
public abstract class PublicTransport {

	private int ID;
	private double x1, y1, x2, y2;
	
	/**
	 * constructs a public transport vehicle.
	 * @param ID : is the number which in order is given when public transport vehicle is created.
	 * @param x1,y1,x2,y2 : are the borders of the range which this vehicle can travel.
	 */
	PublicTransport(int ID, double x1, double y1, double x2, double y2){
		this.ID = ID;
		this.x1 = Math.min(x1, x2);
		this.x2 = Math.max(x1,x2);
		this.y1 = Math.min(y1, y2);
		this.y2 = Math.max(y1, y2);
	}
	
	/**
	 * checks if this vehicle's range includes departure and arrival point or not.
	 * @param departure : departure location of the passenger.
	 * @param arrival : target location the passenger.
	 * @return whether this vehicle can ride to the given location.
	 */
	public boolean canRide (Location departure, Location arrival) {
		double dx = departure.getX();
		double dy = departure.getY();
		double ax = arrival.getX();
		double ay = arrival.getY();
		
		return ((dx >= x1 && dx <= x2) && (dy >= y1 && dy <= y2) && (ax >= x1 && ax <= x2) && (ay >= y1 && ay <= y2));
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE






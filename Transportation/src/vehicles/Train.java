
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import passengers.*;

/**
 * 
 * @author Yavuz
 *
 */
public class Train extends PublicTransport{
	
	/**
	 * constructs a train.
	 * @param ID : is the number which in order is given when train is created.
	 * @param x1,y1,x2,y2 : are the borders of the range which this train can travel.
	 */
	public Train(int ID, double x1, double y1, double x2, double y2){
		super(ID, x1, y1, x2, y2);
	}
	
	/**
	 * 
	 * @param p : is the passenger whose charge of using this train is going to be determined.
	 * @param distance : is the distance between two locations, departure and arrival.
	 * @return the price the passenger should pay.
	 */
	public int getPrice(Passenger p, double distance) {
		int price = 5;
		
		int stopnumber = (int) Math.floor(distance / 15);
		if(distance - stopnumber*15 >= 7.5) stopnumber += 1;
		
		if(p instanceof DiscountedPassenger) return price*stopnumber*8/10;
		if(p instanceof StandardPassenger) return price*stopnumber;
		
		return 0;
		
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


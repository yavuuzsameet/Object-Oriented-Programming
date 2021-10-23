
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;
import passengers.*;

/**
 * 
 * @author Yavuz
 *
 */
public class Bus extends PublicTransport{
	
	/**
	 * constructs a bus.
	 * @param ID : is the number which in order is given when bus is created.
	 * @param x1,y1,x2,y2 : are the borders of the range which this bus can travel.
	 */
	public Bus(int ID, double x1, double y1, double x2, double y2){
		super(ID, x1, y1, x2, y2);
	}
	
	/**
	 * 
	 * @param p : is the passenger whose charge of using this bus is going to be determined.
	 * @return the price the passenger should pay.
	 */
	public int getPrice(Passenger p) {
		int price = 2;
		
		if(p instanceof DiscountedPassenger) return price/2;
		if(p instanceof StandardPassenger) return price;
		
		return 0;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


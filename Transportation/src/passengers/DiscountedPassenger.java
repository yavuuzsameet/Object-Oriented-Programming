
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;
import locations.*;

/**
 * 
 * @author Yavuz
 *
 */
public class DiscountedPassenger extends Passenger {

	/**
	 * constructs a discounted passenger without a car.
	 * @param ID : is the number which in order is given when passenger is created.
	 * @param hasDriversLicense : shows whether this passenger has drivers license. 
	 * @param l : is the location where this passenger has been at the moment.
	 */
	public DiscountedPassenger(int ID, boolean hasDriversLicence, Location l){
		super(ID, hasDriversLicence, l);
	}
	
	/**
	 * constructs a discounted passenger with a car.
	 * @param ID : is the number which in order is given when passenger is created.
	 * @param l : is the location where this passenger has been at the moment.
	 * @param fuelConsumption : is the fuel consumption over kilometer of this passenger's car.
	 */
	public DiscountedPassenger(int ID, Location l, double fuelConsumption){
		super(ID, l, fuelConsumption);
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


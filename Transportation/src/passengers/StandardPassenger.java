
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;
import locations.Location;

/**
 * 
 * @author Yavuz
 *
 */
public class StandardPassenger extends Passenger {
	
	/**
	 * constructs a standard passenger without a car.
	 * @param ID : is the number which in order is given when passenger is created.
	 * @param hasDriversLicense : shows whether this passenger has drivers license. 
	 * @param l : is the location where this passenger has been at the moment.
	 */
	public StandardPassenger(int ID, boolean hasDriversLicence, Location l){
		super(ID, hasDriversLicence, l);
	}
	
	/**
	 * constructs a standard passenger with a car.
	 * @param ID : is the number which in order is given when passenger is created.
	 * @param l : is the location where this passenger has been at the moment.
	 * @param fuelConsumption : is the fuel consumption over kilometer of this passenger's car.
	 */
	public StandardPassenger(int ID, Location l, double fuelConsumption){
		super(ID, l, fuelConsumption);
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


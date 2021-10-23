
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package passengers;

import interfaces.*;
import locations.Location;
import vehicles.*;

/**
 * 
 * @author Yavuz
 *
 */
public abstract class Passenger implements ownCar, usePublicTransport {
	
	private int ID;
	private boolean hasDriversLicence;
	private double cardBalance;
	private Car car;
	private Location currentLocation;
	private boolean hasCar;
	
	/**
	 * constructs a passenger without a car.
	 * @param ID : is the number which in order is given when passenger is created.
	 * @param hasDriversLicense : shows whether this passenger has drivers license. 
	 * @param l : is the location where this passenger has been at the moment.
	 */
	Passenger(int ID, boolean hasDriversLicense, Location l){
		this.ID = ID;
		this.hasDriversLicence = hasDriversLicence;
		currentLocation = l;
		l.incomingPassenger(this);
	}
	
	/**
	 * constructs a passenger with a car.
	 * @param ID : is the number which in order is given when passenger is created.
	 * @param l : is the location where this passenger has been at the moment.
	 * @param fuelConsumption : is the fuel consumption over kilometer of this passenger's car.
	 */
	Passenger(int ID, Location l, double fuelConsumption){
		this.hasDriversLicence = true;
		this.ID = ID;
		this.currentLocation = l;
		car = new Car(ID, fuelConsumption);
		hasCar = true;
		l.incomingPassenger(this);
	}
	
	/**
	 * changes this passenger's current location to another 
	 * by using given public transport vehicle if this passenger have enough amount of money.
	 * @param p : is the public transport vehicle.
	 * @param l : is the target location which this passenger wants to travel.
	 */
	public void ride (PublicTransport p, Location l) {
		if(p.canRide(this.currentLocation, l)) {
			
			int price = 0;
			
			if(p instanceof Bus) {
				Bus bus = (Bus) p;
				price = bus.getPrice(this); 
			}	
			
			if(p instanceof Train) {
				Train train = (Train) p;
				price = train.getPrice(this, l.getDistance(this.currentLocation));
			}	
				
			if(price <= this.cardBalance) {
				this.currentLocation.outgoingPassenger(this);
				this.currentLocation = l;
				this.cardBalance -= price;
				l.incomingPassenger(this);
			}

		}
		
	}
	
	/**
	 * makes the addition of money to this passenger's travel card.
	 * @param amount : is the amount of money which is going to be added to card balance.
	 */
	public void refillCard(double amount) {
		this.cardBalance += amount;
	}
	
	/**
	 * makes the addition of fuel to gas tank of this passenger's car 
	 * by using refuel method of Car class.
	 * @param amount : is the amount of fuel which is going to be added to gas tank.
	 */
	public void refuel(double amount) {
		this.car.refuel(amount);
	}
	
	/**
	 * changes this passenger's current location to another 
	 * by using this passenger's car if the passenger has drivers license and the car has the enough amount of fuel.
	 * @param l : is the target location which this passenger wants to travel.
	 */
	public void drive (Location l) {
		double distance = this.currentLocation.getDistance(l);
		if(this.hasDriversLicence == true) {
			if(this.car.getFuelAmount() >= this.car.getFuelConsumption() * distance) {
				this.currentLocation.outgoingPassenger(this);
				this.currentLocation = l;
				l.incomingPassenger(this);
				this.car.setFuelAmount(this.car.getFuelAmount() - this.car.getFuelConsumption() * distance);
			}
		}
	}
	
	/**
	 * fills the car field of this passenger if empty or changes if it already existed.
	 * @param fuelConsumption : is the fuel consumption over kilometer of this passenger's new car. 
	 */
	public void purchaseCar (double fuelConsumption) {
		this.car = new Car(this.ID, fuelConsumption);
		hasCar = true;
	}
	
	/**
	 * changes the status of drivers license of this passenger.
	 * @param tf : indicates that whether this passenger will have the license.
	 */
	public void setLicense(boolean tf) {
		this.hasDriversLicence = tf;
	}
	
	/**
	 * 
	 * @return the current location of this passenger.
	 */
	public Location getLocation() {
		return this.currentLocation;
	}
	
	/**
	 * 
	 * @return whether this passenger has a car.
	 */
	public boolean getHasCar() {
		return this.hasCar;
	}
	
	/**
	 * 
	 * @return the car of this passenger.
	 */
	public Car getCar() {
		return this.car;
	}
	
	/**
	 * 
	 * @return the ID of this passenger.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * @return card balance of this passenger.
	 */
	public double getCardBalance() {
		return cardBalance;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


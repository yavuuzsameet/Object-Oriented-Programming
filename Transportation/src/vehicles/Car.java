
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package vehicles;

/**
 * 
 * @author Yavuz
 *
 */
public class Car{
	
	private int ownerID;
	private double fuelAmount;
	private double fuelConsumption;
	
	/**
	 * constructs a car.
	 * @param ID : is the ID of the owner of this car.
	 * @param fuelConsumption : is the fuel consumption over kilometer of this car. 
	 */
	public Car(int ID, double fuelConsumption){
		ownerID = ID;
		this.fuelConsumption = fuelConsumption;
	}
	
	/**
	 * makes the addition of fuel to gas tank of this car.
	 * @param amount : is the amount of fuel which is going to be added to gas tank.
	 */
	public void refuel (double amount) {
		fuelAmount += amount;
	}
	
	/**
	 * 
	 * @return the remaining fuel amount of this car.
	 */
	public double getFuelAmount() {
		return this.fuelAmount;
	}
	
	/**
	 * 
	 * @return the fuel consumption rate of this car.
	 */
	public double getFuelConsumption() {
		return this.fuelConsumption;
	}
	
	/**
	 * changes the amount of fuel of this car.
	 * @param newAmount : is the new fuel amount of this car.
	 */
	public void setFuelAmount(double newAmount) {
		this.fuelAmount = newAmount;
	}

	
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE


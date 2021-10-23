
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_input

package main;

import java.util.*;
import vehicles.*;
import java.io.*;
import passengers.*;
import locations.*;

/**
 * 
 * @author Yavuz
 *
 */
public class Main {
	/**
	 * 
	 * @param args : arguments given 
	 * @throws FileNotFoundException when lack of argument
	 */
	public static void main(String[] args) throws FileNotFoundException {
	
		Scanner input = new Scanner(new File(args[0]));
		PrintStream output = new PrintStream(new File(args[1]));

		/**
		 * passengers, locations, vehicles are the ArrayLists for passengers, locations and public transport vehicles
		 * and they store them respectively.
		 */
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		ArrayList<Location> locations = new ArrayList<Location>();
		ArrayList<PublicTransport> vehicles = new ArrayList<PublicTransport>();
		
		  Location l = new Location(0, 0, 0); // The first location is always (0,0).
		  locations.add(l);
		int operations = input.nextInt(); // operation count
		
		/**
		 * nofPassengers, nofLocations, nofPublicTransport are the numbers of passengers, locations and public transport vehicles respectively.
		 * They are increased by one when a new one is created.
		 */
		int nofPassengers = 0;
		int nofLocations = 1;
		int nofPublicTransport = 0;
		
		/**
		 * eleman is the operation number of each line given in the input from 1 to 7.
		 */
		int eleman;
		eleman = input.nextInt();
			
		while(input.hasNext()) {
			if(input.hasNext()) {	
				
				/**
				 * if the operation number is 1, create a new passenger according to given inputs.
				 * if passenger has a car and license, create a passenger with 2nd constructor,
				 * otherwise, create a passenger with 1st constructor.
				 * add to passengers then increase nofPassengers. 
				 */
				if(eleman == 1) {
				 
					String type = input.next();
					
					int forLicense = input.nextInt();
					boolean license;
					if(forLicense == 0) license = false;
					else license = true;
					
					int car = input.nextInt();
					double consumption;
					
					if(car==0) {
						if(type.equals("D")) passengers.add(new DiscountedPassenger(nofPassengers, license, l));
						if(type.equals("S")) passengers.add(new StandardPassenger(nofPassengers, license, l));
					}
					else {
						consumption = input.nextDouble();
						if(forLicense == 0) {
							if(type.equals("D")) passengers.add(new DiscountedPassenger(nofPassengers, license, l));
							if(type.equals("S")) passengers.add(new StandardPassenger(nofPassengers, license, l));
						}
						else {
							if(type.equals("D")) passengers.add(new DiscountedPassenger(nofPassengers, l, consumption));
							if(type.equals("S")) passengers.add(new StandardPassenger(nofPassengers, l, consumption));
						}
					}
				
					nofPassengers++;
					if(input.hasNext()) eleman = input.nextInt();
				
				}
			}
			
			if(input.hasNext()) {
				
				/**
				 * if the operation number is 2, create a new location according to given inputs.
				 * add to locations then increase nofLocations.
				 */
				if(eleman == 2) {
					double x = input.nextDouble();
					double y = input.nextDouble();
					
					locations.add(new Location(nofLocations, x, y));
					
					nofLocations++;
					if(input.hasNext()) eleman = input.nextInt();
				}
			}
			
			if(input.hasNext()) {
				
				/**
				 * if the operation number is 3, create a new public transport vehicle according to given inputs.
				 * it can be either bus or train.
				 * add to vehicles then increase nofPublicTransport.
				 */
				if(eleman == 3) {
					int type = input.nextInt();
				
					if(type == 1) vehicles.add(new Bus(nofPublicTransport, input.nextDouble(), input.nextDouble(), input.nextDouble(), input.nextDouble()));
					if(type == 2) vehicles.add(new Train(nofPublicTransport, input.nextDouble(), input.nextDouble(), input.nextDouble(), input.nextDouble()));
				
					nofPublicTransport++;
					if(input.hasNext()) eleman = input.nextInt();
				}
			}
			
			if(input.hasNext()) {
				
				/**
				 * if the operation number is 4, identify the passenger who wants to travel, location aimed to go and transportation type.
				 * after identification, call ride method if public transportation or drive method if own car.
				 * if target location is same with current location, do not operate.
				 * if target location or passenger or vehicle is null, do not operate.  
				 */
				if(eleman == 4) {
					int travellingPassengerID = input.nextInt();
					int targetLocationID = input.nextInt();
					int type = input.nextInt();
					int publicVehicleID = 0;
					if(type != 3) publicVehicleID = input.nextInt();
					
					Passenger traveller = null;
					if(travellingPassengerID < nofPassengers) 
						traveller = passengers.get(travellingPassengerID);
					
					Location target = null;
					if(targetLocationID < nofLocations)
						target = locations.get(targetLocationID);
					
					PublicTransport vehicle = null;
					if(publicVehicleID < nofPublicTransport)
						vehicle = vehicles.get(publicVehicleID);
					 
					if(traveller != null && target != null) {
						if(!(traveller.getLocation().getX() == target.getX() && traveller.getLocation().getY() == target.getY())) {
							if(vehicle != null) {
								if(type == 1 && vehicle instanceof Bus || type == 2 && vehicle instanceof Train) {
									traveller.ride(vehicle, target); 
								}
							}
							if(type == 3 && traveller.getHasCar()) {
								traveller.drive(target);
							}
						}
					}
					if(input.hasNext()) eleman = input.nextInt();
				}
			}
			
			if(input.hasNext()) {
				
				/**
				 * if the operation number is 5, identify the passenger who wants to purchase a new car and fuel consumption rate of the car.
				 * give the passenger a driversLicense and buy the car.
				 * if passenger is null, do not operate.
				 */
				if(eleman == 5) {
					int consumerID = input.nextInt();
					double consumptionRateOfNewCar = input.nextDouble();
				
					Passenger consumer = null;
					if(consumerID < nofPassengers)
						consumer = passengers.get(consumerID);
					
					if(consumer != null) {
						consumer.setLicense(true);
						consumer.purchaseCar(consumptionRateOfNewCar);
					}
				
					if(input.hasNext()) eleman = input.nextInt();
				}
			}
			
			if(input.hasNext()) {
				
				/**
				 * if the operation number is 6, identify the passenger who wants to refuel the car and the amount of fuel.
				 * refuel the car if passenger truly has a car.
				 * if passenger is null, do not operate.
			     */
				if(eleman == 6) {
					int consumerID = input.nextInt();
					double amount = input.nextDouble();
				
					Passenger consumer = null;
					if(consumerID < nofPassengers)
						consumer = passengers.get(consumerID);
					
					if(consumer != null)
						if(consumer.getHasCar()) 
							consumer.refuel(amount);
				
					if(input.hasNext()) eleman = input.nextInt();
				}
			}
			
			if(input.hasNext()) {
				
				/**
				 * if the operation number is 7, identify the passenger who wants to refill the card and amount of money.
				 * refill the card.
				 * if passenger is null, do not operate.
				 */
				if(eleman == 7) {
					int consumerID =input.nextInt();
					double amount = input.nextDouble();
				
					Passenger consumer = null;
					if(consumerID < nofPassengers)
						consumer = passengers.get(consumerID);
					
					if(consumer != null) consumer.refillCard(amount);
				
					if(input.hasNext()) eleman = input.nextInt();
				}
			}
		}
			
		/**
		 * first line of the output printed out separately from the rest.
		 * locations' current list of passengers sorted according to passenger ID.
		 * then every passenger of that location is printed out to output file.
		 * print remaining fuel if passenger has car, else amount left in the card. 
		 */
		Location firstline = locations.get(0);
		output.printf("Location %d: (%.2f, %.2f)", 0, firstline.getX(), firstline.getY());
		if(!(firstline.current.isEmpty())) {
			
			firstline.current.sort((o1, o2) -> Integer.valueOf(o1.getID()).compareTo(o2.getID()));
			
			for(int j=0; j<firstline.current.size(); j++) {
				
				Passenger yazdir = firstline.current.get(j);
				if(yazdir.getHasCar()) {
					double toBePrinted = (double)((int)(yazdir.getCar().getFuelAmount()*100))/100;
					output.printf("\nPassenger %d: %.2f", yazdir.getID(), toBePrinted);
				}
				else {
					double toBePrinted = (double)((int)(yazdir.getCardBalance()*100))/100;
					output.printf("\nPassenger %d: %.2f", yazdir.getID(), toBePrinted); 
				}
				
		    }
		}
		
		if(locations.size() > 1) {
			for(int m=1; m<locations.size(); m++) {
				
				Location print = locations.get(m);
				double XtoBePrinted = (double)((int)(print.getX()*100))/100, YtoBePrinted = (double)((int)(print.getY()*100))/100;
				output.printf("\nLocation %d: (%.2f, %.2f)", m, print.getX(), print.getY());
				
				if(!(print.current.isEmpty())) {
					
					print.current.sort((o1, o2) -> Integer.valueOf(o1.getID()).compareTo(o2.getID()));
					
					for(int j=0; j<print.current.size(); j++) {
						
						Passenger yazdir = print.current.get(j);
						if(yazdir.getHasCar()) {
							double toBePrinted = (double)((int)(yazdir.getCar().getFuelAmount()*100))/100; 
							output.printf("\nPassenger %d: %.2f", yazdir.getID(), toBePrinted);
						}
						else {
							double toBePrinted = (double)((int)(yazdir.getCardBalance()*100))/100;
							output.printf("\nPassenger %d: %.2f", yazdir.getID(), toBePrinted); 
						}
				     }
					
				}
			}
		}
			
	}

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_input


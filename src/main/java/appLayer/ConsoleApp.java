package appLayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import dataLayer.CarJDBC;
import domainLayer.Car;

public class ConsoleApp {

	public static void main(String[] args) {

		// 1. We instance BufferedReader to collect data from user
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 2. We instance the CarJDBC class, to use its methods to communicate to sql DB
		CarJDBC carJDBC = new CarJDBC();

		// 3. We create a basic console program

		int choice = 0;
		String licensePlate, make, model;
		double price;
		int carId;
		do {
			System.out.println("Select the operation to perform\n" + "1 -> view all cars\n" + "2 -> insert a car\n"
					+ "3 -> change values of a car\n" + "4 -> delete a car");

			try {
				choice = Integer.parseInt(in.readLine());

				switch (choice) {
				case 1:

					List<Car> myCars = carJDBC.select();

					myCars.forEach(car -> {
						System.out.println(car);
					});
					break;
				case 2:
					System.out.println("Insert license plate");
					licensePlate = in.readLine();
					System.out.println("Insert the maker");
					make = in.readLine();
					System.out.println("Insert the model");
					model = in.readLine();
					System.out.println("Insert the price");
					price = Double.parseDouble(in.readLine());

					carJDBC.insert(new Car(licensePlate, make, model, price));
					break;
				case 3:
					System.out.println("Insert id from car you want to update");
					carId = Integer.parseInt(in.readLine());
					System.out.println("Insert license plate");
					licensePlate = in.readLine();
					System.out.println("Insert the maker");
					make = in.readLine();
					System.out.println("Insert the model");
					model = in.readLine();
					System.out.println("Insert the price");
					price = Double.parseDouble(in.readLine());

					carJDBC.update(new Car(carId, licensePlate, make, model, price));
					break;
				case 4:
					System.out.println("Insert id from car you want to update");
					carId = Integer.parseInt(in.readLine());

					carJDBC.delete(new Car(carId));
					break;

				default:
					System.out.println("please, introduce a number between 1 and 5.");
					break;
				}

			} catch (NumberFormatException ex) {
				System.out.println("the expression you introduce has an incorrect format");
				ex.printStackTrace(System.out);
			} catch (IOException ex) {
				System.out.println("you didn't introduce a valid expression");
				ex.printStackTrace(System.out);
			}

		} while (choice != 5);
		
		System.out.println("good bye");

	}

}

package domainLayer;

/*
 * 
 * Each table in SQL must correspond to Java class in the Domain Layer.
 *
 * Steps:
 * 1. Define variables. We shoud create the same variables as fiels we have in the SQL table.
 *    SQL table primary key should ve an int type. Variable names must be significative.
 * 2. Define constructors: empty, only primary key, all, all except primary key.
 * 3. Define getters and setters
 * 4. Define toString method.
 * (IDE can automate steps 2 to 4).
 */

public class Car {

	/*
	 * Car variables, which correspond to a data type in the sql table.
	 */
	private int carId;
	private String licensePlate;
	private String make;
	private String model;
	private double price;

	/*
	 * Constructor methods.
	 * 
	 * SQL database will autogenerate carId for each new car we introduce. We could
	 * need some diferent type of constructor depending of the sql sentence we
	 * execute.
	 */
	public Car() {
	}

	public Car(int carId) {
		this.carId = carId;
	}

	public Car(int carId, String licensePlate, String make, String model, double price) {
		this.carId = carId;
		this.licensePlate = licensePlate;
		this.make = make;
		this.model = model;
		this.price = price;
	}

	public Car(String licensePlate, String make, String model, double price) {
		this.licensePlate = licensePlate;
		this.make = make;
		this.model = model;
		this.price = price;
	}

	/*
	 * Getters and setter.
	 */

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*
	 * toString() method, this is created mainly for test pourposes, feel free to reorganizate it.
	 */

	@Override
	public String toString() {
		return "Car [carId = " + carId + ", licensePlate = " + licensePlate + ", make = " + make + ", model = " + model
				+ ", price = " + price + "]";
	}

}

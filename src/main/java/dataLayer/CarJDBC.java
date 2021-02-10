package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domainLayer.Car;
import static dataLayer.ConnectionToDB.*;

/*
 * In this class we'll define the different methods to interact with the database.
 * 
 * write this: import static dataLayer.ConnectionToDB.*;
 * to import all static methods from ConnectionToDB class
 * 
 * We will use the following interfaces/classes from java.sql library
 * > Connection: to establish a connetion between our Data Base and our Java Application
 * > PreparedStatement: to help us configure a SQL Sentence.
 * > ResultSet: to execute the sql queries to our database. 
 * 
 */

public class CarJDBC {
	
	/*
	 * We define the SQL SENTENCES in an String constant.
	 * Each ? will be changed with the data we provide in the class methods. In the insert method I will explain this in more detail.
	 */
	

	private static final String SQL_SELECT = "SELECT id_car, license_plate, make, model, price FROM car";
	//allows us to see the whole table.
	private static final String SQL_INSERT = "INSERT INTO car (license_plate, make, model, price) VALUES(?, ?, ?, ?)";
	//inserts a new car into the table
	private static final String SQL_UPDATE = "UPDATE car SET license_plate = ?, make = ?, model = ?, price = ? WHERE id_car = ?";
	//changes one (or more) values to a car, given its id.
	private static final String SQL_DELETE = "DELETE FROM car WHERE id_car = ?";
	//deletes a car, given its id.
	
	
	/*
	 * Select method, it will return a list with all our cars.
	 */
	
	public List<Car> select() {
		
		//1. we instance the Connection, PreparedStatemt and ResultSet objects as null, as we have to
		//define them in a try/catch
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//2. we instance a car and a collection type list to gather all data DB is going to send.
		Car car = null;
		List<Car> myCars = new ArrayList<Car>();

		
		try {
			//3. we define the Connection, PreparedStatemt and ResultSet objects
			conn = ConnectionToDB.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT); //we need to specify the SQL sentence we wanna execute
			rs = stmt.executeQuery();
			
			//4. in this loop, we will gather data from each row in the car table, store them in variables,
			//instance a Car object and store it in the List collection.
			while (rs.next()) {
				
				int carId = rs.getInt("id_car");
				String licensePlate = rs.getString("license_plate");
				String make = rs.getString("make");
				String model = rs.getString("model");
				double price = rs.getDouble("price");
				//we use .getXXXX(NAME OF THE ITEM), depending on the data type.

				car = new Car(carId, licensePlate, make, model, price);
				
				myCars.add(car);
			}
			//if anything goes wrong, the error message will appear in the console.
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace(System.out);
		}
		
		finally {
			try {
				//5. we close al the connections.
				close(rs);
				close(stmt);
				close(conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		}
		//we get a collection with all our cars.
		return myCars;
	}
	
	
	/*
	 * INSERT METHOD 
	 * it will add a car to our SQL table.
	 * We don't need to use the ResultSet object here.
	 * We use public INT because the PreparedStatemt.executeUpdate() returns an int
	 * with the number of registers that have been changed.
	 */
	
	public int insert(Car car) {
		Connection conn = null;
		PreparedStatement stmt = null;

		int register = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_INSERT);
			//each "?" in the SQL Statement correspond to one of the next sentences.
			//they follow an order, 1 will be the license plate, 2 the make...
			stmt.setString(1, car.getLicensePlate());
			stmt.setString(2, car.getMake());
			stmt.setString(3, car.getModel());
			stmt.setDouble(4, car.getPrice());
			
			register = stmt.executeUpdate(); 
			//this sentence will execute an update, adding the new car
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace(System.out);
		}
		
		finally {
			try {
				close(stmt);
				close(conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace(System.out);
			}
		}
		System.out.println("Operation successful");
		return register;
	}
	
	/*
	 * UPDATE METHOD.
	 * 
	 * The following methods are very similar to the ones already explained.
	 */
	
	
	public int update(Car car) {
		Connection conn = null;
		PreparedStatement stmt = null;

		int register = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, car.getLicensePlate());
			stmt.setString(2, car.getMake());
			stmt.setString(3, car.getModel());
			stmt.setDouble(4, car.getPrice());
			stmt.setInt(5, car.getCarId());
			register = stmt.executeUpdate(); 
			
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
		
		finally {
			try {
				close(stmt);
				close(conn);
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}
		}
		System.out.println("Operation successful");
		return register;
	}
	
	/*
	 * DELETE METHOD
	 */
	
	public int delete(Car car) {
		Connection conn = null;
		PreparedStatement stmt = null;

		int register = 0;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, car.getCarId());
			register = stmt.executeUpdate(); //este metodo ejecutará la sentencia insert y devolverá el numero de registos afectados.
			
			
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
		
		finally {
			try {
				close(stmt);
				close(conn);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace(System.out);
			}
		}
		System.out.println("Operation successful");
		return register;
	}
	
}
